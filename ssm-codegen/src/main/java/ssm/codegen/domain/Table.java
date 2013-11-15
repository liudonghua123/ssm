package ssm.codegen.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
	private List<Column> columns = new ArrayList<Column>();

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

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
