package ssm.codegen.service;

import java.io.IOException;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月29日 下午9:32:11
 */
public interface CodeGeneratorService {

    String CLASS_NAME_HOLDER = "Xxx";
    String FTL_EXTENSION = ".ftl";

    String FTL_MYBATIS_CONFIG = "mybatis-config.xml.ftl";
    String FTL_DOMAIN = "/Xxx.java.ftl";
    String FTL_DOMAIN_TABLE = "/XxxTable.java.ftl";
    String FTL_DAO = "XxxDao.java.ftl";
    String FTL_DAO_SQLSESSION_IMPL = "XxxDaoSqlSessionImpl.java.ftl";
    String FTL_DAO_JDBC_IMPL = "XxxDaoJdbcImpl.java.ftl";
    String FTL_DAO_MYBATIS_MAPPER = "XxxMapper.xml.ftl";
    String FTL_SERVICE = "XxxService.java.ftl";
    String FTL_SERVICE_IMPL = "XxxServiceImpl.java.ftl";
    String FTL_FACADE = "Facade.java.ftl";
    String FTL_FACADE_IMPL = "FacadeImpl.java.ftl";

    /**
     * 生成 mybatis-config 文件
     */
    void generateMyBatisConfigFile() throws IOException;

    /**
     * 生成 mybatis-config 文件
     *
     * @param isIncludeAllTables 是否包含所有的表
     */
    void generateMyBatisConfigFile(boolean isIncludeAllTables) throws IOException;

    /**
     * 生成 sqlmap 文件
     */
    void generateDaoMyBatisMapperFiles() throws IOException;

    /**
     * 生成 domain 文件
     */
    void generateDomainFiles() throws IOException;

    /**
     * 生成 dao 接口文件
     */
    void generateDaoFiles() throws IOException;

    /**
     * 生成 dao 接口 mybatis 实现文件
     */
    void generateDaoSqlSessionImplFiles() throws IOException;

    /**
     * 生成 dao 接口 jdbc 实现文件
     */
    void generateDaoJdbcImplFiles() throws IOException;

    /**
     * 生成 service 接口文件
     */
    void generateServiceFiles() throws IOException;

    /**
     * 生成 service 接口实现文件
     */
    void generateServiceImplFiles() throws IOException;

    /**
     * 生成 facade 接口文件
     */
    void generateFacadeFile() throws IOException;

    /**
     * 生成 facade 接口文件
     *
     * @param isIncludeAllTables 是否包含所有的表
     */
    void generateFacadeFile(boolean isIncludeAllTables) throws IOException;

    /**
     * 生成 facade 接口实现文件
     */
    void generateFacadeImplFile() throws IOException;

    /**
     * 生成 facade 接口实现文件
     *
     * @param isIncludeAllTables 是否包含所有的表
     */
    void generateFacadeImplFile(boolean isIncludeAllTables) throws IOException;

}
