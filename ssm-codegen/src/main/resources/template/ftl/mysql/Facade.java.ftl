package ${servicePackageName};

/**
 * @author ${author}
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface Facade {

<#list domainClassNames as domainClassName>
	${domainClassName}Service get${domainClassName}Service();

</#list>
}
