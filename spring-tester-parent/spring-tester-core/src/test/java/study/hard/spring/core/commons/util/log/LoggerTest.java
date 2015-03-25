package study.hard.spring.core.commons.util.log;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author iandmyhand@gmail.com
 */
public class LoggerTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Before
	public void setUp() {
		PropertyConfigurator.configure("src/test/resources/log4j.properties");
	}

	@Test
	public void test() {
		logger.debug("This is test message.");
	}

}
