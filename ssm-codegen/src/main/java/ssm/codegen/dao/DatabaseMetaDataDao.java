package ssm.codegen.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

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
	List<String> selectTableNameList() throws DataAccessException;

	/**
	 * 取得表名列表
	 * 
	 * @param isIncludeAllTables
	 *            是否有表名条件
	 * @return 表名集合（List）
	 * @throws DataAccessException
	 */
	List<String> selectTableNameList(boolean isIncludeAllTables) throws DataAccessException;

	/**
	 * 根据表名获取列名列表
	 * 
	 * @param tableName
	 *            表名
	 * @return 列名集合（List）
	 * @throws DataAccessException
	 */
	List<Map<String, Object>> selectColumnNameList(String tableName) throws DataAccessException;

}
