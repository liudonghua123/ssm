package ssm.codegen.domain;

/**
 * @author Jin,QingHua
 * @version build 2013.11
 */
public enum DbType {
	/* MYSQL */
	MYSQL(
			"MYSQL",
			"select t.TABLE_NAME from INFORMATION_SCHEMA.`TABLES` t where t.TABLE_SCHEMA='%s' and t.TABLE_TYPE='BASE TABLE' %s order by t.TABLE_NAME",
			"select t.COLUMN_NAME,t.COLUMN_COMMENT from INFORMATION_SCHEMA.`COLUMNS` t  where t.TABLE_SCHEMA='%s' and t.TABLE_NAME='%s'"),

	/* MYSQL */
	ORACLE(
			"ORACLE",
			"select TABLE_NAME from ALL_TABLES t where t.OWNER='%s' %s order by t.TABLE_NAME",
			"select a.COLUMN_NAME, COMMENTS as COLUMN_COMMENT from ALL_TAB_COMMENTS a, ALL_COL_COMMENTS b "
					+ " where a.TABLE_NAME = b.TABLE_NAME "
					+ " and a.COLUMN_NAME = b.COLUMN_NAME and a.OWNER = b.OWNER and a.OWNER = '%s' and a.TABLE_NAME = '%s'"),

	/* SQLSERVER */
	SQLSERVER("SQLSERVER", "select [NAME] as TABLE_NAME from SYSOBJECTS t where t.XTYPE='U' %s order by t.TABLE_NAME",
			""),

	/* DB2 */
	DB2("DB2", "", "");

	private String dbmsPorductName;
	private String sqlGetTableNames;
	private String sqlGetComments;

	private DbType(String dbmsPorductName, String sqlGetTableNames, String sqlGetComments) {
		this.dbmsPorductName = dbmsPorductName;
		this.sqlGetTableNames = sqlGetTableNames;
		this.sqlGetComments = sqlGetComments;
	}

	public String getDbmsPorductName() {
		return dbmsPorductName;
	}

	public String getSqlGetTableNames() {
		return sqlGetTableNames;
	}

	public String getSqlGetComments() {
		return sqlGetComments;
	}

}
