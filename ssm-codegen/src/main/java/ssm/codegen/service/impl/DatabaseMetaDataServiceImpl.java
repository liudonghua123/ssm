/**
 * 
 */
package ssm.codegen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ssm.codegen.dao.DatabaseMetaDataDao;
import ssm.codegen.domain.Column;
import ssm.codegen.domain.PrimaryKey;
import ssm.codegen.domain.Table;
import ssm.codegen.service.DatabaseMetaDataService;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月18日 上午11:06:31
 */
@Service
public class DatabaseMetaDataServiceImpl implements DatabaseMetaDataService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private DatabaseMetaDataDao databaseMetaDataDao;

	@Override
	public String gettJdbcUrl() {
		return databaseMetaDataDao.selectJdbcUrl();
	}

	@Override
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
		logger.debug("----->tables:={}", tables.toString());
		return tables;
	}

}
