package study.hard.spring.core.commons.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author iandmyhand@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/test-spring-properties.xml"})
public class SpringPropertiesTest {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("#{common['deploy.phase']}")
	private String COMMON_DEPLOY_PHASE;

	@Test
	public void test() {
		logger.debug("COMMON_DEPLOY_PHASE: {}", COMMON_DEPLOY_PHASE);
	}
}
