package ${cfg.servicePackageName};

/**
 * @author ${cfg.author}
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface Facade {

<#list tables as table>
	${table.domainClassName}Service get${table.domainClassName}Service();

</#list>
}
