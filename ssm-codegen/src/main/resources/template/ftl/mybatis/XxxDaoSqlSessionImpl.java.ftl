package ${cfg.daoSqlSessionImplPackageName};

import org.springframework.stereotype.Repository;

import ${cfg.ssmPackageName}.dao.impl.EntityDaoSqlSessionImpl;
import ${cfg.daoPackageName}.${table.domainClassName}Dao;
import ${cfg.domainPackageName}.${table.domainClassName};

/**
 * @author ${cfg.author}
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Repository
public class ${table.domainClassName}DaoSqlSessionImpl extends EntityDaoSqlSessionImpl<${table.domainClassName}> implements ${table.domainClassName}Dao {

}

