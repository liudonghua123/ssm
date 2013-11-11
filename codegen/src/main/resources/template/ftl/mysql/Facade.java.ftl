package ${servicePackageName};

/**
 * @author Jin,QingHua
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface Facade {

<#list domainClassNameList as domainClassName>
	${domainClassName}Service get${domainClassName}Service();

</#list>
}
