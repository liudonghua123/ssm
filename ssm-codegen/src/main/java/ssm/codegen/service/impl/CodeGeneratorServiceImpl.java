package ssm.codegen.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ssm.codegen.config.CodeGeneratorConfig;
import ssm.codegen.dao.DatabaseMetaDataDao;
import ssm.codegen.domain.Table;
import ssm.codegen.service.CodeGeneratorService;
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

		model.put("author", cfg.getAuthor());
		model.put("dateFormatStyle", cfg.getDateFormatStyle());

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

	private String convertFtlToFile(String ftl, String className) {
		return ftl.replace(CLASS_NAME_HOLDER, className).replace(FTL_EXTENSION, "");
	}

	@Override
	public void generateMyBatisConfigFile() throws IOException {
		this.generateMyBatisConfigFile(false);
	}

	@Override
	public void generateMyBatisConfigFile(boolean isIncludeAllTables) throws IOException {
		if (null == model) {
			this.prepare();
		}

		String content = templateService.getContent(cfg.getDbmsFtlFilePathString(FTL_MYBATIS_CONFIG), model);
		this.generateFile(new File(cfg.getProjectSourcePath(), FTL_MYBATIS_CONFIG.replace(FTL_EXTENSION, "")), content,
				true);
	}

	@Override
	public void generateDomainFiles() throws IOException {
		if (null == model) {
			this.prepare();
		}

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);
			String content = templateService.getContent(cfg.getDbmsFtlFilePathString(FTL_DOMAIN), model);
			File file = new File(cfg.convertPackageToPath(cfg.getDomainPackageName()), convertFtlToFile(FTL_DOMAIN,
					table.getDomainClassName()));
			this.generateFile(file, content, false);
		}
	}

	@Override
	public void generateDaoFiles() throws IOException {
		if (null == model) {
			this.prepare();
		}

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);

			String content = templateService.getContent(cfg.getDbmsFtlFilePathString(FTL_DAO), model);
			File file = new File(cfg.convertPackageToPath(cfg.getDaoPackageName()), convertFtlToFile(FTL_DAO,
					table.getDomainClassName()));
			this.generateFile(file, content, false);
		}
	}

	@Override
	public void generateDaoSqlSessionImplFiles() throws IOException {
		if (null == model) {
			this.prepare();
		}

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);

			String content = templateService.getContent(cfg.getDbmsFtlFilePathString(FTL_DAO_SQLSESSION_IMPL), model);

			File file = new File(cfg.convertPackageToPath(cfg.getDaoSqlSessionImplPackageName()), convertFtlToFile(
					FTL_DAO_SQLSESSION_IMPL, table.getDomainClassName()));
			this.generateFile(file, content, false);
		}
	}

	@Override
	public void generateDaoJdbcImplFiles() throws IOException {
		if (null == model) {
			this.prepare();
		}

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);

			String content = templateService.getContent(cfg.getDbmsFtlFilePathString(FTL_DAO_JDBC_IMPL), model);

			File file = new File(cfg.convertPackageToPath(cfg.getDaoJdbcImplPackageName()), convertFtlToFile(
					FTL_DAO_JDBC_IMPL, table.getDomainClassName()));
			this.generateFile(file, content, false);
		}
	}

	@Override
	public void generateDaoMyBatisMapperFiles() throws IOException {
		if (null == model) {
			this.prepare();
		}

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);

			String content = templateService.getContent(cfg.getDbmsFtlFilePathString(FTL_DAO_MYBATIS_MAPPER), model);
			File file = new File(cfg.convertPackageToPath(cfg.getDaoMyBatisMapperPackageName()), convertFtlToFile(
					FTL_DAO_MYBATIS_MAPPER, table.getDomainClassName()));
			this.generateFile(file, content, false);
		}
	}

	@Override
	public void generateServiceFiles() throws IOException {
		if (null == model) {
			this.prepare();
		}

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);

			String content = templateService.getContent(cfg.getDbmsFtlFilePathString(FTL_SERVICE), model);

			File file = new File(cfg.convertPackageToPath(cfg.getServicePackageName()), convertFtlToFile(FTL_SERVICE,
					table.getDomainClassName()));
			this.generateFile(file, content, false);
		}
	}

	@Override
	public void generateServiceImplFiles() throws IOException {
		if (null == model) {
			this.prepare();
		}

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);

			String content = templateService.getContent(cfg.getDbmsFtlFilePathString(FTL_SERVICE_IMPL), model);

			File file = new File(cfg.convertPackageToPath(cfg.getServiceImplPackageName()), convertFtlToFile(
					FTL_SERVICE_IMPL, table.getDomainClassName()));
			this.generateFile(file, content, false);
		}

	}

	@Override
	public void generateFacadeFile() throws IOException {
		generateFacadeFile(false);
	}

	@Override
	public void generateFacadeFile(boolean isIncludeAllTables) throws IOException {
		if (null == model) {
			this.prepare();
		}

		List<Table> tables = (List<Table>) model.get("tables");

		List<String> domainClassNames = new ArrayList<String>();
		for (Table table : tables) {
			domainClassNames.add(table.getDomainClassName());
		}
		model.put("domainClassNames", domainClassNames);

		String content = templateService.getContent(cfg.getDbmsFtlFilePathString(FTL_FACADE), model);

		this.generateFile(
				new File(cfg.convertPackageToPath(cfg.getFacadePackageName()), convertFtlToFile(FTL_FACADE, "")),
				content, false);
	}

	@Override
	public void generateFacadeImplFile() throws IOException {
		generateFacadeImplFile(false);
	}

	@Override
	public void generateFacadeImplFile(boolean isIncludeAllTables) throws IOException {
		if (null == model) {
			this.prepare();
		}

		List<Table> tables = (List<Table>) model.get("tables");

		Set<String> domainClassNames = new HashSet<String>();
		for (Table table : tables) {
			domainClassNames.add(table.getDomainClassName());
		}
		model.put("domainClassNames", domainClassNames);

		String content = templateService.getContent(cfg.getDbmsFtlFilePathString(FTL_FACADE_IMPL), model);

		this.generateFile(
				new File(cfg.convertPackageToPath(cfg.getFacadeImplPackageName()),
						convertFtlToFile(FTL_FACADE_IMPL, "")), content, false);
	}
}
