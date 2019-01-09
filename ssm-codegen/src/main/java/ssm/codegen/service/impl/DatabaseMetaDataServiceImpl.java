package ssm.codegen.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ssm.codegen.dao.DatabaseMetaDataDao;
import ssm.codegen.domain.Column;
import ssm.codegen.domain.PrimaryKey;
import ssm.codegen.domain.Table;
import ssm.codegen.service.DatabaseMetaDataService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月18日 上午11:06:31
 */
@Slf4j
@Service
public class DatabaseMetaDataServiceImpl implements DatabaseMetaDataService {
    @Resource
    private DatabaseMetaDataDao databaseMetaDataDao;

    public String getJdbcUrl() {
        return databaseMetaDataDao.selectJdbcUrl();
    }

    public List<Table> getTables() {
        String schemaPattern = databaseMetaDataDao.selectSchemaPattern();
        List<Table> tables = databaseMetaDataDao.selectTables(schemaPattern);
        List<Column> columns = databaseMetaDataDao.selectColumns(schemaPattern);

        for (Table table : tables) {
            List<PrimaryKey> primaryKeys = databaseMetaDataDao.selectPrimaryKeys(table.getTableSchem(),
                    table.getTableName());

            for (Column column : columns) {
                for (PrimaryKey primaryKey : primaryKeys) {
                    if (primaryKey.getTableName().equalsIgnoreCase(column.getTableName())
                            && primaryKey.getColumnName().equalsIgnoreCase(column.getColumnName())) {
                        column.setPrimaryKey(true);
                    }
                }
                if (table.getTableName().equalsIgnoreCase(column.getTableName())) {
                    table.getColumns().add(column);
                }
            }
        }
        log.debug("----->tables:={}", tables);
        return tables;
    }

}
