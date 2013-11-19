package ${cfg.domainPackageName};

import ${cfg.domainPackageName}.table.${table.domainClassName}Table;
import ${cfg.baseDomainClassName};

/**
 * @author ${cfg.author}
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 * Class to be custom...
 */
public class ${table.domainClassName} extends ${table.domainClassName}Table {

	private static final long serialVersionUID = ${uuid?c}L;

	// ------------------------------User Custom Fields------------------------
	
	
	// ==============================Constructors===============================
	public ${table.domainClassName}() {
		super();
	}

<#assign params = "">
<#assign paramsSuper = "">	
<#assign first = true>
<#list table.columns as column>	
	<#if column.primaryKey>
	<#if !first><#assign params = params + ", "><#assign paramsSuper = paramsSuper + ", "></#if>
     <#assign params = params + column.javaClassSimpleName + " " + column.javaPropertyName>
     <#assign paramsSuper = paramsSuper + column.javaPropertyName>
	 <#assign first = false>
  </#if>
</#list>
	public ${table.domainClassName}(${params}) {
		super(${paramsSuper});
	}

	// ------------------------------User Custom Constructors-------------------
	

	// ------------------------------User Custom Getter and Setters-------------
	
}