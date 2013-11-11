package ssm.codegen.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class DbTypeTest {

	@Test
	public void test() {
		assertEquals(DbType.MYSQL.getDbmsPorductName(), "MYSQL");
		assertEquals(DbType.ORACLE.getDbmsPorductName(), "ORACLE");
		assertEquals(DbType.SQLSERVER.getDbmsPorductName(), "SQLSERVER");
		assertEquals(DbType.DB2.getDbmsPorductName(), "DB2");
	}

}
