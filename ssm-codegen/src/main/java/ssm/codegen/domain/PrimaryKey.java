package ssm.codegen.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ssm.codegen.domain.support.PrimaryKeyResultSetColumns;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月18日 上午10:17:02 主键
 * @see http://technet.microsoft.com/zh-cn/library/ms379041.aspx
 */
public class PrimaryKey extends PrimaryKeyResultSetColumns {

	public PrimaryKey() {

	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
