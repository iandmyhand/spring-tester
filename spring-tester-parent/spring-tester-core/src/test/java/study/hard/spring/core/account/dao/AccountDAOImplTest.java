package study.hard.spring.core.account.dao;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import study.hard.spring.core.account.entity.Account;
import study.hard.spring.core.commons.db.BaseDAOTest;

public class AccountDAOImplTest extends BaseDAOTest<AccountDAOImpl, Account> {

	private Logger logger = LoggerFactory.getLogger(AccountDAOImplTest.class);

	@Before
	public void setUp() {
		Account account = new Account("#TESTKEY2#", "test@test.com", "Tester", "Spring", new Date());
		Account insertedAccount = suite.insert(account);
		logger.debug("insertedAccount: {}", insertedAccount);
	}

	@Test
	public void testSelectOne() {
		logger.debug("test");

		Account account = suite.selectOne("#TESTKEY2#");
		logger.debug("account: {}", account);
		validateIsNotNull(account);
	}

	@Override
	protected Class<AccountDAOImpl> getRepsitoryClass() {
		return AccountDAOImpl.class;
	}

	@Override
	protected void validateIsNotNull(Account target) {
		assertNotNull(target);
	}

	@Override
	protected void validateEquals(Account target1, Account target2) {
	}

}
