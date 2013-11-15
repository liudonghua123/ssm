package ${daoSqlSessionImplPackageName};

import org.springframework.stereotype.Repository;

import ${ssmPackageName}.dao.impl.EntityDaoSqlSessionImpl;
import ${daoPackageName}.${table.domainClassName}Dao;
import ${domainPackageName}.${table.domainClassName};

/**
 * @author ${author}
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Repository
public class ${table.domainClassName}DaoSqlSessionImpl extends EntityDaoSqlSessionImpl<${table.domainClassName}> implements ${table.domainClassName}Dao {

}

