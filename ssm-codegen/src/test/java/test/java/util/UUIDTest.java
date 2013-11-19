package test.java.util;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UUIDTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void test() {
		System.out.println(UUID.randomUUID().getLeastSignificantBits());
		System.out.println(UUID.randomUUID().getMostSignificantBits());
		logger.debug(UUID.randomUUID().toString());
	}

}