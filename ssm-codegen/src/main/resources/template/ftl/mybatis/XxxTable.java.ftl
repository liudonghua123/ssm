package ${cfg.domainPackageName}.table;

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
 * Class created from table
 */
public class ${table.domainClassName}Table extends BaseDomain {

	private static final long serialVersionUID = ${uuid?c}L;
	
<#list table.columns as column>
	<#if (column.remarks)??>
	/**
	 * ${(column.remarks)!""}
	 */
	</#if>
	private ${column.javaClassSimpleName} ${column.javaPropertyName};

</#list>
	
	// ==============================Constructors===============================
	public ${table.domainClassName}Table() {

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
	public ${table.domainClassName}Table(${params}) {
<#list table.columns as column>
	<#if column.primaryKey>	
		this.${column.javaPropertyName} = ${column.javaPropertyName};
	</#if>
</#list>
	}
	
	// ==============================Getter and Setters=========================
<#list table.columns as column>
	public ${column.javaClassSimpleName} get${column.javaPropertyName?cap_first}() {
		return ${column.javaPropertyName};
	}

	public void set${column.javaPropertyName?cap_first}(${column.javaClassSimpleName} ${column.javaPropertyName}) {
		this.${column.javaPropertyName} = ${column.javaPropertyName};
	}

</#list>
}