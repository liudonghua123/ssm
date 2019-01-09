package ssm.codegen.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jeff Butler
 * @see <a href="https://github.com/mybatis/generator/blob/master/core/mybatis-generator-core/src/main/java/org/mybatis/generator/internal/types/JavaTypeResolverDefaultImpl.java">JavaTypeResolver</a>
 */
public class JdbcTypeResolver {

    private JdbcTypeResolver() {
        throw new UnsupportedOperationException();
    }

    private static final Map<Integer, JdbcTypeInformation> typeMap;

    private static final String BYTE_ARRAY = "byte[]";

    static {
        typeMap = new HashMap<>();

        typeMap.put(Types.ARRAY, new JdbcTypeInformation("ARRAY", Object.class.getName()));
        typeMap.put(Types.BIGINT, new JdbcTypeInformation("BIGINT", Long.class.getName()));
        typeMap.put(Types.BINARY, new JdbcTypeInformation("BINARY", BYTE_ARRAY));
        typeMap.put(Types.BIT, new JdbcTypeInformation("BIT", Boolean.class.getName()));
        typeMap.put(Types.BLOB, new JdbcTypeInformation("BLOB", BYTE_ARRAY));
        typeMap.put(Types.BOOLEAN, new JdbcTypeInformation("BOOLEAN", Boolean.class.getName()));
        typeMap.put(Types.CHAR, new JdbcTypeInformation("CHAR", String.class.getName()));
        typeMap.put(Types.CLOB, new JdbcTypeInformation("CLOB", String.class.getName()));
        typeMap.put(Types.DATALINK, new JdbcTypeInformation("DATALINK", Object.class.getName()));
        typeMap.put(Types.DATE, new JdbcTypeInformation("DATE", Date.class.getName()));
        typeMap.put(Types.DISTINCT, new JdbcTypeInformation("DISTINCT", Object.class.getName()));
        typeMap.put(Types.DOUBLE, new JdbcTypeInformation("DOUBLE", Double.class.getName()));
        typeMap.put(Types.FLOAT, new JdbcTypeInformation("FLOAT", Double.class.getName()));
        typeMap.put(Types.INTEGER, new JdbcTypeInformation("INTEGER", Integer.class.getName()));
        typeMap.put(Types.JAVA_OBJECT, new JdbcTypeInformation("JAVA_OBJECT", Object.class.getName()));
        typeMap.put(Types.LONGNVARCHAR, new JdbcTypeInformation("LONGNVARCHAR", String.class.getName()));
        typeMap.put(Types.LONGVARBINARY, new JdbcTypeInformation("LONGVARBINARY", BYTE_ARRAY));
        typeMap.put(Types.LONGVARCHAR, new JdbcTypeInformation("LONGVARCHAR", String.class.getName()));
        typeMap.put(Types.NCHAR, new JdbcTypeInformation("NCHAR", String.class.getName()));
        typeMap.put(Types.NCLOB, new JdbcTypeInformation("NCLOB", String.class.getName()));
        typeMap.put(Types.NVARCHAR, new JdbcTypeInformation("NVARCHAR", String.class.getName()));
        typeMap.put(Types.NULL, new JdbcTypeInformation("NULL", Object.class.getName()));
        typeMap.put(Types.OTHER, new JdbcTypeInformation("OTHER", Object.class.getName()));
        typeMap.put(Types.REAL, new JdbcTypeInformation("REAL", Float.class.getName()));
        typeMap.put(Types.REF, new JdbcTypeInformation("REF", Object.class.getName()));
        typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT", Short.class.getName()));
        typeMap.put(Types.STRUCT, new JdbcTypeInformation("STRUCT", Object.class.getName()));
        typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", Date.class.getName()));
        typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", Date.class.getName()));
        typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", Byte.class.getName()));
        typeMap.put(Types.VARBINARY, new JdbcTypeInformation("VARBINARY", BYTE_ARRAY));
        typeMap.put(Types.VARCHAR, new JdbcTypeInformation("VARCHAR", String.class.getName()));
    }

    public static JdbcTypeInformation getJdbcTypeInformation(int jdbcType, int columnSize, int decimalDigits) {
        JdbcTypeInformation jdbcTypeInformation = new JdbcTypeInformation();
        jdbcTypeInformation.setJavaClassName(calculateJavaType(jdbcType, columnSize, decimalDigits));
        jdbcTypeInformation.setJdbcTypeName(calculateJdbcTypeName(jdbcType));
        return jdbcTypeInformation;
    }

    private static String calculateJavaType(int jdbcType, int columnSize, int decimalDigits) {
        String answer;
        JdbcTypeInformation jdbcTypeInformation = typeMap.get(jdbcType);

        if (jdbcTypeInformation == null) {
            switch (jdbcType) {
                case Types.DECIMAL:
                case Types.NUMERIC:
                    if (decimalDigits > 0 || columnSize > 20) {// 原来是18
                        answer = BigDecimal.class.getName();
                    } else if (columnSize > 9) {
                        answer = Long.class.getName();
                    } else if (columnSize > 4) {
                        answer = Integer.class.getName();
                    } else {
                        answer = Short.class.getName();
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

    private static String calculateJdbcTypeName(int jdbcType) {
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

        JdbcTypeInformation() {

        }

        JdbcTypeInformation(String jdbcTypeName, String javaClassName) {
            this.jdbcTypeName = jdbcTypeName;
            this.javaClassName = javaClassName;
        }

        public String getJdbcTypeName() {
            return jdbcTypeName;
        }

        public String getJavaClassName() {
            return javaClassName;
        }

        void setJdbcTypeName(String jdbcTypeName) {
            this.jdbcTypeName = jdbcTypeName;
        }

        void setJavaClassName(String javaClassName) {
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
