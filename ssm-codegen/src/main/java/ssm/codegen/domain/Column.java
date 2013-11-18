package ssm.codegen.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月18日 上午11:21:52
 * @see http://technet.microsoft.com/zh-cn/library/ms379048.aspx
 */
public class Column {
	private String tableSchem;// Schema
	private String tableName;// 表名
	private String columnName;// 列名
	private int dataType;// 来自 java.sql.Types 的 SQL 数据类型
	private String typeName;// 数据类型的名称
	private int columnSize;// 列的精度
	private int decimalDigits;// 列的小数位数
	private int nullable;// 指示列是否可以为 Null可以是下列值之一：columnNoNulls (0)columnNullable (1)
	private String remarks;// 与列关联的注释,对于此列，SQL Server 始终返回 Null
	private String columnDef;// 列的默认值
	private String isNullable;// 指示列是否允许 Null 值
	private String isAutoincrement;// 如果列是自动递增的，则为“是”如果列不是自动递增的，则为“否”如果驱动程序无法确定列是否为自动递增，则为 ""（空字符串）1

	private String jdbcTypeName;// 列对应的jdbcType名，如VARCHAR
	private String javaPropertyName;// 列对应的java属性名，如fieldName
	private String javaClassName;// 列对应的Java类名称, 如java.lang.String
	private String javaClassSimpleName; // 列对应的的Java短名，如String

	private boolean primaryKey = false; // 是否是主键盘，默认为false

	public Column() {
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

	public Column(String name) {
		this.columnName = name;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public int getDecimalDigits() {
		return decimalDigits;
	}

	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	public int getNullable() {
		return nullable;
	}

	public void setNullable(int nullable) {
		this.nullable = nullable;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getColumnDef() {
		return columnDef;
	}

	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getIsAutoincrement() {
		return isAutoincrement;
	}

	public void setIsAutoincrement(String isAutoincrement) {
		this.isAutoincrement = isAutoincrement;
	}

	public String getJavaPropertyName() {
		return javaPropertyName;
	}

	public void setJavaPropertyName(String javaPropertyName) {
		this.javaPropertyName = javaPropertyName;
	}

	public String getJdbcTypeName() {
		return jdbcTypeName;
	}

	public void setJdbcTypeName(String jdbcTypeName) {
		this.jdbcTypeName = jdbcTypeName;
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
