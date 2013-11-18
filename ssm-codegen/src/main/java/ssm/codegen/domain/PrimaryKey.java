package ssm.codegen.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月18日 上午10:17:02 主键
 * @see http://technet.microsoft.com/zh-cn/library/ms379041.aspx
 */
public class PrimaryKey {
	private String tableSchem;// 表架构的名称
	private String tableName;// 表名
	private String cloumnName; // 列的名称
	private short KeySeq;// 多列主键中列的序列号
	private String PkName;// 主键的名称

	public PrimaryKey() {
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

	public String getCloumnName() {
		return cloumnName;
	}

	public void setCloumnName(String cloumnName) {
		this.cloumnName = cloumnName;
	}

	public short getKeySeq() {
		return KeySeq;
	}

	public void setKeySeq(short keySeq) {
		KeySeq = keySeq;
	}

	public String getPkName() {
		return PkName;
	}

	public void setPkName(String pkName) {
		PkName = pkName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
