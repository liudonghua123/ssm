package ssm.codegen.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月18日 上午11:21:11
 * @see http://technet.microsoft.com/zh-cn/library/ms378769.aspx
 */
public class Table {
	private String tableSchem; // 架构名
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 注释
	 */
	private String remarks;
	/**
	 * 对应的领域模型名
	 */
	private String domainClassName;
	/**
	 * 包含的列的集合
	 */
	private List<Column> columns = new ArrayList<Column>();

	private List<PrimaryKey> primaryKeys = new ArrayList<PrimaryKey>();

	public Table() {
	}

	public Table(String name) {
		this.tableName = name;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDomainClassName() {
		return domainClassName;
	}

	public void setDomainClassName(String domainClassName) {
		this.domainClassName = domainClassName;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public List<PrimaryKey> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<PrimaryKey> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
