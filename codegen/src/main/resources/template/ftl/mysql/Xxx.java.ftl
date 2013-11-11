package ${domainPackageName};

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;

import ${baseDomainClassName};

/**
 * @author Jin,QingHua
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
public class ${domainClassName} extends BaseDomain<${domainClassName}> implements Serializable {

	private static final long serialVersionUID = -1L;

<#list columnList as column>
	<#if (column.columnComments)??>
	/**
	 * ${(column.columnComments)!""}
	 */
	</#if>
	private ${column.javaClassSimpleName} ${column.propertyName};

</#list>

	public ${domainClassName}() {

	}

<#list columnList as column>
	public ${column.javaClassSimpleName} get${column.propertyName?cap_first}() {
		return ${column.propertyName};
	}

	public void set${column.propertyName?cap_first}(${column.javaClassSimpleName} ${column.propertyName}) {
		this.${column.propertyName} = ${column.propertyName};
	}
	
</#list>
}