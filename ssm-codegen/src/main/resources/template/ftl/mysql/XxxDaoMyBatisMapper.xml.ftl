<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- @author ${cfg.author} -->
<!-- @version ${now?string("yyyy-MM-dd HH:mm:ss")} -->
<mapper namespace="${table.domainClassName}">

	<!--cache /-->

	<resultMap id="${table.domainClassName?uncap_first}ResultForList" type="${table.domainClassName?uncap_first}">
		<#list table.columns as column>
		<result column="${column.columnName}" property="${column.javaPropertyName}" jdbcType="${column.jdbcTypeName}" />
		</#list>
	</resultMap>

	<resultMap id="${table.domainClassName?uncap_first}Result" type="${table.domainClassName?uncap_first}" extends="${table.domainClassName?uncap_first}ResultForList">
	</resultMap>

	<sql id="sf-columns">
		<trim suffixOverrides=",">
		<#list table.columns as column>
			${column.columnName},
		</#list>
		</trim>
	</sql>
	
	<sql id="sf-${table.domainClassName?uncap_first}">
		<#list table.columns as column>
		<if test="@ssm.core.Ognl@isNotEmpty(${column.javaPropertyName})"> and ${column.columnName} = ${r"#"}{${column.javaPropertyName}:${column.jdbcTypeName}}</if>
		</#list>
	</sql>

	<select id="select${table.domainClassName}" resultMap="${table.domainClassName?uncap_first}Result" parameterType="${table.domainClassName?uncap_first}">
		select <include refid="sf-columns" /> from ${table.tableName}
		<where>
			<include refid="sf-${table.domainClassName?uncap_first}" />
		</where>
	</select>

	<select id="select${table.domainClassName}List" resultMap="${table.domainClassName?uncap_first}ResultForList" parameterType="${table.domainClassName?uncap_first}">
		select <include refid="sf-columns" /> from ${table.tableName}
		<where>
			<include refid="sf-${table.domainClassName?uncap_first}" />
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

	<select id="select${table.domainClassName}Count" resultType="long" parameterType="${table.domainClassName?uncap_first}">
		select count(*) from ${table.tableName}
		<where>
			<include refid="sf-${table.domainClassName?uncap_first}" />
		</where>
	</select>

	<insert id="insert${table.domainClassName}" parameterType="${table.domainClassName?uncap_first}" useGeneratedKeys="true" keyProperty="id">
		<![CDATA[insert into ${table.tableName} ]]>
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list table.columns as column>
			<if test="null != ${column.javaPropertyName}">${column.columnName},</if>
			</#list>
		</trim>
		<![CDATA[ values ]]>
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list table.columns as column>
			<if test="null != ${column.javaPropertyName}">${r"#"}{${column.javaPropertyName}:${column.jdbcTypeName}},</if>
			</#list>
		</trim>
		<!--selectKey resultType="int" keyProperty="id">SELECT LAST_INSERT_ID()</selectKey-->
	</insert>

	<update id="update${table.domainClassName}" parameterType="${table.domainClassName?uncap_first}">
		update ${table.tableName}
		<set>
			<#list table.columns as column>
				<#if !column.primaryKey>
			<if test="null != ${column.javaPropertyName}">${column.columnName} = ${r"#"}{${column.javaPropertyName}:${column.jdbcTypeName}},</if>
				</#if>
			</#list>
		</set>
		where ID = ${r"#"}{id:BIGINT}
	</update>

	<delete id="delete${table.domainClassName}" parameterType="${table.domainClassName?uncap_first}">
		<if test="_map._isLogicalDelete == null">update ${table.tableName} set IS_DEL = 1 </if>
		<if test="_map._isLogicalDelete != null">delete from ${table.tableName} </if>
		<where>
			<if test="_map._isLogicalDelete == null"> and IS_LOCK = 0 </if>
			<if test="_map._ids == null">
				<include refid="sf-${table.domainClassName?uncap_first}" />
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