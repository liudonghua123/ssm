package ssm.codegen.util;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;

import ssm.codegen.domain.MyType;

public class TypeUtils {

	public static final MyType getType(int dataType, int columnSize, int decimalDigits) {
		MyType type = new MyType();
		switch (dataType) {
		case Types.ARRAY:
			type.setJdbcTypeName("ARRAY");
			type.setJavaClassName(Object.class.getName());
			break;

		case Types.BIGINT:
			type.setJdbcTypeName("BIGINT");
			type.setJavaClassName(Long.class.getName());
			break;

		case Types.BINARY:
			type.setJdbcTypeName("BINARY");
			type.setJavaClassName("byte[]");
			break;

		case Types.BIT:
			type.setJdbcTypeName("BIT");
			type.setJavaClassName(Boolean.class.getName());
			break;

		case Types.BLOB:
			type.setJdbcTypeName("BLOB");
			type.setJavaClassName("byte[]");
			break;

		case Types.BOOLEAN:
			type.setJdbcTypeName("BOOLEAN");
			type.setJavaClassName(Boolean.class.getName());
			break;

		case Types.CHAR:
			type.setJdbcTypeName("CHAR");
			type.setJavaClassName(String.class.getName());
			break;

		case Types.CLOB:
			type.setJdbcTypeName("CLOB");
			type.setJavaClassName(String.class.getName());
			break;

		case Types.DATALINK:
			type.setJdbcTypeName("DATALINK");
			type.setJavaClassName(Object.class.getName());
			break;

		case Types.DATE:
			type.setJdbcTypeName("DATE");
			type.setJavaClassName(Date.class.getName());
			break;

		/*
		 * @see http://blog.csdn.net/kimsoft/article/details/8824448
		 */
		case Types.DECIMAL:
			type.setJdbcTypeName("DECIMAL");
			if (decimalDigits > 0 || columnSize > 20) {
				type.setJavaClassName(BigDecimal.class.getName());
			} else if (columnSize > 10) {
				type.setJavaClassName(Long.class.getName());
				// } else if (columnSize > 4) {
				// type.setJavaClassName(Integer.class.getName());
				// } else {
				// type.setJavaClassName(Short.class.getName());
				// }
			} else {
				type.setJavaClassName(Integer.class.getName());
			}
			break;

		case Types.DISTINCT:
			type.setJdbcTypeName("DISTINCT");
			type.setJavaClassName(Object.class.getName());
			break;

		case Types.DOUBLE:
			type.setJdbcTypeName("DOUBLE");
			type.setJavaClassName(Double.class.getName());
			break;

		case Types.FLOAT:
			type.setJdbcTypeName("FLOAT");
			type.setJavaClassName(Double.class.getName());
			break;

		case Types.INTEGER:
			type.setJdbcTypeName("INTEGER");
			type.setJavaClassName(Integer.class.getName());
			break;

		case Types.JAVA_OBJECT:
			type.setJdbcTypeName("JAVA_OBJECT");
			type.setJavaClassName(Object.class.getName());
			break;

		case Types.LONGVARBINARY:
			type.setJdbcTypeName("LONGVARBINARY");
			type.setJavaClassName("byte[]");
			break;

		case Types.LONGVARCHAR:
			type.setJdbcTypeName("LONGVARCHAR");
			type.setJavaClassName(String.class.getName());
			break;

		case Types.NULL:
			type.setJdbcTypeName("NULL");
			type.setJavaClassName(Object.class.getName());
			break;

		case Types.NUMERIC:
			type.setJdbcTypeName("NUMERIC");
			if (decimalDigits > 0 || columnSize > 20) {
				type.setJavaClassName(BigDecimal.class.getName());
			} else if (columnSize > 10) {
				type.setJavaClassName(Long.class.getName());
				// } else if (columnSize > 4) {
				// type.setJavaClassName(Integer.class.getName());
				// } else {
				// type.setJavaClassName(Short.class.getName());
				// }
			} else {
				type.setJavaClassName(Integer.class.getName());
			}
			break;

		case Types.OTHER:
			type.setJdbcTypeName("OTHER");
			type.setJavaClassName(Object.class.getName());
			break;

		case Types.REAL:
			type.setJdbcTypeName("REAL");
			type.setJavaClassName(Float.class.getName());
			break;

		case Types.REF:
			type.setJdbcTypeName("REF");
			type.setJavaClassName(Object.class.getName());
			break;

		case Types.SMALLINT:
			type.setJdbcTypeName("SMALLINT");
			type.setJavaClassName(Short.class.getName());
			break;

		case Types.STRUCT:
			type.setJdbcTypeName("STRUCT");
			type.setJavaClassName(Object.class.getName());
			break;

		case Types.TIME:
			type.setJdbcTypeName("TIME");
			type.setJavaClassName(Date.class.getName());
			break;

		case Types.TIMESTAMP:
			type.setJdbcTypeName("TIMESTAMP");
			type.setJavaClassName(Date.class.getName());
			break;

		case Types.TINYINT:
			type.setJdbcTypeName("TINYINT");
			type.setJavaClassName(Byte.class.getName());
			break;

		case Types.VARBINARY:
			type.setJdbcTypeName("VARBINARY");
			type.setJavaClassName("byte[]");
			break;

		case Types.VARCHAR:
			type.setJdbcTypeName("VARCHAR");
			type.setJavaClassName(String.class.getName());
			break;

		default:
			return null;
			// throw new UnsupportedDataTypeException();
		}
		return type;
	}
}
