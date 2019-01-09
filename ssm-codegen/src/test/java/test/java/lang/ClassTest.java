package test.java.lang;

import org.junit.Test;

public class ClassTest {

    @Test
    public void test() {
        byte[] bytes = new byte[5];
        System.out.println(bytes.getClass());
        System.out.println(bytes.getClass().getName());
    }

}
