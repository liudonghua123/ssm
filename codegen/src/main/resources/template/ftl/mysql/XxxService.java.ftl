package ${servicePackageName};

import java.util.List;

import ${domainPackageName}.${domainClassName};

/**
 * @author Jin,QingHua
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${domainClassName}Service {

	Long create${domainClassName}(${domainClassName} t);

	int modify${domainClassName}(${domainClassName} t);

	int remove${domainClassName}(${domainClassName} t);

	${domainClassName} get${domainClassName}(${domainClassName} t);

	List<${domainClassName}> get${domainClassName}List(${domainClassName} t);

	Long get${domainClassName}Count(${domainClassName} t);

	<#--List<${domainClassName}> get${domainClassName}PaginatedList(${domainClassName} t);-->

}
