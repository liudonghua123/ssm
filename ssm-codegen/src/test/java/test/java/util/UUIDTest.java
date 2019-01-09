package test.java.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.UUID;

@Slf4j
public class UUIDTest {

    @Test
    public void test() {
        if (log.isDebugEnabled()) {
            log.debug("{}", UUID.randomUUID().getLeastSignificantBits());
            log.debug("{}", UUID.randomUUID().getMostSignificantBits());
            log.debug("{}", UUID.randomUUID().toString());
        }
    }

}
