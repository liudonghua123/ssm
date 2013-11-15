package ssm.codegen.dao;

import java.util.List;

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
	 * 取得表集合
	 * 
	 * @return 表集合（List）
	 * @throws DataAccessException
	 */
	List<Table> selectTables() throws DataAccessException;
}
