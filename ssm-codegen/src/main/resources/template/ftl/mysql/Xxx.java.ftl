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

<#list table.columns as column>
	<#if (column.columnComments)??>
	/**
	 * ${(column.columnComments)!""}
	 */
	</#if>
	private ${column.javaClassSimpleName} ${column.propertyName};

</#list>

	public ${table.domainClassName}() {

	}

<#list table.columns as column>
	public ${column.javaClassSimpleName} get${column.propertyName?cap_first}() {
		return ${column.propertyName};
	}

	public void set${column.propertyName?cap_first}(${column.javaClassSimpleName} ${column.propertyName}) {
		this.${column.propertyName} = ${column.propertyName};
	}
	
</#list>
}