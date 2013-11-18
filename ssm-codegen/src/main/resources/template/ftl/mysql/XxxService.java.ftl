package ${cfg.servicePackageName};

import java.util.List;

import ${cfg.domainPackageName}.${table.domainClassName};

/**
 * @author ${cfg.author}
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${table.domainClassName}Service {

	Long create${table.domainClassName}(${table.domainClassName} t);

	int modify${table.domainClassName}(${table.domainClassName} t);

	int remove${table.domainClassName}(${table.domainClassName} t);

	${table.domainClassName} get${table.domainClassName}(${table.domainClassName} t);

	List<${table.domainClassName}> get${table.domainClassName}List(${table.domainClassName} t);

	Long get${table.domainClassName}Count(${table.domainClassName} t);

	<#--List<${table.domainClassName}> get${table.domainClassName}PaginatedList(${table.domainClassName} t);-->

}
