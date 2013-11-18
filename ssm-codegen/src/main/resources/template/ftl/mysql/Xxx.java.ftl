package ${cfg.domainPackageName};

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;

import ${cfg.baseDomainClassName};

/**
 * @author ${cfg.author}
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
public class ${table.domainClassName} extends BaseDomain<${table.domainClassName}> implements Serializable {

	private static final long serialVersionUID = -1L;

	// ==============================Fields=====================================
<#list table.columns as column>
	<#if (column.remarks)??>
	/**
	 * ${(column.remarks)!""}
	 */
	</#if>
	private ${column.javaClassSimpleName} ${column.javaPropertyName};

</#list>
	// ------------------------------User Custom Fields------------------------
	
	// ==============================Constructors===============================
	public ${table.domainClassName}() {

	}

<#assign params = "">	
<#assign first = true>
<#list table.columns as column>	
	<#if column.primaryKey>
	<#if !first><#assign params = params + ", "></#if>
     <#assign params = params + column.javaClassSimpleName + " " + column.javaPropertyName>
	 <#assign first = false>
  </#if>
</#list>
	public ${table.domainClassName}(${params}) {
<#list table.columns as column>
	<#if column.primaryKey>	
		this.${column.javaPropertyName} = ${column.javaPropertyName};
	</#if>
</#list>
	}
	
	// ------------------------------User Custom Constructors-------------------

	// ==============================Getter and Setters=========================
<#list table.columns as column>
	public ${column.javaClassSimpleName} get${column.javaPropertyName?cap_first}() {
		return ${column.javaPropertyName};
	}

	public void set${column.javaPropertyName?cap_first}(${column.javaClassSimpleName} ${column.javaPropertyName}) {
		this.${column.javaPropertyName} = ${column.javaPropertyName};
	}

</#list>

	// ------------------------------User Custom Getter and Setters-------------
	
}