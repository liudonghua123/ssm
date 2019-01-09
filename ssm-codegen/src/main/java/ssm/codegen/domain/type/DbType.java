package ssm.codegen.domain.type;

/**
 * 目前这个类用不着了
 *
 * @author jinqinghua@gmail.com
 * @version 2013年11月18日 下午10:03:41
 */
public enum DbType {
    /* MYSQL */
    MYSQL(
            "MYSQL",
            "com.mysql.jdbc.Driver",
            "select t.TABLE_NAME from INFORMATION_SCHEMA.`TABLES` t where t.TABLE_SCHEMA='%s' and t.TABLE_TYPE='BASE TABLE' %s order by t.TABLE_NAME",
            "select t.COLUMN_NAME,t.COLUMN_COMMENT from INFORMATION_SCHEMA.`COLUMNS` t  where t.TABLE_SCHEMA='%s' and t.TABLE_NAME='%s'"),

    /* MYSQL */
    ORACLE(
            "ORACLE",
            "oracle.jdbc.driver.OracleDriver",
            "select TABLE_NAME from ALL_TABLES t where t.OWNER='%s' %s order by t.TABLE_NAME",
            "select a.COLUMN_NAME, COMMENTS as COLUMN_COMMENT from ALL_TAB_COMMENTS a, ALL_COL_COMMENTS b "
                    + " where a.TABLE_NAME = b.TABLE_NAME "
                    + " and a.COLUMN_NAME = b.COLUMN_NAME and a.OWNER = b.OWNER and a.OWNER = '%s' and a.TABLE_NAME = '%s'"),

    /* SQLSERVER */
    SQLSERVER("SQLSERVER", "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            "select [NAME] as TABLE_NAME from SYSOBJECTS t where t.XTYPE='U' %s order by t.TABLE_NAME", ""),

    /* DB2 */
    DB2("DB2", "com.ibm.db2.jcc.DB2Driver", "", ""),

    /* POSTGRESQL */
    POSTGRESQL("POSTGRESQL", "org.postgresql.Driver", "", ""),

    /* HSQLDB */
    HSQLDB("HSQLDB", "org.hsqldb.jdbcDriver", "", ""),

    H2("H2", "org.h2.Driver", "", "");

    private final String dbmsProductName;
    private final String jdbcDriver;
    private final String sqlGetTableNames;
    private final String sqlGetComments;

    DbType(String dbmsProductName, String jdbcDriver, String sqlGetTableNames, String sqlGetComments) {
        this.dbmsProductName = dbmsProductName;
        this.jdbcDriver = jdbcDriver;
        this.sqlGetTableNames = sqlGetTableNames;
        this.sqlGetComments = sqlGetComments;
    }

    public String getDbmsProductName() {
        return dbmsProductName;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public String getSqlGetTableNames() {
        return sqlGetTableNames;
    }

    public String getSqlGetComments() {
        return sqlGetComments;
    }

}
