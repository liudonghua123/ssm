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

	private String author = "jinqinghua@gmail.com";
	private String dateFormatStyle = "yyyy-MM-dd HH:mm:ss";

	private String databaseProductName; // 数据库ORACLE|MYSQL|MSSQLSERVER
	private String projectPathString; // 项目根目录（生成文件的目录）
	private String projectSourcePathString; // 项目src目录
	private String projectResourcesPathString; // resoures目录，支持Maven项目

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

		this.author = cfg.getString("cfg.author");
		this.dateFormatStyle = cfg.getString("cfg.dateFormatStyle");

		// DBMS
		this.databaseProductName = cfg.getString("cfg.databaseProductName");
		// PATH
		this.projectPathString = cfg.getString("cfg.path.project");
		this.projectSourcePathString = cfg.getString("cfg.path.projectSource");
		this.projectResourcesPathString = cfg.getString("cfg.path.projectResources");

		// PACKAGE
		this.projectPackageName = cfg.getString("cfg.package.project");
		this.domainPackageName = cfg.getString("cfg.package.domain");

		this.daoPackageName = cfg.getString("cfg.package.dao");
		this.daoJdbcImplPackageName = cfg.getString("cfg.package.daoJdbcImpl");
		this.daoSqlSessionImplPackageName = cfg.getString("cfg.package.daoSqlSessionImpl");
		this.daoMyBatisMapperPackageName = cfg.getString("cfg.package.daoMyBatisMapper");

		this.servicePackageName = cfg.getString("cfg.package.service");
		this.serviceImplPackageName = cfg.getString("cfg.package.serviceImpl");

		this.facadePackageName = cfg.getString("cfg.package.facade");
		this.facadeImplPackageName = cfg.getString("cfg.package.facadeImpl");

		this.ssmPackageName = cfg.getString("cfg.package.ssm");
		// CLASS
		this.baseDomainClassName = cfg.getString("cfg.class.baseDomain");
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
		return databaseProductName.toLowerCase().concat("/").concat(ftlFileName);
	}

	// ==============================getters==============================
	public String getAuthor() {
		return author;
	}

	public String getDateFormatStyle() {
		return dateFormatStyle;
	}

	public String getDatabaseProductName() {
		return databaseProductName;
	}

	public String getProjectPathString() {
		return projectPathString;
	}

	public String getProjectSourcePathString() {
		return projectSourcePathString;
	}

	public String getProjectResourcesPathString() {
		return projectResourcesPathString;
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
