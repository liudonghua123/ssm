package ssm.codegen.config;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ssm.codegen.domain.type.DbType;
import ssm.codegen.domain.type.ProjectType;

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

	private String projectTypeName;// MAVEN|ECLIPSE
	private String jdbcUrl;

	// private String databaseProductName; // 数据库ORACLE|MYSQL|MSSQLSERVER|DB2
	private String projectPathName; // 项目根目录（生成文件的目录）

	private String mavenProjectJavaPathName;
	private String mavenProjectResourcesPathName;
	private String mavenProjectWebappPathName;

	private String eclipseProjectSrcPathName;
	private String eclipseProjectWebContentPathName;

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

	// private String tableNameSqlConditions; // 过滤表名的SQL条件

	public CodeGeneratorConfig() {
		logger.debug("CodeGeneratorConfig Construct...");
	}

	public void init() throws ConfigurationException {
		logger.debug("CodeGeneratorConfig Init...");

		// this.author = cfg.getString("cfg.author");
		// this.dateFormatStyle = cfg.getString("cfg.dateFormatStyle");
		this.projectTypeName = cfg.getString("cfg.type.project");
		this.jdbcUrl = cfg.getString("jdbc.url");

		// PATH
		this.projectPathName = cfg.getString("cfg.path.project");

		this.mavenProjectJavaPathName = cfg.getString("cfg.path.project.maven.java");
		this.mavenProjectResourcesPathName = cfg.getString("cfg.path.project.maven.resources");
		this.mavenProjectWebappPathName = cfg.getString("cfg.path.project.maven.webapp");

		this.eclipseProjectSrcPathName = cfg.getString("cfg.path.project.eclipse.src");
		this.eclipseProjectWebContentPathName = cfg.getString("cfg.path.project.eclipse.WebContent");

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
		// this.tableNameSqlConditions = cfg.getString("sql.tablename.conditions");
		// logger.info("tableNameSqlConditions:{}", tableNameSqlConditions);
	}

	/**
	 * 将Package名称变为绝对地址
	 */
	public File convertPackageNameToPath(final String packageName) {
		return this.convertPackageNameToPath(this.getProjectSourcePath(), packageName);
	}

	public File convertPackageNameToPath(File path, final String packageName) {
		return new File(path, StringUtils.replace(packageName, ".", "/"));
	}

	/**
	 * 获取项目java|src目录的绝对地址
	 * 
	 */
	public File getProjectSourcePath() {
		switch (ProjectType.valueOf(this.projectTypeName.toUpperCase())) {
		case MAVEN:
			return new File(this.mavenProjectJavaPathName);
		case ECLIPSE:
			return new File(this.eclipseProjectSrcPathName);
		}
		return null;
	}

	/**
	 * 获取项目resources目录的绝对地址
	 */
	public File getProjectResourcePath() {
		switch (ProjectType.valueOf(this.projectTypeName.toUpperCase())) {
		case MAVEN:
			return new File(this.mavenProjectResourcesPathName);
		case ECLIPSE:
			return new File(this.eclipseProjectSrcPathName);// eclipse resoures和src在同一目录
		}
		return null;
	}

	/**
	 * 获取项目webapp|WebContent目录的绝对地址
	 */
	public File getProjectWebappPath() {
		switch (ProjectType.valueOf(this.projectTypeName.toUpperCase())) {
		case MAVEN:
			return new File(this.mavenProjectWebappPathName);
		case ECLIPSE:
			return new File(this.eclipseProjectWebContentPathName);
		}
		return null;
	}

	/**
	 * 根据数据库类型(从jdbc.url获取)获取不同数据库模板ftl文件的位置
	 * 
	 */
	public String getFtlFilePathName(String ftlFileName) {
		for (DbType dbType : DbType.values()) {
			if (this.jdbcUrl.toLowerCase().startsWith("jdbc:" + dbType.getDbmsPorductName().toLowerCase())) {
				return dbType.getDbmsPorductName().toLowerCase().concat("/").concat(ftlFileName);
			}
		}
		return null;
	}

	// ==============================Getter and Setters=========================
	public String getAuthor() {
		return author;
	}

	public String getDateFormatStyle() {
		return dateFormatStyle;
	}

	public String getProjectTypeName() {
		return projectTypeName;
	}

	public String getProjectPathName() {
		return projectPathName;
	}

	public String getMavenProjectJavaPathName() {
		return mavenProjectJavaPathName;
	}

	public String getMavenProjectResourcesPathName() {
		return mavenProjectResourcesPathName;
	}

	public String getMavenProjectWebappPathName() {
		return mavenProjectWebappPathName;
	}

	public String getEclipseProjectSrcPathName() {
		return eclipseProjectSrcPathName;
	}

	public String getEclipseProjectWebContentPathName() {
		return eclipseProjectWebContentPathName;
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

}
