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
	public List<Table> getTables() {
		List<Table> tables = databaseMetaDataDao.selectTables();

		List<Column> columns = databaseMetaDataDao.selectColumns(null);
		for (Table table : tables) {
			List<PrimaryKey> primaryKeys = databaseMetaDataDao.selectPrimaryKeys(table.getTableSchem(),
					table.getTableName());
			table.setPrimaryKeys(primaryKeys);

			for (Column column : columns) {
				if (table.getTableName().equals(column.getTableName())) {
					table.getColumns().add(column);
				}
			}
		}
		logger.debug("tables:={}", tables.toString());
		return tables;
	}

}
