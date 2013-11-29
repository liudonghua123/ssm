package ssm.codegen.domain.support;

/**
 * 
 * @author jinqinghua@gmail.com
 * @version 2013年11月29日 下午9:55:48
 * @see java.sql.DatabaseMetaData#getPrimaryKeys(String, String, String)
 * @see http://technet.microsoft.com/zh-cn/library/ms379041.aspx
 */
public class PrimaryKeyResultSetColumns {

	private String tableCat;
	private String tableSchem;
	private String tableName;
	private String columnName;
	private String keySeq;
	private String pkName;

	public PrimaryKeyResultSetColumns() {

	}

	public String getTableCat() {
		return tableCat;
	}

	public void setTableCat(String tableCat) {
		this.tableCat = tableCat;
	}

	public String getTableSchem() {
		return tableSchem;
	}

	public void setTableSchem(String tableSchem) {
		this.tableSchem = tableSchem;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(String keySeq) {
		this.keySeq = keySeq;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

}
