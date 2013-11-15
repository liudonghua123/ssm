package ssm.codegen.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Jin,QingHua
 * @version build 2008.12
 */
public class MyType {

	/**
	 * 
	 * java.sql.Types[not use]
	 */
	private int jdbcType;

	/**
	 * eg. DECIMAL
	 */
	private String jdbcTypeName;

	/**
	 * e.g. java.lang.Long[not use]
	 */
	private Class<? extends Object> javaClass;

	/**
	 * e.g. java.lang.Long
	 */
	private String javaClassName;

	/**
	 * e.g. Long
	 */
	private String javaClassSimpleName;

	public MyType() {

	}

	public int getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(int jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getJdbcTypeName() {
		return jdbcTypeName;
	}

	public void setJdbcTypeName(String jdbcTypeName) {
		this.jdbcTypeName = jdbcTypeName;
	}

	public Class<? extends Object> getJavaClass() {
		return javaClass;
	}

	public void setJavaClass(Class<? extends Object> javaClass) {
		this.javaClass = javaClass;
	}

	public String getJavaClassName() {
		return javaClassName;
	}

	public void setJavaClassName(String javaClassName) {
		this.javaClassName = javaClassName;
	}

	public String getJavaClassSimpleName() {
		if (null == this.javaClassSimpleName || this.javaClassSimpleName.length() == 0) {
			return StringUtils.substringAfterLast(this.javaClassName, ".");
		} else {
			return javaClassSimpleName;
		}
	}

	public void setJavaClassSimpleName(String javaClassSimpleName) {
		this.javaClassSimpleName = javaClassSimpleName;
	}
}
