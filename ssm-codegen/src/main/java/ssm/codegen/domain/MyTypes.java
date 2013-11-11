package ssm.codegen.domain;

/**
 * @author Jin,QingHua
 * @version build 2008.12
 */
public class MyTypes {

	/**
	 * java.sql.Types
	 */
	private int jdbcType;

	/**
	 * eg. DECIMAL
	 */
	private String jdbcTypeName;

	/**
	 * eg. java.lang.Long
	 */
	private Class<? extends Object> javaClass;

	/**
	 * eg. Long
	 */
	private String javaClassName;

	public MyTypes() {

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

}
