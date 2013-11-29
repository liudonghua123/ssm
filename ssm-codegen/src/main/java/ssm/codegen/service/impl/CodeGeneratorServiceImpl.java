package ssm.codegen.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ssm.codegen.config.CodeGeneratorConfig;
import ssm.codegen.domain.Table;
import ssm.codegen.service.CodeGeneratorService;
import ssm.codegen.service.DatabaseMetaDataService;
import ssm.core.service.TemplateService;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月29日 下午9:32:37
 */
@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private DatabaseMetaDataService databaseMetaDataService;

	@Resource
	private TemplateService templateService;

	@Resource(name = "codeGeneratorConfig")
	private CodeGeneratorConfig cfg;

	private static Map<String, Object> model;

	public CodeGeneratorServiceImpl() {
	}

	private void prepareDataModel() {
		if (null != model) {// 保证只执行一次
			return;
		}

		model = new HashMap<String, Object>();

		model.put("now", new Date());
		model.put("cfg", cfg);
		model.put("tables", databaseMetaDataService.getTables());
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
	public void generateMyBatisConfigFile(boolean overwrite) throws IOException {
		this.prepareDataModel();

		File file = new File(cfg.getProjectResourcePath(), convertFtlToFile(FTL_MYBATIS_CONFIG, ""));
		String content = templateService.getContent(FTL_MYBATIS_CONFIG, model);
		this.generateFile(file, content, true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void generateDomainFiles() throws IOException {
		this.prepareDataModel();

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);
			model.put("uuid", UUID.randomUUID().getLeastSignificantBits());// 为domain类增加serialVersionUID
			File file = new File(cfg.convertPackageNameToPath(cfg.getDomainPackageName()), convertFtlToFile(FTL_DOMAIN,
					table.getDomainClassName()));
			String content = templateService.getContent(FTL_DOMAIN, model);
			this.generateFile(file, content, true);

			file = new File(cfg.convertPackageNameToPath(cfg.getDomainPackageName() + ".table"), convertFtlToFile(
					FTL_DOMAIN_TABLE, table.getDomainClassName()));
			content = templateService.getContent(FTL_DOMAIN_TABLE, model);
			this.generateFile(file, content, false);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void generateDaoFiles() throws IOException {
		this.prepareDataModel();

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);

			File file = new File(cfg.convertPackageNameToPath(cfg.getDaoPackageName()), convertFtlToFile(FTL_DAO,
					table.getDomainClassName()));
			String content = templateService.getContent(FTL_DAO, model);
			this.generateFile(file, content, false);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void generateDaoSqlSessionImplFiles() throws IOException {
		this.prepareDataModel();

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);

			File file = new File(cfg.convertPackageNameToPath(cfg.getDaoSqlSessionImplPackageName()), convertFtlToFile(
					FTL_DAO_SQLSESSION_IMPL, table.getDomainClassName()));
			String content = templateService.getContent(FTL_DAO_SQLSESSION_IMPL, model);
			this.generateFile(file, content, false);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void generateDaoJdbcImplFiles() throws IOException {
		this.prepareDataModel();

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);

			File file = new File(cfg.convertPackageNameToPath(cfg.getDaoJdbcImplPackageName()), convertFtlToFile(
					FTL_DAO_JDBC_IMPL, table.getDomainClassName()));
			String content = templateService.getContent(FTL_DAO_JDBC_IMPL, model);
			this.generateFile(file, content, false);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void generateDaoMyBatisMapperFiles() throws IOException {
		this.prepareDataModel();

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);

			File file = new File(cfg.convertPackageNameToPath(cfg.getProjectResourcePath(),
					cfg.getDaoMyBatisMapperPackageName()), convertFtlToFile(FTL_DAO_MYBATIS_MAPPER,
					table.getDomainClassName()));
			String content = templateService.getContent(cfg.getFtlFilePathName(FTL_DAO_MYBATIS_MAPPER), model);
			this.generateFile(file, content, false);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void generateServiceFiles() throws IOException {
		this.prepareDataModel();

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);

			File file = new File(cfg.convertPackageNameToPath(cfg.getServicePackageName()), convertFtlToFile(
					FTL_SERVICE, table.getDomainClassName()));
			String content = templateService.getContent(FTL_SERVICE, model);
			this.generateFile(file, content, false);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void generateServiceImplFiles() throws IOException {
		this.prepareDataModel();

		List<Table> tables = (List<Table>) model.get("tables");
		for (Table table : tables) {
			model.put("table", table);

			File file = new File(cfg.convertPackageNameToPath(cfg.getServiceImplPackageName()), convertFtlToFile(
					FTL_SERVICE_IMPL, table.getDomainClassName()));
			String content = templateService.getContent(FTL_SERVICE_IMPL, model);
			this.generateFile(file, content, false);
		}

	}

	@Override
	public void generateFacadeFile() throws IOException {
		generateFacadeFile(false);
	}

	@Override
	public void generateFacadeFile(boolean overwrite) throws IOException {
		this.prepareDataModel();

		File file = new File(cfg.convertPackageNameToPath(cfg.getFacadePackageName()), convertFtlToFile(FTL_FACADE, ""));
		String content = templateService.getContent(FTL_FACADE, model);
		this.generateFile(file, content, false);

	}

	@Override
	public void generateFacadeImplFile() throws IOException {
		generateFacadeImplFile(false);
	}

	@Override
	public void generateFacadeImplFile(boolean overwrite) throws IOException {
		this.prepareDataModel();

		File file = new File(cfg.convertPackageNameToPath(cfg.getFacadeImplPackageName()), convertFtlToFile(
				FTL_FACADE_IMPL, ""));
		String content = templateService.getContent(FTL_FACADE_IMPL, model);
		this.generateFile(file, content, false);
	}
}
