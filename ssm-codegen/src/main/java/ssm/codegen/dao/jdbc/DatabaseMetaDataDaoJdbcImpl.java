package ssm.codegen.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import ssm.codegen.dao.DatabaseMetaDataDao;
import ssm.codegen.domain.Column;
import ssm.codegen.domain.PrimaryKey;
import ssm.codegen.domain.Table;
import ssm.codegen.util.JavaBeansUtils;
import ssm.codegen.util.JdbcTypeResolver;
import ssm.codegen.util.JdbcTypeResolver.JdbcTypeInformation;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月18日 下午4:50:33
 */
@Repository
public class DatabaseMetaDataDaoJdbcImpl extends JdbcDaoSupport implements DatabaseMetaDataDao {

    public static final String TABLE_SCHEM = "TABLE_SCHEM";
    public static final String TABLE_NAME = "TABLE_NAME";
    public static final String REMARKS = "REMARKS";
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private DataSource dataSource;

    public String selectJdbcUrl() {
        assert super.getJdbcTemplate() != null;
        return (String) super.getJdbcTemplate().execute(new ConnectionCallback<Object>() {
            public Object doInConnection(Connection connection) throws SQLException {
                return connection.getMetaData().getURL();
            }
        });
    }

    public String selectSchemaPattern() {
        assert super.getJdbcTemplate() != null;
        return (String) super.getJdbcTemplate().execute(new ConnectionCallback<Object>() {
            public Object doInConnection(Connection connection) throws SQLException {
                DatabaseMetaData dbMetaData = connection.getMetaData();
                log.debug("{} -> {}", dbMetaData.getDatabaseProductName(), dbMetaData.getDatabaseProductVersion());
                log.debug("{} -> {}", dbMetaData.getDriverName(), dbMetaData.getDriverVersion());

                SimpleDriverDataSource simpleDriverDataSource = ((SimpleDriverDataSource) dataSource);
                // MySQL大小写不关注
                // Oracle必须大写
                // SQLServer默认DBO
                String schemaPattern = Objects.requireNonNull(simpleDriverDataSource.getUsername()).toUpperCase();
                String url = simpleDriverDataSource.getUrl();

                assert url != null;
                if (url.toLowerCase().contains("sqlserver")) {
                    schemaPattern = "DBO";
                }
                return schemaPattern;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<Table> selectTables(final String schemaPattern) {
        assert super.getJdbcTemplate() != null;
        return (List<Table>) super.getJdbcTemplate().execute(new ConnectionCallback<Object>() {
            public Object doInConnection(Connection connection) throws SQLException {
                DatabaseMetaData dbMetaData = connection.getMetaData();
                ResultSet rst = dbMetaData.getTables(null, schemaPattern, null, new String[]{"TABLE"});
                Assert.notNull(rst, "ResultSet should not be null");
                List<Table> tables = new ArrayList<>();
                while (rst.next()) {
                    Table table = new Table();

                    table.setTableSchem(rst.getString(TABLE_SCHEM));
                    table.setTableName(rst.getString(TABLE_NAME).toUpperCase());
                    table.setRemarks(rst.getString(REMARKS));
                    table.setDomainClassName(JavaBeansUtils.getCamelCaseString(table.getTableName(), true));

                    log.debug("\n\n==============================表{}", table.getTableName());
                    log.debug("[TABLE_NAME]:{}", table.getTableName());// 表名
                    log.debug("[REMARKS]:{}", table.getRemarks());// 注释

                    tables.add(table);
                }
                rst.close();
                connection.close();

                return tables;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<PrimaryKey> selectPrimaryKeys(final String schemaPattern, final String tableName) {
        assert super.getJdbcTemplate() != null;
        return (List<PrimaryKey>) super.getJdbcTemplate().execute(new ConnectionCallback<Object>() {
            public Object doInConnection(Connection connection) throws SQLException {
                DatabaseMetaData dbMetaData = connection.getMetaData();

                List<PrimaryKey> primaryKeys = new ArrayList<>();
                ResultSet primaryKeysResultSet = dbMetaData.getPrimaryKeys(null, schemaPattern, tableName);
                Assert.notNull(primaryKeysResultSet, "ResultSet should not be null");
                while (primaryKeysResultSet.next()) {
                    PrimaryKey primaryKey = new PrimaryKey();
                    primaryKey.setTableSchem(primaryKeysResultSet.getString(TABLE_SCHEM));
                    primaryKey.setTableName(primaryKeysResultSet.getString(TABLE_NAME).toUpperCase());
                    primaryKey.setColumnName(primaryKeysResultSet.getString("COLUMN_NAME").toUpperCase());
                    primaryKey.setPkName(primaryKeysResultSet.getString("PK_NAME").toUpperCase());
                    log.debug("\n------------------------------主键{}", primaryKey.getColumnName());
                    log.debug("[PK_NAME]:{}", primaryKey.getPkName());
                    primaryKeys.add(primaryKey);
                }
                primaryKeysResultSet.close();
                connection.close();
                return primaryKeys;
            }
        });

    }

    @SuppressWarnings("unchecked")
    public List<Column> selectColumns(final String schemaPattern) {
        assert super.getJdbcTemplate() != null;
        return (List<Column>) super.getJdbcTemplate().execute(new ConnectionCallback<Object>() {
            public Object doInConnection(Connection connection) throws SQLException {
                DatabaseMetaData dbMetaData = connection.getMetaData();

                ResultSet rsc = dbMetaData.getColumns(null, schemaPattern, null, null);
                Assert.notNull(rsc, "ResultSet should not be null");
                List<Column> columns = new ArrayList<>();
                while (rsc.next()) {
                    Column column = new Column();
                    column.setTableSchem(rsc.getString(TABLE_SCHEM));
                    column.setTableName(rsc.getString(TABLE_NAME).toUpperCase());
                    column.setColumnName(rsc.getString("COLUMN_NAME").toUpperCase());
                    column.setDataType(rsc.getInt("DATA_TYPE"));
                    column.setTypeName(rsc.getString("TYPE_NAME"));
                    column.setColumnSize(rsc.getInt("COLUMN_SIZE"));
                    column.setDecimalDigits(rsc.getInt("DECIMAL_DIGITS"));
                    column.setNullable(rsc.getInt("NULLABLE"));
                    column.setRemarks(rsc.getString(REMARKS));
                    column.setColumnDef(rsc.getString("COLUMN_DEF"));
                    column.setIsNullable(rsc.getString("IS_NULLABLE"));
                    // column.setIsAutoincrement(rsc.getString("IS_AUTOINCREMENT"));

                    JdbcTypeInformation type = JdbcTypeResolver.getJdbcTypeInformation(column.getDataType(),
                            column.getColumnSize(), column.getDecimalDigits());

                    column.setJavaPropertyName(column.getColumnName().toLowerCase());// TODO 此名仅简单地小写了一下
                    column.setJdbcTypeName(type.getJdbcTypeName());
                    column.setJavaClassName(type.getJavaClassName());
                    column.setJavaClassSimpleName(type.getJavaClassSimpleName());

                    log.debug("\n------------------------------列{}", column.getColumnName());
                    log.debug("[TABLE_SCHEM]:{}", column.getTableSchem());
                    log.debug("[TABLE_NAME]:{}", column.getTableName());
                    log.debug("[COLUMN_NAME]:{}", column.getColumnName());
                    log.debug("[DATA_TYPE]:{}", column.getDataType());
                    log.debug("[TYPE_NAME]:{}", column.getTypeName());
                    log.debug("[COLUMN_SIZE]:{}", column.getColumnSize());
                    log.debug("[DECIMAL_DIGITS]:{}", column.getDecimalDigits());
                    log.debug("[NULLABLE]:{}", column.getNullable());
                    log.debug("[REMARKS]:{}", column.getRemarks());
                    log.debug("[COLUMN_DEF]:{}", column.getColumnDef());
                    log.debug("[IS_NULLABLE]:{}", column.getIsNullable());
                    // log.debug("[IS_AUTOINCREMENT]:{}", column.getIsAutoincrement());

                    columns.add(column);
                }
                rsc.close();
                connection.close();
                return columns;
            }
        });
    }

}
