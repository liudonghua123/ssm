package ${serviceImplPackageName};

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ${servicePackageName}.Facade;
<#list domainClassNameList as domainClassName>
import ${servicePackageName}.${domainClassName}Service;
</#list>

import ${ssmPackageName}.service.TemplateService;
import ${ssmPackageName}.service.impl.BaseFacadeImpl;

/**
 * @author Jin,QingHua
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Component("facade")
public class FacadeImpl extends BaseFacadeImpl implements Facade {

<#list domainClassNameList as domainClassName>

	@Resource
	private ${domainClassName}Service ${domainClassName?uncap_first}Service;
</#list>

<#list domainClassNameList as domainClassName>

	public ${domainClassName}Service get${domainClassName}Service() {
		return ${domainClassName?uncap_first}Service;
	}

</#list>
}
