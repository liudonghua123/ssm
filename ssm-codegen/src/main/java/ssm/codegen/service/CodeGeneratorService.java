package ssm.codegen.service;

import java.io.IOException;

/**
 * @author Jin,QingHua
 * @version build 2008.12
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
	 * 生成 mybatis-conifg 文件
	 * 
	 * @throws IOException
	 */
	void generateMyBatisConfigFile() throws IOException;

	/**
	 * 生成 mybatis-conifg 文件
	 * 
	 * @param isIncludeAllTables
	 *            是否包含所有的表
	 * @throws IOException
	 */
	void generateMyBatisConfigFile(boolean isIncludeAllTables) throws IOException;

	/**
	 * 生成 sqlmap 文件
	 * 
	 * @throws IOException
	 */
	void generateDaoMyBatisMapperFiles() throws IOException;

	/**
	 * 生成 domain 文件
	 * 
	 * @throws IOException
	 */
	void generateDomainFiles() throws IOException;

	/**
	 * 生成 dao 接口文件
	 * 
	 * @throws IOException
	 */
	void generateDaoFiles() throws IOException;

	/**
	 * 生成 dao 接口 mybatis 实现文件
	 * 
	 * @throws IOException
	 */
	void generateDaoSqlSessionImplFiles() throws IOException;

	/**
	 * 生成 dao 接口 jdbc 实现文件
	 * 
	 * @throws IOException
	 */
	void generateDaoJdbcImplFiles() throws IOException;

	/**
	 * 生成 service 接口文件
	 * 
	 * @throws IOException
	 */
	void generateServiceFiles() throws IOException;

	/**
	 * 生成 service 接口实现文件
	 * 
	 * @throws IOException
	 */
	void generateServiceImplFiles() throws IOException;

	/**
	 * 生成 facade 接口文件
	 * 
	 * @throws IOException
	 */
	void generateFacadeFile() throws IOException;

	/**
	 * 生成 facade 接口文件
	 * 
	 * @param isIncludeAllTables
	 *            是否包含所有的表
	 * @throws IOException
	 */
	void generateFacadeFile(boolean isIncludeAllTables) throws IOException;

	/**
	 * 生成 facade 接口实现文件
	 * 
	 * @throws IOException
	 */
	void generateFacadeImplFile() throws IOException;

	/**
	 * 生成 facade 接口实现文件
	 * 
	 * @param isIncludeAllTables
	 *            是否包含所有的表
	 * @throws IOException
	 */
	void generateFacadeImplFile(boolean isIncludeAllTables) throws IOException;

}
