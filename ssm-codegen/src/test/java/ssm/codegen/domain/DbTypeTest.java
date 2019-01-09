package ssm.codegen.domain;

import org.junit.Test;
import ssm.codegen.domain.type.DbType;

import static org.junit.Assert.assertEquals;

public class DbTypeTest {

    @Test
    public void test() {
        assertEquals(DbType.MYSQL.getDbmsProductName(), "MYSQL");
        assertEquals(DbType.ORACLE.getDbmsProductName(), "ORACLE");
        assertEquals(DbType.SQLSERVER.getDbmsProductName(), "SQLSERVER");
        assertEquals(DbType.DB2.getDbmsProductName(), "DB2");
    }

}
