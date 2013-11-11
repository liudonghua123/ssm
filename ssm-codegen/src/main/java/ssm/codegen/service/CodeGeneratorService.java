package ssm.codegen.service;

import java.io.IOException;

/**
 * @author Jin,QingHua
 * @version build 2008.12
 */
public interface CodeGeneratorService {

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
