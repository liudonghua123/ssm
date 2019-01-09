package ssm.codegen.service;

import ssm.codegen.domain.Table;

import java.util.List;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月18日 上午11:06:01
 */
public interface DatabaseMetaDataService {

    String getJdbcUrl();

    List<Table> getTables();

}
