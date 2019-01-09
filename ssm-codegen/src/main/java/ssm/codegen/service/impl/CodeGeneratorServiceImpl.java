package ssm.codegen.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import ssm.codegen.config.CodeGeneratorConfig;
import ssm.codegen.domain.Table;
import ssm.codegen.service.CodeGeneratorService;
import ssm.codegen.service.DatabaseMetaDataService;
import ssm.core.service.TemplateService;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月29日 下午9:32:37
 */
@SuppressWarnings("ALL")
@Slf4j
@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    public static final String TABLES = "tables";
    public static final String NOW = "now";
    public static final String CONFIG = "codeGeneratorConfig";
    public static final String TABLE = "table";
    public static final String UUID = "uuid";

    @Resource
    private DatabaseMetaDataService databaseMetaDataService;

    @Resource
    private TemplateService templateService;

    @Resource(name = "codeGeneratorConfig")
    private CodeGeneratorConfig codeGeneratorConfig;

    private Map<String, Object> model;

    public CodeGeneratorServiceImpl() {
        // Do nothing
    }

    private void prepareDataModel() {
        if (null != model) { // 保证只执行一次
            return;
        }

        model = new HashMap<>();

        model.put(NOW, new Date());
        model.put(CONFIG, codeGeneratorConfig);
        model.put(TABLES, databaseMetaDataService.getTables());
    }

    private void generateFile(File file, String context, boolean overwrite) throws IOException {
        if (!overwrite && file.exists()) {
            log.warn("The file [{}] is already exists!", file.getAbsolutePath());
            return;
        }
        FileUtils.writeStringToFile(file, context, StandardCharsets.UTF_8);
    }

    private String convertFtlToFile(String ftl, String className) {
        return ftl.replace(CLASS_NAME_HOLDER, className).replace(FTL_EXTENSION, "");
    }

    public void generateMyBatisConfigFile() throws IOException {
        this.generateMyBatisConfigFile(false);
    }

    public void generateMyBatisConfigFile(boolean overwrite) throws IOException {
        this.prepareDataModel();

        File file = new File(codeGeneratorConfig.getProjectResourcePath(), convertFtlToFile(FTL_MYBATIS_CONFIG, ""));
        String content = templateService.getContent(FTL_MYBATIS_CONFIG, model);
        this.generateFile(file, content, true);
    }

    public void generateDomainFiles() throws IOException {
        this.prepareDataModel();

        List<Table> tables = (List<Table>) model.get(TABLES);
        for (Table table : tables) {
            model.put(TABLE, table);
            model.put(UUID, java.util.UUID.randomUUID().getLeastSignificantBits());// 为domain类增加serialVersionUID
            File file = new File(codeGeneratorConfig.convertPackageNameToPath(codeGeneratorConfig.getDomainPackageName()), convertFtlToFile(FTL_DOMAIN,
                    table.getDomainClassName()));
            String content = templateService.getContent(FTL_DOMAIN, model);
            this.generateFile(file, content, true);

            file = new File(codeGeneratorConfig.convertPackageNameToPath(codeGeneratorConfig.getDomainPackageName() + ".table"), convertFtlToFile(
                    FTL_DOMAIN_TABLE, table.getDomainClassName()));
            content = templateService.getContent(FTL_DOMAIN_TABLE, model);
            this.generateFile(file, content, false);
        }
    }

    public void generateDaoFiles() throws IOException {
        this.prepareDataModel();

        List<Table> tables = (List<Table>) model.get(TABLES);
        for (Table table : tables) {
            model.put(TABLE, table);

            File file = new File(codeGeneratorConfig.convertPackageNameToPath(codeGeneratorConfig.getDaoPackageName()), convertFtlToFile(FTL_DAO,
                    table.getDomainClassName()));
            String content = templateService.getContent(FTL_DAO, model);
            this.generateFile(file, content, false);
        }
    }

    public void generateDaoSqlSessionImplFiles() throws IOException {
        this.prepareDataModel();

        List<Table> tables = (List<Table>) model.get(TABLES);
        for (Table table : tables) {
            model.put(TABLE, table);

            File file = new File(codeGeneratorConfig.convertPackageNameToPath(codeGeneratorConfig.getDaoSqlSessionImplPackageName()), convertFtlToFile(
                    FTL_DAO_SQLSESSION_IMPL, table.getDomainClassName()));
            String content = templateService.getContent(FTL_DAO_SQLSESSION_IMPL, model);
            this.generateFile(file, content, false);
        }
    }

    public void generateDaoJdbcImplFiles() throws IOException {
        this.prepareDataModel();

        List<Table> tables = (List<Table>) model.get(TABLES);
        for (Table table : tables) {
            model.put(TABLE, table);

            File file = new File(codeGeneratorConfig.convertPackageNameToPath(codeGeneratorConfig.getDaoJdbcImplPackageName()), convertFtlToFile(
                    FTL_DAO_JDBC_IMPL, table.getDomainClassName()));
            String content = templateService.getContent(FTL_DAO_JDBC_IMPL, model);
            this.generateFile(file, content, false);
        }
    }

    public void generateDaoMyBatisMapperFiles() throws IOException {
        this.prepareDataModel();

        List<Table> tables = (List<Table>) model.get(TABLES);
        for (Table table : tables) {
            model.put(TABLE, table);

            File file = new File(codeGeneratorConfig.convertPackageNameToPath(codeGeneratorConfig.getProjectResourcePath(),
                    codeGeneratorConfig.getDaoMyBatisMapperPackageName()), convertFtlToFile(FTL_DAO_MYBATIS_MAPPER,
                    table.getDomainClassName()));
            String content = templateService.getContent(
                    codeGeneratorConfig.getFtlFilePathName(databaseMetaDataService.getJdbcUrl(), FTL_DAO_MYBATIS_MAPPER), model);
            this.generateFile(file, content, false);
        }
    }

    public void generateServiceFiles() throws IOException {
        this.prepareDataModel();

        List<Table> tables = (List<Table>) model.get(TABLES);
        for (Table table : tables) {
            model.put(TABLE, table);

            File file = new File(codeGeneratorConfig.convertPackageNameToPath(codeGeneratorConfig.getServicePackageName()), convertFtlToFile(
                    FTL_SERVICE, table.getDomainClassName()));
            String content = templateService.getContent(FTL_SERVICE, model);
            this.generateFile(file, content, false);
        }
    }

    public void generateServiceImplFiles() throws IOException {
        this.prepareDataModel();

        List<Table> tables = (List<Table>) model.get(TABLES);
        for (Table table : tables) {
            model.put(TABLE, table);

            File file = new File(codeGeneratorConfig.convertPackageNameToPath(codeGeneratorConfig.getServiceImplPackageName()), convertFtlToFile(
                    FTL_SERVICE_IMPL, table.getDomainClassName()));
            String content = templateService.getContent(FTL_SERVICE_IMPL, model);
            this.generateFile(file, content, false);
        }

    }

    public void generateFacadeFile() throws IOException {
        generateFacadeFile(false);
    }

    public void generateFacadeFile(boolean overwrite) throws IOException {
        this.prepareDataModel();

        File file = new File(codeGeneratorConfig.convertPackageNameToPath(codeGeneratorConfig.getFacadePackageName()), convertFtlToFile(FTL_FACADE, ""));
        String content = templateService.getContent(FTL_FACADE, model);
        this.generateFile(file, content, false);

    }

    public void generateFacadeImplFile() throws IOException {
        generateFacadeImplFile(false);
    }

    public void generateFacadeImplFile(boolean overwrite) throws IOException {
        this.prepareDataModel();

        File file = new File(codeGeneratorConfig.convertPackageNameToPath(codeGeneratorConfig.getFacadeImplPackageName()), convertFtlToFile(
                FTL_FACADE_IMPL, ""));
        String content = templateService.getContent(FTL_FACADE_IMPL, model);
        this.generateFile(file, content, false);
    }
}
