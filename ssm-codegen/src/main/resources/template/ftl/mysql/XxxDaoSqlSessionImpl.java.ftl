package ${daoSqlSessionImplPackageName};

import org.springframework.stereotype.Repository;

import ${ssmPackageName}.dao.impl.EntityDaoSqlSessionImpl;
import ${daoPackageName}.${domainClassName}Dao;
import ${domainPackageName}.${domainClassName};

/**
 * @author Jin,QingHua
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Repository
public class ${domainClassName}DaoSqlSessionImpl extends EntityDaoSqlSessionImpl<${domainClassName}> implements ${domainClassName}Dao {

}

