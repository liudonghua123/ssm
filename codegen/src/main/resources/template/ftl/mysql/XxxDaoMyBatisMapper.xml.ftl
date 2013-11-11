<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- MYSQL -->
<!-- @author Jin,QingHua -->
<!-- @version ${now?string("yyyy-MM-dd HH:mm:ss")} -->
<mapper namespace="${domainClassName}">

	<!--cache /-->

	<resultMap id="${domainClassName?uncap_first}ResultForList" type="${domainClassName?uncap_first}">
		<#list columnList as column>
		<result column="${column.columnName}" property="${column.propertyName}" jdbcType="${column.jdbcTypeName}" />
		</#list>
	</resultMap>

	<resultMap id="${domainClassName?uncap_first}Result" type="${domainClassName?uncap_first}" extends="${domainClassName?uncap_first}ResultForList">
	</resultMap>

	<sql id="sf-columnList">
		<trim suffixOverrides=",">
		<#list columnList as column>
			${column.columnName},
		</#list>
		</trim>
	</sql>
	
	<sql id="sf-${domainClassName?uncap_first}">
		<#list columnList as column>
		<if test="@ssm.core.Ognl@isNotEmpty(${column.propertyName})"> and ${column.columnName} = ${r"#"}{${column.propertyName}:${column.jdbcTypeName}}</if>
		</#list>
	</sql>

	<select id="select${domainClassName}" resultMap="${domainClassName?uncap_first}Result" parameterType="${domainClassName?uncap_first}">
		select <include refid="sf-columnList" /> from ${tableName}
		<where>
			<include refid="sf-${domainClassName?uncap_first}" />
		</where>
	</select>

	<select id="select${domainClassName}List" resultMap="${domainClassName?uncap_first}ResultForList" parameterType="${domainClassName?uncap_first}">
		select <include refid="sf-columnList" /> from ${tableName}
		<where>
			<include refid="sf-${domainClassName?uncap_first}" />
		</where>
		<!-- order by ID asc -->
		<if test="_rowBounds.limit != null">
			<if test="_rowBounds.offset != null">
				limit ${r"#"}{_rowBounds.offset}, ${r"#"}{_rowBounds.limit}
			</if>
			<if test="_rowBounds.offset == null">
				limit 0, ${r"#"}{_rowBounds.limit}
			</if>
		</if>
	</select>

	<select id="select${domainClassName}Count" resultType="long" parameterType="${domainClassName?uncap_first}">
		select count(*) from ${tableName}
		<where>
			<include refid="sf-${domainClassName?uncap_first}" />
		</where>
	</select>

	<insert id="insert${domainClassName}" parameterType="${domainClassName?uncap_first}" useGeneratedKeys="true" keyProperty="id">
		<![CDATA[insert into ${tableName} ]]>
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list columnList as column>
			<if test="@ssm.core.Ognl@isNotEmpty(${column.propertyName})">${column.columnName},</if>
			</#list>
		</trim>
		<![CDATA[ values ]]>
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list columnList as column>
			<if test="@ssm.core.Ognl@isNotEmpty(${column.propertyName})">${r"#"}{${column.propertyName}:${column.jdbcTypeName}},</if>
			</#list>
		</trim>
		<!--selectKey resultType="int" keyProperty="id">SELECT LAST_INSERT_ID()</selectKey-->
	</insert>

	<update id="update${domainClassName}" parameterType="${domainClassName?uncap_first}">
		update ${tableName}
		<set>
			<#list columnList as column>
			<if test="@ssm.core.Ognl@isNotEmpty(${column.propertyName})">${column.columnName} = ${r"#"}{${column.propertyName}:${column.jdbcTypeName}},</if>
			</#list>
		</set>
		where ID = ${r"#"}{id:BIGINT}
	</update>

	<delete id="delete${domainClassName}" parameterType="${domainClassName?uncap_first}">
		<if test="_map._isLogicalDelete == null">update ${tableName} set IS_DEL = 1 </if>
		<if test="_map._isLogicalDelete != null">delete from ${tableName} </if>
		<where>
			<if test="_map._isLogicalDelete == null"> and IS_LOCK = 0 </if>
			<if test="_map._ids == null">
				<include refid="sf-${domainClassName?uncap_first}" />
			</if>
			<if test="_map._ids != null">
				and ID in
				<foreach collection="_map._ids" item="item" open="(" separator="," close=")">
					${r"#"}{item}
				</foreach>
			</if>
		</where>
	</delete>

</mapper>