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
	private String tableSchem; // 表Schema
	private String tableName; // 表名
	private String remarks;// 表注释，有些库不提供此字段，如SQLServer, Oracle

	private String domainClassName; // 对应领域模型的名称
	private List<Column> columns = new ArrayList<Column>(); // 此表所有列

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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
