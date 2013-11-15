package ${serviceImplPackageName};

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ${daoPackageName}.${table.domainClassName}Dao;
import ${domainPackageName}.${table.domainClassName};
import ${servicePackageName}.${table.domainClassName}Service;

/**
 * @author ${author}
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Service
public class ${table.domainClassName}ServiceImpl implements ${table.domainClassName}Service {

	@Resource
	private ${table.domainClassName}Dao ${table.domainClassName?uncap_first}Dao;
	
	<#--
	public void set${table.domainClassName}Dao(${table.domainClassName}Dao ${table.domainClassName?uncap_first}Dao) {
		this.${table.domainClassName?uncap_first}Dao = ${table.domainClassName?uncap_first}Dao;
	}
	-->
	@Override
	public Long create${table.domainClassName}(${table.domainClassName} t) {
		this.${table.domainClassName?uncap_first}Dao.insertEntity(t);
		return t.getId(); //注意MyBatis的insert和iBATIS不同！
	}
	
	@Override
	public int modify${table.domainClassName}(${table.domainClassName} t) {
		return this.${table.domainClassName?uncap_first}Dao.updateEntity(t);
	}

	@Override
	public int remove${table.domainClassName}(${table.domainClassName} t) {
		return this.${table.domainClassName?uncap_first}Dao.deleteEntity(t);
	}

	@Override
	public ${table.domainClassName} get${table.domainClassName}(${table.domainClassName} t) {
		return this.${table.domainClassName?uncap_first}Dao.selectEntity(t);
	}

	@Override
	public Long get${table.domainClassName}Count(${table.domainClassName} t) {
		return this.${table.domainClassName?uncap_first}Dao.selectEntityCount(t);
	}

	@Override
	public List<${table.domainClassName}> get${table.domainClassName}List(${table.domainClassName} t) {
		return this.${table.domainClassName?uncap_first}Dao.selectEntityList(t);
	}

	<#--
	public List<${table.domainClassName}> get${table.domainClassName}PaginatedList(${table.domainClassName} t) {
		return this.${table.domainClassName?uncap_first}Dao.selectEntityPaginatedList(t);
	}
	-->

}
