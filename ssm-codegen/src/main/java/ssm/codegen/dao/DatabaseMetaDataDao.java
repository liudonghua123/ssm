package ssm.codegen.dao;

import ssm.codegen.domain.Column;
import ssm.codegen.domain.PrimaryKey;
import ssm.codegen.domain.Table;

import java.util.List;

/**
 * 数据库元数据信息
 *
 * @author Jin, QingHua
 * @version build 2008.12
 */
public interface DatabaseMetaDataDao {

    String selectJdbcUrl();

    String selectSchemaPattern();

    List<Table> selectTables(String schemaPattern);

    List<PrimaryKey> selectPrimaryKeys(String schemaPattern, String tableName);

    List<Column> selectColumns(String schemaPattern);
}
