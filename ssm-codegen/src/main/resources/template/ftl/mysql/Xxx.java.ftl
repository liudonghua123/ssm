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
	private ${column.javaClassSimpleName} ${column.propertyName};

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
     <#assign params = params + column.javaClassSimpleName + " " + column.propertyName>
	 <#assign first = false>
  </#if>
</#list>
	public ${table.domainClassName}(${params}) {
<#list table.columns as column>
	<#if column.primaryKey>	
		this.${column.propertyName} = ${column.propertyName};
	</#if>
</#list>
	}
	
	// ------------------------------User Custom Constructors-------------------

	// ==============================Getter and Setters=========================
<#list table.columns as column>
	public ${column.javaClassSimpleName} get${column.propertyName?cap_first}() {
		return ${column.propertyName};
	}

	public void set${column.propertyName?cap_first}(${column.javaClassSimpleName} ${column.propertyName}) {
		this.${column.propertyName} = ${column.propertyName};
	}

</#list>

	// ------------------------------User Custom Getter and Setters-------------
	
}