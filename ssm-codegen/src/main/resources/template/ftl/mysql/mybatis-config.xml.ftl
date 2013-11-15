<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!-- @author ${author} -->
<!-- @version ${now?string("yyyy-MM-dd HH:mm:ss")} -->
<configuration>

	<!--
	Content Model : (
	properties?, 
	settings?, 
	typeAliases?, 
 	typeHandlers?, 
 	objectFactory?, 
 	objectWrapperFactory?, 
 	plugins?, 
 	environments?, 
 	databaseIdProvider?, 
 	mappers?)
 	-->

	<!-- http://www.mybatis.org/core/zh/configuration.html#settings -->
	<settings>
		<setting name="cacheEnabled" value="false" />
		<setting name="lazyLoadingEnabled" value="false" />
		<setting name="aggressiveLazyLoading" value="true" />
		<setting name="multipleResultSetsEnabled" value="true" />
		<setting name="useColumnLabel" value="true" />
		<setting name="useGeneratedKeys" value="false" />
		<setting name="autoMappingBehavior" value="PARTIAL" />
		<setting name="defaultExecutorType" value="SIMPLE" />
		<setting name="defaultStatementTimeout" value="25000" />
		<setting name="safeRowBoundsEnabled" value="false" />
		<setting name="mapUnderscoreToCamelCase" value="false" />
		<setting name="localCacheScope" value="SESSION" />
		<setting name="jdbcTypeForNull" value="OTHER" />
		<setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString" />
	</settings>
 	
 	<!-- http://www.mybatis.org/core/zh/configuration.html#typeAliases -->
 	<!--
	<typeAliases>
		<package name="cn.ligoo.part.domain" />
	</typeAliases>
	-->

	<databaseIdProvider type="VENDOR">
		<property name="SQL Server" value="sqlserver" />
		<property name="DB2" value="db2" />
		<property name="Oracle" value="oracle" />
		<property name="MySQL" value="mysql" />
	</databaseIdProvider>

	<!-- http://www.mybatis.org/core/zh/configuration.html#mappers -->
	<!-- 
	<mappers>
<#list tables as table>
		<mapper resource="${projectPackageName?replace(".", "/")}/dao/mybatis/mapper/${table.domainClassName}Mapper.xml"/>
</#list>
	</mappers>
	-->

</configuration>    