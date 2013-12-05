package ssm.codegen.service;

import java.util.List;

import ssm.codegen.domain.Table;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月18日 上午11:06:01
 */
public interface DatabaseMetaDataService {

	String gettJdbcUrl();

	List<Table> getTables();

}
