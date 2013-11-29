package ssm.codegen.config;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ssm.codegen.domain.type.DbType;
import ssm.codegen.domain.type.ProjectType;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月29日 下午9:25:18
 */
@Component
public class CodeGeneratorConfig {

	private final Logger logger = LoggerFactory.getLogger(CodeGeneratorConfig.class);

	// ==============================固定配置==============================
	private final String author = "jinqinghua@gmail.com";
	private final String dateFormatStyle = "yyyy-MM-dd HH:mm:ss";

	// ==============================项目类型=============================
	@Value("${cfg.type.project:Maven}")
	private String projectTypeName;// MAVEN|ECLIPSE

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${cfg.path.project}")
	private String projectPathName; // 项目根目录（生成文件的目录）

	// ==============================Maven项目相关配置==============================
	@Value("${cfg.path.project.maven.java}")
	private String mavenProjectJavaPathName;

	@Value("${cfg.path.project.maven.resources}")
	private String mavenProjectResourcesPathName;

	@Value("${cfg.path.project.maven.webapp}")
	private String mavenProjectWebappPathName;

	// ==============================Eclipse项目相关配置==============================
	@Value("${cfg.path.project.eclipse.src}")
	private String eclipseProjectSrcPathName;

	@Value("${cfg.path.project.eclipse.WebContent}")
	private String eclipseProjectWebContentPathName;

	// ==============================Package相关配置==============================
	@Value("${cfg.package.project}")
	private String projectPackageName; // 项目包名，一般是com.company.project

	@Value("${cfg.package.domain}")
	private String domainPackageName;

	@Value("${cfg.package.dao}")
	private String daoPackageName;

	@Value("${cfg.package.daoJdbcImpl}")
	private String daoJdbcImplPackageName;

	@Value("${cfg.package.daoSqlSessionImpl}")
	private String daoSqlSessionImplPackageName;

	@Value("${cfg.package.daoMyBatisMapper}")
	private String daoMyBatisMapperPackageName;

	@Value("${cfg.package.service}")
	private String servicePackageName;

	@Value("${cfg.package.serviceImpl}")
	private String serviceImplPackageName;

	@Value("${cfg.package.facade}")
	private String facadePackageName;

	@Value("${cfg.package.facadeImpl}")
	private String facadeImplPackageName;

	// ==============================SSM相关配置==============================
	@Value("${cfg.package.ssm}")
	private String ssmPackageName;

	@Value("${cfg.class.baseDomain}")
	private String baseDomainClassName;

	public CodeGeneratorConfig() {
		logger.debug("CodeGeneratorConfig Construct...");
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

	// ==============================Getters=========================
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
