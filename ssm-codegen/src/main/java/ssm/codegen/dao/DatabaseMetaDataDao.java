package ssm.codegen.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import ssm.codegen.domain.Column;
import ssm.codegen.domain.PrimaryKey;
import ssm.codegen.domain.Table;

/**
 * 数据库元数据信息
 * 
 * @author Jin,QingHua
 * @version build 2008.12
 */
public interface DatabaseMetaDataDao {

	String selectSchemaPattern() throws DataAccessException;

	List<Table> selectTables(String schemaPattern) throws DataAccessException;

	List<PrimaryKey> selectPrimaryKeys(String schemaPattern, String tableName) throws DataAccessException;

	List<Column> selectColumns(String schemaPattern) throws DataAccessException;
}
