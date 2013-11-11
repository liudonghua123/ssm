package ssm.codegen.util;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;

import ssm.codegen.domain.MyTypes;

public class TypeUtils {

	public static final MyTypes getTypes(int type, int columnSize, int decimalDigits) {
		MyTypes mts = new MyTypes();
		switch (type) {
		case Types.ARRAY:
			mts.setJdbcTypeName("ARRAY");
			mts.setJavaClassName(Object.class.getName());
			break;

		case Types.BIGINT:
			mts.setJdbcTypeName("BIGINT");
			mts.setJavaClassName(Long.class.getName());
			break;

		case Types.BINARY:
			mts.setJdbcTypeName("BINARY");
			mts.setJavaClassName("byte[]");
			break;

		case Types.BIT:
			mts.setJdbcTypeName("BIT");
			mts.setJavaClassName(Boolean.class.getName());
			break;

		case Types.BLOB:
			mts.setJdbcTypeName("BLOB");
			mts.setJavaClassName("byte[]");
			break;

		case Types.BOOLEAN:
			mts.setJdbcTypeName("BOOLEAN");
			mts.setJavaClassName(Boolean.class.getName());
			break;

		case Types.CHAR:
			mts.setJdbcTypeName("CHAR");
			mts.setJavaClassName(String.class.getName());
			break;

		case Types.CLOB:
			mts.setJdbcTypeName("CLOB");
			mts.setJavaClassName(String.class.getName());
			break;

		case Types.DATALINK:
			mts.setJdbcTypeName("DATALINK");
			mts.setJavaClassName(Object.class.getName());
			break;

		case Types.DATE:
			mts.setJdbcTypeName("DATE");
			mts.setJavaClassName(Date.class.getName());
			break;

		/*
		 * @see http://blog.csdn.net/kimsoft/article/details/8824448
		 */
		case Types.DECIMAL:
			mts.setJdbcTypeName("DECIMAL");
			if (decimalDigits > 0 || columnSize > 20) {
				mts.setJavaClassName(BigDecimal.class.getName());
			} else if (columnSize > 10) {
				mts.setJavaClassName(Long.class.getName());
				// } else if (columnSize > 4) {
				// mts.setJavaClassName(Integer.class.getName());
				// } else {
				// mts.setJavaClassName(Short.class.getName());
				// }
			} else {
				mts.setJavaClassName(Integer.class.getName());
			}
			break;

		case Types.DISTINCT:
			mts.setJdbcTypeName("DISTINCT");
			mts.setJavaClassName(Object.class.getName());
			break;

		case Types.DOUBLE:
			mts.setJdbcTypeName("DOUBLE");
			mts.setJavaClassName(Double.class.getName());
			break;

		case Types.FLOAT:
			mts.setJdbcTypeName("FLOAT");
			mts.setJavaClassName(Double.class.getName());
			break;

		case Types.INTEGER:
			mts.setJdbcTypeName("INTEGER");
			mts.setJavaClassName(Integer.class.getName());
			break;

		case Types.JAVA_OBJECT:
			mts.setJdbcTypeName("JAVA_OBJECT");
			mts.setJavaClassName(Object.class.getName());
			break;

		case Types.LONGVARBINARY:
			mts.setJdbcTypeName("LONGVARBINARY");
			mts.setJavaClassName("byte[]");
			break;

		case Types.LONGVARCHAR:
			mts.setJdbcTypeName("LONGVARCHAR");
			mts.setJavaClassName(String.class.getName());
			break;

		case Types.NULL:
			mts.setJdbcTypeName("NULL");
			mts.setJavaClassName(Object.class.getName());
			break;

		case Types.NUMERIC:
			mts.setJdbcTypeName("NUMERIC");
			if (decimalDigits > 0 || columnSize > 20) {
				mts.setJavaClassName(BigDecimal.class.getName());
			} else if (columnSize > 10) {
				mts.setJavaClassName(Long.class.getName());
				// } else if (columnSize > 4) {
				// mts.setJavaClassName(Integer.class.getName());
				// } else {
				// mts.setJavaClassName(Short.class.getName());
				// }
			} else {
				mts.setJavaClassName(Integer.class.getName());
			}
			break;

		case Types.OTHER:
			mts.setJdbcTypeName("OTHER");
			mts.setJavaClassName(Object.class.getName());
			break;

		case Types.REAL:
			mts.setJdbcTypeName("REAL");
			mts.setJavaClassName(Float.class.getName());
			break;

		case Types.REF:
			mts.setJdbcTypeName("REF");
			mts.setJavaClassName(Object.class.getName());
			break;

		case Types.SMALLINT:
			mts.setJdbcTypeName("SMALLINT");
			mts.setJavaClassName(Short.class.getName());
			break;

		case Types.STRUCT:
			mts.setJdbcTypeName("STRUCT");
			mts.setJavaClassName(Object.class.getName());
			break;

		case Types.TIME:
			mts.setJdbcTypeName("TIME");
			mts.setJavaClassName(Date.class.getName());
			break;

		case Types.TIMESTAMP:
			mts.setJdbcTypeName("TIMESTAMP");
			mts.setJavaClassName(Date.class.getName());
			break;

		case Types.TINYINT:
			mts.setJdbcTypeName("TINYINT");
			mts.setJavaClassName(Byte.class.getName());
			break;

		case Types.VARBINARY:
			mts.setJdbcTypeName("VARBINARY");
			mts.setJavaClassName("byte[]");
			break;

		case Types.VARCHAR:
			mts.setJdbcTypeName("VARCHAR");
			mts.setJavaClassName(String.class.getName());
			break;

		default:
			return null;
			// throw new UnsupportedDataTypeException();
		}
		return mts;
	}

}
