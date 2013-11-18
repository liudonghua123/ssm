package ${cfg.daoPackageName};

import ${cfg.domainPackageName}.${table.domainClassName};
import ${cfg.ssmPackageName}.dao.EntityDao;

/**
 * @author ${cfg.author}
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${table.domainClassName}Dao extends EntityDao<${table.domainClassName}> {

}
