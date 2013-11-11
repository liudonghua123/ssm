package ${daoJdbcImplPackageName};

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import ${daoPackageName}.${domainClassName}Dao;
import ${domainPackageName}.${domainClassName};

/**
 * @author Jin,QingHua
 * @version ${now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Repository
public class ${domainClassName}DaoJdbcImpl extends NamedParameterJdbcDaoSupport implements ${domainClassName}Dao {

	ParameterizedRowMapper<${domainClassName}> rowMapperForList = new ParameterizedRowMapper<${domainClassName}>() {
		public ${domainClassName} mapRow(ResultSet rs, int rowNum) throws SQLException {
			${domainClassName} ${domainClassName?uncap_first} = new ${domainClassName}();
<#list columnList as column>
			${domainClassName?uncap_first}.set${column.propertyName?cap_first}(rs.get<#if column.javaClassSimpleName?starts_with("Int")>Int<#else>${column.javaClassSimpleName}</#if>("${column.columnName}"));
</#list>
			return ${domainClassName?uncap_first};
		}
	};

	protected String getConditionSql(${domainClassName} t) {
		StringBuffer sb = new StringBuffer();	
		//like CONCAT('%', :contact, '%')"
		
<#list columnList as column>	
		if (null != t.get${column.propertyName?cap_first}() <#if column.javaClassSimpleName == "String">&& 0 != t.get${column.propertyName?cap_first}().length()</#if>) {
			sb.append(" and ${column.propertyName}=:${column.propertyName}");
		}
</#list>
		return sb.toString();
	}

	public int deleteEntity(${domainClassName} t) throws DataAccessException {
		if (null != t.getId()) {// deleteById
			return super.getNamedParameterJdbcTemplate().getJdbcOperations().update("delete from ${tableName} where id=?", t.getId());
		} else {// deleteByIds
			String[] ids = (String[]) t.getMap().get("ids");
			if (null != ids) {
				return super.getNamedParameterJdbcTemplate().getJdbcOperations().update(
						"delete from ${tableName} where id in (" + StringUtils.join(ids, ",") + ")");
			} else {
				return 0;
			}
		}
	}

	public Integer insertEntity(${domainClassName} t) throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ${tableName} ");
		sb.append("<#list columnList as column> ${column.propertyName}<#if column_has_next>,</#if></#list>");
		sb.append(" values ");
		sb.append("<#list columnList as column>:${column.propertyName}<#if column_has_next>,</#if></#list>");
		return super.getNamedParameterJdbcTemplate().getJdbcOperations().update(sb.toString(), new BeanPropertySqlParameterSource(t));
	}

	public ${domainClassName} selectEntity(${domainClassName} t) throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ${tableName} where 1=1 ");
		sb.append(this.getConditionSql(t));
		List<${domainClassName}> entityList = super.getNamedParameterJdbcTemplate().getJdbcOperations().query(sb.toString(),
				ParameterizedBeanPropertyRowMapper.newInstance(${domainClassName}.class), new BeanPropertySqlParameterSource(t));
		if (null == entityList || 0 == entityList.size()) {
			return null;
		}
		if (1 == entityList.size()) {
			return entityList.get(0);
		} else {
			throw new IncorrectResultSizeDataAccessException(null, 1, entityList.size());
		}
	}

	public Integer selectEntityCount(${domainClassName} t) throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from ${tableName} where 1=1");
		sb.append(this.getConditionSql(t));
		return super.getNamedParameterJdbcTemplate().getJdbcOperations().queryForInt(sb.toString(), new BeanPropertySqlParameterSource(t));
	}

	public List<${domainClassName}> selectEntityList(${domainClassName} t) throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ${tableName} where 1=1");
		sb.append(this.getConditionSql(t));
		//sb.append(" order by id desc");
		return super.getNamedParameterJdbcTemplate().getJdbcOperations().query(sb.toString(), rowMapperForList,
				new BeanPropertySqlParameterSource(t));
	}

	public List<${domainClassName}> selectEntityPaginatedList(${domainClassName} t) throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ${tableName} where 1=1");
		sb.append(this.getConditionSql(t));
		//sb.append(" order by id desc");
		sb.append(" limit ");
		sb.append(t.getRow().getFirst());
		sb.append(",");
		sb.append(t.getRow().getCount());
		return super.getNamedParameterJdbcTemplate().getJdbcOperations().query(sb.toString(), rowMapperForList,
				new BeanPropertySqlParameterSource(t));
	}

	public int updateEntity(${domainClassName} t) throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		
<#list columnList as column>	
		if (null != t.get${column.propertyName?cap_first}() <#if column.javaClassSimpleName == "String">&& 0 != t.get${column.propertyName?cap_first}().length()</#if>) {
			sb.append("${column.propertyName}=:${column.propertyName},");
		}
</#list>

		String sqlCondition = sb.toString();
		if (sqlCondition.endsWith(",")) {
			sqlCondition = sqlCondition.substring(0, sqlCondition.length() - 1);
		}
		String sql = "update ${tableName} set " + sqlCondition + " where id=:id";
		return super.getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(t));
	}

}
