package ssm.codegen.config;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jin,QingHua
 * @version build 2008.12
 */
public class CodeGeneratorConfig {

	private final Logger logger = LoggerFactory.getLogger(CodeGeneratorConfig.class);

	@Resource(name = "propertiesConfiguration")
	private PropertiesConfiguration cfg;

	private String dbmsPorductName; // 数据库ORACLE|MYSQL|MSSQLSERVER
	private String projectPathString; // 项目根目录（生成文件的目录）
	private String projectSourcePathString; // 项目src目录
	private String projectPackageName; // 项目包，一般是com.company.project
	private String domainPackageName;
	private String daoPackageName;
	private String daoJdbcImplPackageName;
	private String daoSqlSessionImplPackageName;
	private String daoMyBatisMapperPackageName;
	private String servicePackageName;
	private String serviceImplPackageName;
	private String facadePackageName;
	private String facadeImplPackageName;
	private String ssmPackageName;
	private String baseDomainClassName;
	private String tableNameSqlConditions; // 过滤表名的SQL条件

	public CodeGeneratorConfig() {
		logger.debug("do construct...");
	}

	public void init() throws ConfigurationException {
		logger.debug("init...");

		// DBMS
		this.dbmsPorductName = cfg.getString("ssm.dbmsPorductName");
		// PATH
		this.projectPathString = cfg.getString("ssm.path.projectPathString");
		this.projectSourcePathString = cfg.getString("ssm.path.projectSourcePathString");
		// PACKAGE
		this.projectPackageName = cfg.getString("ssm.package.projectPackageName");
		this.domainPackageName = cfg.getString("ssm.package.domainPackageName");

		this.daoPackageName = cfg.getString("ssm.package.daoPackageName");
		this.daoJdbcImplPackageName = cfg.getString("ssm.package.daoJdbcImplPackageName");
		this.daoSqlSessionImplPackageName = cfg.getString("ssm.package.daoSqlSessionImplPackageName");
		this.daoMyBatisMapperPackageName = cfg.getString("ssm.package.daoMyBatisMapperPackageName");

		this.servicePackageName = cfg.getString("ssm.package.servicePackageName");
		this.serviceImplPackageName = cfg.getString("ssm.package.serviceImplPackageName");

		this.facadePackageName = cfg.getString("ssm.package.facadePackageName");
		this.facadeImplPackageName = cfg.getString("ssm.package.facadeImplPackageName");

		this.ssmPackageName = cfg.getString("ssm.package.ssmPackageName");
		// CLASS
		this.baseDomainClassName = cfg.getString("ssm.class.baseDomainClassName");
		// SQL
		this.tableNameSqlConditions = cfg.getString("sql.tablename.conditions");
		logger.info("tableNameSqlConditions:{}", tableNameSqlConditions);
	}

	/**
	 * 将Package名称变为绝对地址
	 * 
	 * @param packageName
	 * @return Package名称的绝对地址
	 */
	public File convertPackageToPath(final String packageName) {
		return new File(this.getProjectSourcePath(), StringUtils.replace(packageName, ".", "/"));
	}

	/**
	 * 获取项目src目录的绝对地址
	 * 
	 * @return src目录的绝对地址
	 */
	public File getProjectSourcePath() {
		return new File(this.projectSourcePathString);
	}

	/**
	 * 获取不同数据库模板ftl文件的位置
	 * 
	 * @return ftl文件的位置
	 */
	public String getDbmsFtlFilePathString(String ftlFileName) {
		return dbmsPorductName.toLowerCase().concat("/").concat(ftlFileName);
	}

	public String getDbmsPorductName() {
		return dbmsPorductName;
	}

	public String getProjectPathString() {
		return projectPathString;
	}

	public String getProjectSourcePathString() {
		return projectSourcePathString;
	}

	public String getProjectPackageName() {
		return projectPackageName;
	}

	public String getDomainPackageName() {
		return domainPackageName;
	}

	public String getDaoPackageName() {
		return daoPackageName;
	}

	public String getDaoJdbcImplPackageName() {
		return daoJdbcImplPackageName;
	}

	public String getDaoSqlSessionImplPackageName() {
		return daoSqlSessionImplPackageName;
	}

	public String getDaoMyBatisMapperPackageName() {
		return daoMyBatisMapperPackageName;
	}

	public String getServicePackageName() {
		return servicePackageName;
	}

	public String getServiceImplPackageName() {
		return serviceImplPackageName;
	}

	public String getFacadePackageName() {
		return facadePackageName;
	}

	public String getFacadeImplPackageName() {
		return facadeImplPackageName;
	}

	public String getSsmPackageName() {
		return ssmPackageName;
	}

	public String getBaseDomainClassName() {
		return baseDomainClassName;
	}

	public String getTableNameSqlConditions() {
		return tableNameSqlConditions;
	}

}
