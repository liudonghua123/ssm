package ssm.codegen.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ssm.codegen.config.CodeGeneratorConfig;
import ssm.codegen.dao.DatabaseMetaDataDao;
import ssm.codegen.service.CodeGeneratorService;
import ssm.codegen.util.JavaBeansUtils;
import ssm.core.service.TemplateService;

/**
 * @author Jin,QingHua
 * @version build 2008.12
 */
@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

	private final Logger logger = LoggerFactory.getLogger(CodeGeneratorServiceImpl.class);

	@Resource
	private DatabaseMetaDataDao databaseMetaDataDao;

	@Resource
	private TemplateService templateService;

	@Resource(name = "codeGeneratorConfig")
	private CodeGeneratorConfig cfg;

	private static Map<String, Object> model;

	public CodeGeneratorServiceImpl() {
	}

	private void prepare() {
		model = new HashMap<String, Object>();
		model.put("now", new Date());
		model.put("ssmPackageName", cfg.getSsmPackageName());

		model.put("getProjectPathString", cfg.getProjectPathString());
		model.put("projectSourcePathString", cfg.getProjectSourcePathString());

		model.put("projectPackageName", cfg.getProjectPackageName());
		model.put("domainPackageName", cfg.getDomainPackageName());

		model.put("daoPackageName", cfg.getDaoPackageName());
		model.put("daoSqlSessionImplPackageName", cfg.getDaoSqlSessionImplPackageName());
		model.put("daoJdbcImplPackageName", cfg.getDaoJdbcImplPackageName());
		model.put("daoMyBatisMapperPackageName", cfg.getDaoMyBatisMapperPackageName());

		model.put("servicePackageName", cfg.getServicePackageName());
		model.put("serviceImplPackageName", cfg.getServiceImplPackageName());

		model.put("facadePackageName", cfg.getFacadePackageName());
		model.put("facadeImplPackageName", cfg.getFacadeImplPackageName());
		model.put("ssmPackageName", cfg.getSsmPackageName());

		model.put("baseDomainClassName", cfg.getBaseDomainClassName());
		
		model.put("tables", databaseMetaDataDao.selectTables());
	}

	private void generateFile(File file, String context, boolean overwrite) throws IOException {
		if (!overwrite && file.exists()) {
			logger.warn("The file [{}] is already exists!", file.getAbsolutePath());
			return;
		}
		FileUtils.writeStringToFile(file, context);
	}

	@Override
	public void generateMyBatisConfigFile() throws IOException {
		this.generateMyBatisConfigFile(false);
	}

	@Override
	public void generateMyBatisConfigFile(boolean isIncludeAllTables) throws IOException {
		this.prepare();

		Set<String> tableNames = databaseMetaDataDao.selectTableNames(isIncludeAllTables);
		model.put("tableNames", tableNames);

		Set<String> domainClassNames = new HashSet<String>();
		for (String tableName : tableNames) {
			String domainClassName = JavaBeansUtils.getCamelCaseString(tableName, true);
			domainClassNames.add(domainClassName);
		}
		model.put("domainClassNames", domainClassNames);

		String content = templateService.getContent(cfg.getDbmsFtlFilePathString("mybatis-config.xml.ftl"), model);
		this.generateFile(new File(cfg.getProjectSourcePath(), "mybatis-config.xml"), content, true);
	}

	@Override
	public void generateDomainFiles() throws IOException {
		this.prepare();

		Set<String> tableNames = databaseMetaDataDao.selectTableNames();
		model.put("tableNames", tableNames);

		for (String tableName : tableNames) {
			String domainClassName = JavaBeansUtils.getCamelCaseString(tableName, true);
			model.put("domainClassName", domainClassName);

			Set<Map<String, Object>> columnList = databaseMetaDataDao.selectColumnNames(tableName);
			model.put("columnList", columnList);

			String content = templateService.getContent(cfg.getDbmsFtlFilePathString("/Xxx.java.ftl"), model);
			File file = new File(cfg.convertPackageToPath(cfg.getDomainPackageName()), domainClassName.concat(".java"));
			this.generateFile(file, content, false);
		}
	}

	@Override
	public void generateDaoFiles() throws IOException {
		this.prepare();

		Set<String> tableNames = databaseMetaDataDao.selectTableNames();
		model.put("tableNames", tableNames);

		for (String tableName : tableNames) {
			String domainClassName = JavaBeansUtils.getCamelCaseString(tableName, true);
			model.put("domainClassName", domainClassName);

			String content = templateService.getContent(cfg.getDbmsFtlFilePathString("XxxDao.java.ftl"), model);

			File file = new File(cfg.convertPackageToPath(cfg.getDaoPackageName()), domainClassName.concat("Dao.java"));
			this.generateFile(file, content, false);
		}
	}

	@Override
	public void generateDaoSqlSessionImplFiles() throws IOException {
		this.prepare();

		Set<String> tableNames = databaseMetaDataDao.selectTableNames();
		model.put("tableNames", tableNames);

		for (String tableName : tableNames) {
			String domainClassName = JavaBeansUtils.getCamelCaseString(tableName, true);
			model.put("domainClassName", domainClassName);

			String content = templateService.getContent(cfg.getDbmsFtlFilePathString("XxxDaoSqlSessionImpl.java.ftl"),
					model);

			File file = new File(cfg.convertPackageToPath(cfg.getDaoSqlSessionImplPackageName()),
					domainClassName.concat("DaoSqlSessionImpl.java"));
			this.generateFile(file, content, false);
		}
	}

	@Override
	public void generateDaoJdbcImplFiles() throws IOException {
		this.prepare();

		Set<String> tableNames = databaseMetaDataDao.selectTableNames();
		model.put("tableNames", tableNames);

		for (String tableName : tableNames) {
			model.put("tableName", tableName);

			String domainClassName = JavaBeansUtils.getCamelCaseString(tableName, true);
			model.put("domainClassName", domainClassName);

			Set<Map<String, Object>> columnList = databaseMetaDataDao.selectColumnNames(tableName);
			model.put("columnList", columnList);

			String content = templateService.getContent(cfg.getDbmsFtlFilePathString("XxxDaoJdbcImpl.java.ftl"), model);

			File file = new File(cfg.convertPackageToPath(cfg.getDaoJdbcImplPackageName()),
					domainClassName.concat("DaoJdbcImpl.java"));
			this.generateFile(file, content, false);
		}
	}

	@Override
	public void generateFacadeFile() throws IOException {
		generateFacadeFile(false);
	}

	@Override
	public void generateFacadeFile(boolean isIncludeAllTables) throws IOException {
		this.prepare();

		Set<String> tableNames = databaseMetaDataDao.selectTableNames(isIncludeAllTables);
		model.put("tableNames", tableNames);

		Set<String> domainClassNames = new HashSet<String>();
		for (String tableName : tableNames) {
			String domainClassName = JavaBeansUtils.getCamelCaseString(tableName, true);
			domainClassNames.add(domainClassName);
		}
		model.put("domainClassNames", domainClassNames);

		String content = templateService.getContent(cfg.getDbmsFtlFilePathString("Facade.java.ftl"), model);

		this.generateFile(new File(cfg.convertPackageToPath(cfg.getFacadePackageName()), "Facade.java"), content, false);
	}

	@Override
	public void generateFacadeImplFile() throws IOException {
		generateFacadeImplFile(false);
	}

	@Override
	public void generateFacadeImplFile(boolean isIncludeAllTables) throws IOException {
		this.prepare();

		Set<String> tableNames = databaseMetaDataDao.selectTableNames(isIncludeAllTables);
		model.put("tableNames", tableNames);

		Set<String> domainClassNames = new HashSet<String>();
		for (String tableName : tableNames) {
			String domainClassName = JavaBeansUtils.getCamelCaseString(tableName, true);
			domainClassNames.add(domainClassName);
		}
		model.put("domainClassNames", domainClassNames);

		String content = templateService.getContent(cfg.getDbmsFtlFilePathString("FacadeImpl.java.ftl"), model);

		this.generateFile(new File(cfg.convertPackageToPath(cfg.getFacadeImplPackageName()), "FacadeImpl.java"),
				content, false);
	}

	@Override
	public void generateServiceFiles() throws IOException {
		this.prepare();

		Set<String> tableNames = databaseMetaDataDao.selectTableNames();
		model.put("tableNames", tableNames);

		for (String tableName : tableNames) {
			String domainClassName = JavaBeansUtils.getCamelCaseString(tableName, true);
			model.put("domainClassName", domainClassName);

			String content = templateService.getContent(cfg.getDbmsFtlFilePathString("XxxService.java.ftl"), model);

			File file = new File(cfg.convertPackageToPath(cfg.getServicePackageName()),
					domainClassName.concat("Service.java"));
			this.generateFile(file, content, false);
		}
	}

	@Override
	public void generateServiceImplFiles() throws IOException {
		this.prepare();

		Set<String> tableNames = databaseMetaDataDao.selectTableNames();
		model.put("tableNames", tableNames);

		for (String tableName : tableNames) {
			String domainClassName = JavaBeansUtils.getCamelCaseString(tableName, true);
			model.put("domainClassName", domainClassName);

			String content = templateService.getContent(cfg.getDbmsFtlFilePathString("XxxServiceImpl.java.ftl"), model);

			File file = new File(cfg.convertPackageToPath(cfg.getServiceImplPackageName()),
					domainClassName.concat("ServiceImpl.java"));
			this.generateFile(file, content, false);
		}

	}

	@Override
	public void generateDaoMyBatisMapperFiles() throws IOException {
		this.prepare();

		Set<String> tableNames = databaseMetaDataDao.selectTableNames();
		model.put("tableNames", tableNames);

		for (String tableName : tableNames) {
			String domainClassName = JavaBeansUtils.getCamelCaseString(tableName, true);
			model.put("domainClassName", domainClassName);

			model.put("tableName", tableName);

			Set<Map<String, Object>> columns = databaseMetaDataDao.selectColumnNames(tableName);
			model.put("columnList", columns);

			String content = templateService.getContent(cfg.getDbmsFtlFilePathString("XxxDaoMyBatisMapper.xml.ftl"),
					model);
			File file = new File(cfg.convertPackageToPath(cfg.getDaoMyBatisMapperPackageName()),
					domainClassName.concat("Mapper.xml"));
			this.generateFile(file, content, false);
		}
	}

}
