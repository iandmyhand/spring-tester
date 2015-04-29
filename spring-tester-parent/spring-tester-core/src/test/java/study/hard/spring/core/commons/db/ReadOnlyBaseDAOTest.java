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

/**
 * @author iandmyhand@gmail.com
 * @param <TYPE> ReadOnly DAO Class
 * @param <ENTITY> Entity Class
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/test-root-context.xml"})
public abstract class ReadOnlyBaseDAOTest<TYPE, ENTITY> extends Assert {

	@Autowired
	protected ApplicationContext applicationContext;

	protected TYPE sut;

	protected abstract Class<TYPE> getRepsitoryClass();

	protected abstract void validateIsNotNull(ENTITY target);

	protected abstract void validateEquals(ENTITY target1, ENTITY target2);

	@Before
	public void initSut() {
		Class<TYPE> clazz = getRepsitoryClass();
		if (clazz != null) {
			sut = createAutowiredBean(getRepsitoryClass());
		}
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <E> E createAutowiredBean(Class<E> clazz) {
		if (clazz.isInterface()) {
			throw new IllegalArgumentException(clazz.getName() + "is Interface. Please use Implement Class.");
		}
		AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
		return (E)factory.createBean(clazz, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
	}
}
