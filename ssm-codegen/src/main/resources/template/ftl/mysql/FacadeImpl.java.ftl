package ${cfg.serviceImplPackageName};

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ${cfg.servicePackageName}.Facade;
<#list tables as table>
import ${cfg.servicePackageName}.${table.domainClassName}Service;
</#list>

import ${cfg.ssmPackageName}.service.TemplateService;
import ${cfg.ssmPackageName}.service.impl.BaseFacadeImpl;

/**
 * @author ${cfg.author}
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Component("facade")
public class FacadeImpl extends BaseFacadeImpl implements Facade {

<#list tables as table>

	@Resource
	private ${table.domainClassName}Service ${table.domainClassName?uncap_first}Service;
</#list>

<#list tables as table>

	public ${table.domainClassName}Service get${table.domainClassName}Service() {
		return ${table.domainClassName?uncap_first}Service;
	}

</#list>
}
