package ssm.codegen.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ssm.codegen.domain.support.TableResultSetColumns;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月18日 上午11:21:11
 * @see http://technet.microsoft.com/zh-cn/library/ms378769.aspx
 */
public class Table extends TableResultSetColumns {

	private String domainClassName; // 对应领域模型的名称
	private List<Column> columns = new ArrayList<Column>(); // 此表所有列

	public Table() {

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
