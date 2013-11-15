package ssm.codegen.dao.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

	@Override
	public List<Table> selectTables() throws DataAccessException {
		return (List<Table>) super.getJdbcTemplate().execute(new ConnectionCallback<Object>() {
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

				List<Table> tables = new ArrayList<Table>();
				while (rst.next()) {
					Table table = new Table();

					table.setTableName(rst.getString("TABLE_NAME").toUpperCase());
					table.setRemarks(rst.getString("REMARKS"));
					table.setDomainClassName(JavaBeansUtils.getCamelCaseString(table.getTableName(), true));

					tables.add(table);

					// log.debug("\n\n========================================表{}", table.getTableName());
					// log.debug("[TABLE_NAME]:{}", table.getTableName());// 表名
					// log.debug("[REMARKS]:{}", table.getRemarks());// 注释

					ResultSet rsc = dbMetaData.getColumns(null, schemaPattern, table.getTableName(), null);
					Assert.notNull(rsc);

					List<Column> columns = new ArrayList<Column>();
					while (rsc.next()) {
						Column column = new Column();

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

						// log.debug("\n----------------------------------------列{}", rsc.getString("COLUMN_NAME"));
						// log.debug("[COLUMN_NAME ]:{}", rsc.getString("COLUMN_NAME"));
						// log.debug("[DATA_TYPE]:{}", rsc.getInt("DATA_TYPE"));
						// log.debug("[TYPE_NAME]:{}", rsc.getString("TYPE_NAME"));
						// log.debug("[COLUMN_SIZE]:{}", rsc.getInt("COLUMN_SIZE"));
						// log.debug("[DECIMAL_DIGITS]:{}", rsc.getInt("DECIMAL_DIGITS"));
						// log.debug("[NULLABLE]:{}", rsc.getInt("NULLABLE"));
						// log.debug("[REMARKS]:{}", rsc.getString("REMARKS"));
						// log.debug("[COLUMN_DEF]:{}", rsc.getString("COLUMN_DEF"));
						// log.debug("[IS_NULLABLE]:{}", rsc.getString("IS_NULLABLE"));
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
