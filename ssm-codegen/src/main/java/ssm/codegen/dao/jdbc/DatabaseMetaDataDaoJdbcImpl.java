package ssm.codegen.dao.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import org.springframework.util.Assert;

import ssm.codegen.config.CodeGeneratorConfig;
import ssm.codegen.dao.DatabaseMetaDataDao;
import ssm.codegen.domain.Column;
import ssm.codegen.domain.MyType;
import ssm.codegen.domain.Table;
import ssm.codegen.util.JavaBeansUtils;
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

	@Override
	public Set<String> selectTableNames(boolean isIncludeAllTables) throws DataAccessException {
		return this.selectTableNames(false);
	}

	@Override
	public Set<String> selectTableNames() throws DataAccessException {
		return (Set<String>) super.getJdbcTemplate().execute(new ConnectionCallback<Object>() {
			@Override
			public Object doInConnection(Connection connection) throws SQLException, DataAccessException {
				DatabaseMetaData dbMetaData = connection.getMetaData();
				log.debug("{} -> {}", dbMetaData.getDatabaseProductName(), dbMetaData.getDatabaseProductVersion());

				SimpleDriverDataSource simpleDriverDataSource = ((SimpleDriverDataSource) dataSource);
				// MySQL大小写不关注
				// Oracle必须大写
				// SQLServer不需要
				String schemaPattern = simpleDriverDataSource.getUsername().toUpperCase();
				String url = simpleDriverDataSource.getUrl();

				if (url.toLowerCase().contains("sqlserver")) {
					schemaPattern = null;
				}

				ResultSet rst = dbMetaData.getTables(null, schemaPattern, null, new String[] { "TABLE" });
				Assert.notNull(rst);

				Set<String> tableNames = new HashSet<String>();
				String tableName = null;
				while (rst.next()) {
					log.debug("[TABLE_NAME]:{}", rst.getString("TABLE_NAME"));// 表名
					log.debug("[TABLE_TYPE]:{}", rst.getString("TABLE_TYPE"));// 表类型，TABLE,VIEW等
					log.debug("[REMARKS]:{}", rst.getString("REMARKS"));// 注释

					tableName = rst.getString("TABLE_NAME").toUpperCase();
					tableNames.add(tableName);
				}
				rst.close();
				connection.close();

				return tableNames;
			}
		});
	}

	@Override
	public Set<Map<String, Object>> selectColumnNames(final String tableName) throws DataAccessException {
		return (Set<Map<String, Object>>) super.getJdbcTemplate().execute(new ConnectionCallback<Object>() {
			@Override
			public Object doInConnection(Connection connection) throws SQLException, DataAccessException {
				DatabaseMetaData dbMetaData = connection.getMetaData();
				String schemaPattern = ((SimpleDriverDataSource) dataSource).getUsername();

				ResultSet rsc = dbMetaData.getColumns(null, schemaPattern, tableName, null);
				Set<Map<String, Object>> set = new HashSet<Map<String, Object>>();
				while (rsc.next()) {
					log.debug("[COLUMN_NAME ]:{}", rsc.getString("COLUMN_NAME"));
					log.debug("[DATA_TYPE]:{}", rsc.getInt("DATA_TYPE"));
					log.debug("[TYPE_NAME]:{}", rsc.getString("TYPE_NAME"));
					log.debug("[COLUMN_SIZE]:{}", rsc.getInt("COLUMN_SIZE"));
					log.debug("[DECIMAL_DIGITS]:{}", rsc.getInt("DECIMAL_DIGITS"));
					log.debug("[NULLABLE]:{}", rsc.getInt("NULLABLE"));
					log.debug("[REMARKS]:{}", rsc.getString("REMARKS"));
					log.debug("[COLUMN_DEF]:{}", rsc.getString("COLUMN_DEF"));
					log.debug("[IS_NULLABLE]:{}", rsc.getString("IS_NULLABLE"));
					// log.debug("[IS_AUTOINCREMENT]:{}", rsc.getString("IS_AUTOINCREMENT"));

					Map<String, Object> map = new HashMap<String, Object>();

					String columnName = rsc.getString("COLUMN_NAME").toUpperCase();
					map.put("columnName", columnName);

					String columnComments = rsc.getString("REMARKS").toUpperCase();
					map.put("columnComments", columnComments);

					String propertyName = columnName.toLowerCase();
					map.put("propertyName", propertyName);

					int columnSize = rsc.getInt("COLUMN_SIZE");
					map.put("columnSize", columnSize);

					int decimalDigits = rsc.getInt("DECIMAL_DIGITS");
					map.put("decimalDigits", decimalDigits);

					int dataType = rsc.getInt("DATA_TYPE");
					map.put("dataType", dataType);

					MyType mts = TypeUtils.getType(dataType, columnSize, decimalDigits);

					String jdbcTypeName = mts.getJdbcTypeName();
					map.put("jdbcTypeName", jdbcTypeName);
					log.debug("[jdbcTypeName]:{}", jdbcTypeName);

					String javaClassName = mts.getJavaClassName();
					map.put("javaClassName", javaClassName);
					log.debug("[javaClassName]:{}", javaClassName);

					String javaClassSimpleName = StringUtils.substringAfterLast(javaClassName, ".");
					map.put("javaClassSimpleName", javaClassSimpleName);
					log.debug("[javaClassSimpleName]:{}", javaClassSimpleName);

					set.add(map);
				}
				rsc.close();
				connection.close();

				return set;
			}
		});
	}

	@Override
	public Set<Table> selectTables() throws DataAccessException {
		return (Set<Table>) super.getJdbcTemplate().execute(new ConnectionCallback<Object>() {
			@Override
			public Object doInConnection(Connection connection) throws SQLException, DataAccessException {
				DatabaseMetaData dbMetaData = connection.getMetaData();
				log.debug("{} -> {}", dbMetaData.getDatabaseProductName(), dbMetaData.getDatabaseProductVersion());

				SimpleDriverDataSource simpleDriverDataSource = ((SimpleDriverDataSource) dataSource);
				// MySQL大小写不关注
				// Oracle必须大写
				// SQLServer不需要
				String schemaPattern = simpleDriverDataSource.getUsername().toUpperCase();
				String url = simpleDriverDataSource.getUrl();

				if (url.toLowerCase().contains("sqlserver")) {
					schemaPattern = null;
				}

				ResultSet rst = dbMetaData.getTables(null, schemaPattern, null, new String[] { "TABLE" });
				Assert.notNull(rst);

				Set<Table> tables = new HashSet<Table>();
				Table table = new Table();

				while (rst.next()) {
					table.setTableName(rst.getString("TABLE_NAME"));
					table.setRemarks(rst.getString("REMARKS"));
					table.setDomainClassName(JavaBeansUtils.getCamelCaseString(table.getTableName(), true));

					tables.add(table);

					log.debug("\n\n========================================表{}", table.getTableName());
					log.debug("[TABLE_NAME]:{}", table.getTableName());// 表名
					log.debug("[REMARKS]:{}", table.getRemarks());// 注释

					ResultSet rsc = dbMetaData.getColumns(null, schemaPattern, table.getTableName(), null);
					Assert.notNull(rsc);

					Set<Column> columns = new HashSet<Column>();
					Column column = new Column();
					while (rsc.next()) {
						column.setColumnName(rsc.getString("COLUMN_NAME"));
						column.setDataType(rsc.getInt("DATA_TYPE"));
						column.setTypeName(rsc.getString("TYPE_NAME"));
						column.setColumnSize(rsc.getInt("COLUMN_SIZE"));
						column.setDecimalDigits(rsc.getInt("DECIMAL_DIGITS"));
						column.setNullable(rsc.getInt("NULLABLE"));
						column.setRemarks(rsc.getString("REMARKS"));
						column.setColumnDef(rsc.getString("COLUMN_DEF"));
						column.setIsNullable(rsc.getString("IS_NULLABLE"));
						// column.setIsAutoincrement(rsc.getString("IS_AUTOINCREMENT"));

						MyType type = TypeUtils.getType(column.getDataType(), column.getColumnSize(),
								column.getDecimalDigits());
						column.setPropertyName(column.getColumnName().toLowerCase());
						column.setJdbcTypeName(type.getJdbcTypeName());
						column.setJavaClassName(type.getJavaClassName());
						column.setJavaClassSimpleName(type.getJavaClassSimpleName());

						log.debug("\n----------------------------------------列{}", rsc.getString("COLUMN_NAME"));
						log.debug("[COLUMN_NAME ]:{}", rsc.getString("COLUMN_NAME"));
						log.debug("[DATA_TYPE]:{}", rsc.getInt("DATA_TYPE"));
						log.debug("[TYPE_NAME]:{}", rsc.getString("TYPE_NAME"));
						log.debug("[COLUMN_SIZE]:{}", rsc.getInt("COLUMN_SIZE"));
						log.debug("[DECIMAL_DIGITS]:{}", rsc.getInt("DECIMAL_DIGITS"));
						log.debug("[NULLABLE]:{}", rsc.getInt("NULLABLE"));
						log.debug("[REMARKS]:{}", rsc.getString("REMARKS"));
						log.debug("[COLUMN_DEF]:{}", rsc.getString("COLUMN_DEF"));
						log.debug("[IS_NULLABLE]:{}", rsc.getString("IS_NULLABLE"));
						// log.debug("[IS_AUTOINCREMENT]:{}", rsc.getString("IS_AUTOINCREMENT"));

						columns.add(column);
					}
					table.setColumns(columns);
					rsc.close();
				}
				rst.close();
				connection.close();

				return tables;
			}
		});
	}
}
