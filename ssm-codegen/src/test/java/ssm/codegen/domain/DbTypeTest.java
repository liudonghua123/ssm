package ssm.codegen.domain;

import org.junit.Test;
import ssm.codegen.domain.type.DbType;

import static org.junit.Assert.assertEquals;

public class DbTypeTest {

    @Test
    public void test() {
        assertEquals("MYSQL", DbType.MYSQL.getDbmsProductName());
        assertEquals("ORACLE", DbType.ORACLE.getDbmsProductName());
        assertEquals("SQLSERVER", DbType.SQLSERVER.getDbmsProductName());
        assertEquals("DB2", DbType.DB2.getDbmsProductName());
    }

}
