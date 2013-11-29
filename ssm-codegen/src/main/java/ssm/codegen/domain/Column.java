package ssm.codegen.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ssm.codegen.domain.support.ColumnResultSetColumns;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月18日 上午11:21:52
 * @see http://technet.microsoft.com/zh-cn/library/ms379048.aspx
 */
public class Column extends ColumnResultSetColumns {

	private String jdbcTypeName;// 列对应的jdbcType名，如VARCHAR
	private String javaPropertyName;// 列对应的java属性名，如fieldName
	private String javaClassName;// 列对应的Java类名称, 如java.lang.String
	private String javaClassSimpleName; // 列对应的的Java短名，如String

	private boolean primaryKey = false; // 是否是主键盘，默认为false

	public Column() {

	}

	public String getJdbcTypeName() {
		return jdbcTypeName;
	}

	public void setJdbcTypeName(String jdbcTypeName) {
		this.jdbcTypeName = jdbcTypeName;
	}

	public String getJavaPropertyName() {
		return javaPropertyName;
	}

	public void setJavaPropertyName(String javaPropertyName) {
		this.javaPropertyName = javaPropertyName;
	}

	public String getJavaClassName() {
		return javaClassName;
	}

	public void setJavaClassName(String javaClassName) {
		this.javaClassName = javaClassName;
	}

	public String getJavaClassSimpleName() {
		return javaClassSimpleName;
	}

	public void setJavaClassSimpleName(String javaClassSimpleName) {
		this.javaClassSimpleName = javaClassSimpleName;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
