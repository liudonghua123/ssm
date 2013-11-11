package ${serviceImplPackageName};

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ${daoPackageName}.${domainClassName}Dao;
import ${domainPackageName}.${domainClassName};
import ${servicePackageName}.${domainClassName}Service;

/**
 * @author Jin,QingHua
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Service
public class ${domainClassName}ServiceImpl implements ${domainClassName}Service {

	@Resource
	private ${domainClassName}Dao ${domainClassName?uncap_first}Dao;
	
	<#--
	public void set${domainClassName}Dao(${domainClassName}Dao ${domainClassName?uncap_first}Dao) {
		this.${domainClassName?uncap_first}Dao = ${domainClassName?uncap_first}Dao;
	}
	-->
	@Override
	public Long create${domainClassName}(${domainClassName} t) {
		this.${domainClassName?uncap_first}Dao.insertEntity(t);
		return t.getId(); //注意MyBatis的insert和iBATIS不同！
	}
	
	@Override
	public int modify${domainClassName}(${domainClassName} t) {
		return this.${domainClassName?uncap_first}Dao.updateEntity(t);
	}

	@Override
	public int remove${domainClassName}(${domainClassName} t) {
		return this.${domainClassName?uncap_first}Dao.deleteEntity(t);
	}

	@Override
	public ${domainClassName} get${domainClassName}(${domainClassName} t) {
		return this.${domainClassName?uncap_first}Dao.selectEntity(t);
	}

	@Override
	public Long get${domainClassName}Count(${domainClassName} t) {
		return this.${domainClassName?uncap_first}Dao.selectEntityCount(t);
	}

	@Override
	public List<${domainClassName}> get${domainClassName}List(${domainClassName} t) {
		return this.${domainClassName?uncap_first}Dao.selectEntityList(t);
	}

	<#--
	public List<${domainClassName}> get${domainClassName}PaginatedList(${domainClassName} t) {
		return this.${domainClassName?uncap_first}Dao.selectEntityPaginatedList(t);
	}
	-->

}
