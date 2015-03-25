package study.hard.spring.core.commons.db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/test-root-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public abstract class BaseDAOTest<T, E> extends Assert {

	@Autowired
	protected ApplicationContext applicationContext;

	protected T suite;

	protected abstract Class<T> getRepsitoryClass();

	protected abstract void validateIsNotNull(E target);

	protected abstract void validateEquals(E target1, E target2);

	@Before
	public void initSuite() {
		Class<T> clazz = getRepsitoryClass();
		if (clazz != null) {
			suite = createAutowiredBean(getRepsitoryClass());
		}
	}

	/**
	 * @return
	 */
	@SuppressWarnings({"unchecked", "hiding"})
	public <T> T createAutowiredBean(Class<T> clazz) {
		if (clazz.isInterface()) {
			throw new IllegalArgumentException(clazz.getName() + " is interface. Please use implemented Class.");
		}
		AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
		return (T)factory.createBean(clazz, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
	}
}
