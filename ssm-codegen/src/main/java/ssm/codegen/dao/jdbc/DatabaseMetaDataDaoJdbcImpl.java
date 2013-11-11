package ssm.codegen.dao.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Service;

import ssm.codegen.config.CodeGeneratorConfig;
import ssm.codegen.dao.DatabaseMetaDataDao;
import ssm.codegen.domain.DbType;
import ssm.codegen.domain.MyTypes;
import ssm.codegen.util.TypeUtils;

/**
 * @author Jin,QingHua
 * @version build 2008.12
 */
@Service
public class DatabaseMetaDataDaoJdbcImpl extends JdbcDaoSupport implements DatabaseMetaDataDao {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private DataSource dataSource;

	@Resource(name = "codeGeneratorConfig")
	private CodeGeneratorConfig cfg;

	private String schemaPattern = null;

	private boolean isAllTables = false;

	@Override
	public List<String> selectTableNameList() throws DataAccessException {
		return this.selectTableNameList(false);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> selectTableNameList(boolean isIncludeAllTables) throws DataAccessException {
		isAllTables = isIncludeAllTables;
		return (List<String>) super.getJdbcTemplate().execute(new ConnectionCallback<Object>() {
			@Override
			public Object doInConnection(Connection connection) throws SQLException, DataAccessException {
				DatabaseMetaData dbMetaData = connection.getMetaData();
				log.debug("{} -> {}", dbMetaData.getDatabaseProductName(), dbMetaData.getDatabaseProductVersion());

				List<String> tableNameList = new ArrayList<String>();
				String dbmsPorductName = StringUtils.upperCase(cfg.getDbmsPorductName());
				log.debug("dbmsPorductName:{}", dbmsPorductName);
				String sql = "";
				String sqlConditions = isAllTables ? cfg.getTableNameSqlConditions() : "";
				ResultSet rs = null;

				schemaPattern = ((SimpleDriverDataSource) dataSource).getUsername();
				log.debug("schemaPattern:{}", schemaPattern);

				switch (DbType.valueOf(dbmsPorductName)) {
				case MYSQL:
					sql = String.format(DbType.MYSQL.getSqlGetTableNames(), schemaPattern, sqlConditions);
					break;
				case ORACLE:
					sql = String.format(DbType.ORACLE.getSqlGetTableNames(), schemaPattern, sqlConditions);
					break;
				case SQLSERVER:
					sql = String.format(DbType.SQLSERVER.getSqlGetTableNames(), sqlConditions);
					break;
				case DB2:
					log.warn("not yet implement");
					break;
				default:
					log.warn("not yet implement");
					break;
				}

				log.info("sql:{}", sql);
				rs = connection.createStatement().executeQuery(sql);

				String tableName = null;
				if (null == rs) {
					return null;
				}
				while (rs.next()) {
					tableName = rs.getString("TABLE_NAME").toUpperCase();
					log.debug("[table]:{}", tableName);
					tableNameList.add(tableName);
				}
				rs.close();

				return tableNameList;
			}
		});
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectColumnNameList(final String tableName) throws DataAccessException {
		return (List<Map<String, Object>>) super.getJdbcTemplate().execute(new ConnectionCallback<Object>() {
			@Override
			public Object doInConnection(Connection connection) throws SQLException, DataAccessException {
				DatabaseMetaData dbMetaData = connection.getMetaData();
				schemaPattern = ((SimpleDriverDataSource) dataSource).getUsername();

				String dbmsPorductName = cfg.getDbmsPorductName();
				String sqlComments = null;

				Map<String, String> commentsMap = new HashMap<String, String>();

				switch (DbType.valueOf(dbmsPorductName)) {
				case MYSQL:
					sqlComments = String.format(DbType.MYSQL.getSqlGetComments(), schemaPattern,
							cfg.getTableNameSqlConditions());
					break;
				case ORACLE:
					sqlComments = String.format(DbType.ORACLE.getSqlGetComments(), schemaPattern,
							cfg.getTableNameSqlConditions());
					break;
				case SQLSERVER:
					sqlComments = String.format(DbType.SQLSERVER.getSqlGetComments(), cfg.getTableNameSqlConditions());
					break;
				case DB2:
					sqlComments = String.format(DbType.DB2.getSqlGetComments(), cfg.getTableNameSqlConditions());
					break;
				default:
					log.warn("not yet implement");
					break;
				}

				ResultSet rsc = connection.prepareStatement(sqlComments).executeQuery(sqlComments);
				if (null == rsc) {
					return null;
				}
				while (rsc.next()) {
					commentsMap.put(rsc.getString("column_name"), rsc.getString("column_comment"));
				}
				rsc.close();

				ResultSet columns = dbMetaData.getColumns(null, schemaPattern, tableName, null);
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				while (columns.next()) {
					Map<String, Object> map = new HashMap<String, Object>();

					String columnName = columns.getString("COLUMN_NAME").toUpperCase();
					map.put("columnName", columnName);
					log.debug("[columnName]:{}", columnName);

					map.put("columnComments", commentsMap.get(columnName));
					log.debug("[columnComments]:{}", commentsMap.get(columnName));

					String propertyName = columnName.toLowerCase();
					map.put("propertyName", propertyName);
					log.debug("[propertyName]:{}", propertyName);

					int columnSize = columns.getInt("COLUMN_SIZE");
					map.put("columnSize", columnSize);
					log.debug("[columnSize]:{}", columnSize);

					int decimalDigits = columns.getInt("DECIMAL_DIGITS");
					map.put("decimalDigits", decimalDigits);
					log.debug("[decimalDigits]:{}", decimalDigits);

					int dataType = columns.getInt("DATA_TYPE");
					map.put("dataType", dataType);
					log.debug("[dataType]:{}", dataType);

					MyTypes mts = TypeUtils.getTypes(dataType, columnSize, decimalDigits);

					String jdbcTypeName = mts.getJdbcTypeName();
					map.put("jdbcTypeName", jdbcTypeName);
					log.debug("[jdbcTypeName]:{}", jdbcTypeName);

					String javaClassName = mts.getJavaClassName();
					map.put("javaClassName", javaClassName);
					log.debug("[javaClassName]:{}", javaClassName);

					String javaClassSimpleName = StringUtils.substringAfterLast(javaClassName, ".");
					map.put("javaClassSimpleName", javaClassSimpleName);
					log.debug("[javaClassSimpleName]:{}", javaClassSimpleName);

					list.add(map);
				}
				return list;
			}
		});
	}
}
