package ssm.codegen.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jinqinghua@gmail.com
 * @version 2013.11.15
 */
public class Table {
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
	private Set<Column> columns = new HashSet<Column>();

	public Table() {
	}

	public Table(String name) {
		this.tableName = name;
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

	public Set<Column> getColumns() {
		return columns;
	}

	public void setColumns(Set<Column> columns) {
		this.columns = columns;
	}

}
