package ssm.codegen.dao;

import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import ssm.codegen.domain.Table;

/**
 * 数据库元数据信息
 * 
 * @author Jin,QingHua
 * @version build 2008.12
 */
public interface DatabaseMetaDataDao {

	/**
	 * 取得表名列表
	 * 
	 * @return 表名集合（List）
	 * @throws DataAccessException
	 */
	Set<Table> selectTables() throws DataAccessException;

	/**
	 * 取得表名列表
	 * 
	 * @return 表名集合（List）
	 * @throws DataAccessException
	 */
	Set<String> selectTableNames() throws DataAccessException;

	/**
	 * 取得表名列表
	 * 
	 * @param isIncludeAllTables
	 *            是否有表名条件
	 * @return 表名集合（List）
	 * @throws DataAccessException
	 */
	Set<String> selectTableNames(boolean isIncludeAllTables) throws DataAccessException;

	/**
	 * 根据表名获取列名列表
	 * 
	 * @param tableName
	 *            表名
	 * @return 列名集合（List）
	 * @throws DataAccessException
	 */
	Set<Map<String, Object>> selectColumnNames(String tableName) throws DataAccessException;

}
