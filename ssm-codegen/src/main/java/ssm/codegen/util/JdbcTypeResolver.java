package ssm.codegen.util;

/*
 *  Copyright 2005 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Jeff Butler
 * @see https
 *      ://github.com/mybatis/generator/blob/master/core/mybatis-generator-core/src/main/java/org/mybatis/generator/
 *      internal/types/JavaTypeResolverDefaultImpl.java
 */
public class JdbcTypeResolver {

	protected static Map<Integer, JdbcTypeInformation> typeMap;

	static {
		typeMap = new HashMap<Integer, JdbcTypeInformation>();

		typeMap.put(Types.ARRAY, new JdbcTypeInformation("ARRAY", new String(Object.class.getName())));
		typeMap.put(Types.BIGINT, new JdbcTypeInformation("BIGINT", new String(Long.class.getName())));
		typeMap.put(Types.BINARY, new JdbcTypeInformation("BINARY", new String("byte[]")));
		typeMap.put(Types.BIT, new JdbcTypeInformation("BIT", new String(Boolean.class.getName())));
		typeMap.put(Types.BLOB, new JdbcTypeInformation("BLOB", new String("byte[]")));
		typeMap.put(Types.BOOLEAN, new JdbcTypeInformation("BOOLEAN", new String(Boolean.class.getName())));
		typeMap.put(Types.CHAR, new JdbcTypeInformation("CHAR", new String(String.class.getName())));
		typeMap.put(Types.CLOB, new JdbcTypeInformation("CLOB", new String(String.class.getName())));
		typeMap.put(Types.DATALINK, new JdbcTypeInformation("DATALINK", new String(Object.class.getName())));
		typeMap.put(Types.DATE, new JdbcTypeInformation("DATE", new String(Date.class.getName())));
		typeMap.put(Types.DISTINCT, new JdbcTypeInformation("DISTINCT", new String(Object.class.getName())));
		typeMap.put(Types.DOUBLE, new JdbcTypeInformation("DOUBLE", new String(Double.class.getName())));
		typeMap.put(Types.FLOAT, new JdbcTypeInformation("FLOAT", new String(Double.class.getName())));
		typeMap.put(Types.INTEGER, new JdbcTypeInformation("INTEGER", new String(Integer.class.getName())));
		typeMap.put(Types.JAVA_OBJECT, new JdbcTypeInformation("JAVA_OBJECT", new String(Object.class.getName())));
		typeMap.put(Types.LONGNVARCHAR, new JdbcTypeInformation("LONGNVARCHAR", new String(String.class.getName())));
		typeMap.put(Types.LONGVARBINARY, new JdbcTypeInformation("LONGVARBINARY", new String("byte[]")));
		typeMap.put(Types.LONGVARCHAR, new JdbcTypeInformation("LONGVARCHAR", new String(String.class.getName())));
		typeMap.put(Types.NCHAR, new JdbcTypeInformation("NCHAR", new String(String.class.getName())));
		typeMap.put(Types.NCLOB, new JdbcTypeInformation("NCLOB", new String(String.class.getName())));
		typeMap.put(Types.NVARCHAR, new JdbcTypeInformation("NVARCHAR", new String(String.class.getName())));
		typeMap.put(Types.NULL, new JdbcTypeInformation("NULL", new String(Object.class.getName())));
		typeMap.put(Types.OTHER, new JdbcTypeInformation("OTHER", new String(Object.class.getName())));
		typeMap.put(Types.REAL, new JdbcTypeInformation("REAL", new String(Float.class.getName())));
		typeMap.put(Types.REF, new JdbcTypeInformation("REF", new String(Object.class.getName())));
		typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT", new String(Short.class.getName())));
		typeMap.put(Types.STRUCT, new JdbcTypeInformation("STRUCT", new String(Object.class.getName())));
		typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", new String(Date.class.getName())));
		typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", new String(Date.class.getName())));
		typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", new String(Byte.class.getName())));
		typeMap.put(Types.VARBINARY, new JdbcTypeInformation("VARBINARY", new String("byte[]")));
		typeMap.put(Types.VARCHAR, new JdbcTypeInformation("VARCHAR", new String(String.class.getName())));
	}

	public static JdbcTypeInformation getJdbcTypeInfomation(int jdbcType, int columnSize, int decimalDigits) {
		JdbcTypeInformation jdbcTypeInformation = new JdbcTypeInformation();
		jdbcTypeInformation.setJavaClassName(calculateJavaType(jdbcType, columnSize, decimalDigits));
		jdbcTypeInformation.setJdbcTypeName(calculateJdbcTypeName(jdbcType));
		return jdbcTypeInformation;
	}

	public static String calculateJavaType(int jdbcType, int columnSize, int decimalDigits) {
		String answer;
		JdbcTypeInformation jdbcTypeInformation = typeMap.get(jdbcType);

		if (jdbcTypeInformation == null) {
			switch (jdbcType) {
			case Types.DECIMAL:
			case Types.NUMERIC:
				if (decimalDigits > 0 || columnSize > 20) {// 原来是18
					answer = new String(BigDecimal.class.getName());
				} else if (columnSize > 9) {
					answer = new String(Long.class.getName());
				} else if (columnSize > 4) {
					answer = new String(Integer.class.getName());
				} else {
					answer = new String(Short.class.getName());
				}
				break;

			default:
				answer = null;
				break;
			}
		} else {
			answer = jdbcTypeInformation.getJavaClassName();
		}
		return answer;
	}

	public static String calculateJdbcTypeName(int jdbcType) {
		String answer;
		JdbcTypeInformation jdbcTypeInformation = typeMap.get(jdbcType);

		if (jdbcTypeInformation == null) {
			switch (jdbcType) {
			case Types.DECIMAL:
				answer = "DECIMAL";
				break;
			case Types.NUMERIC:
				answer = "NUMERIC";
				break;
			default:
				answer = null;
				break;
			}
		} else {
			answer = jdbcTypeInformation.getJdbcTypeName();
		}
		return answer;
	}

	public static class JdbcTypeInformation {
		private String jdbcTypeName;
		private String javaClassName;
		private String javaClassSimpleName;

		public JdbcTypeInformation() {

		}

		public JdbcTypeInformation(String jdbcTypeName, String javaClassName) {
			this.jdbcTypeName = jdbcTypeName;
			this.javaClassName = javaClassName;
		}

		public String getJdbcTypeName() {
			return jdbcTypeName;
		}

		public String getJavaClassName() {
			return javaClassName;
		}

		public void setJdbcTypeName(String jdbcTypeName) {
			this.jdbcTypeName = jdbcTypeName;
		}

		public void setJavaClassName(String javaClassName) {
			this.javaClassName = javaClassName;
		}

		public String getJavaClassSimpleName() {
			if (null == this.javaClassSimpleName || this.javaClassSimpleName.length() == 0) {
				return StringUtils.substringAfterLast(this.javaClassName, ".");
			} else {
				return javaClassSimpleName;
			}
		}
	}
}
