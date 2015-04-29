package study.hard.spring.core.account.dao;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import study.hard.spring.core.account.entity.Account;
import study.hard.spring.core.account.fixture.AccountFixture;
import study.hard.spring.core.commons.db.BaseDAOTest;

public class AccountDAOImplTest extends BaseDAOTest<AccountDAOImpl, Account> {

	private Logger logger = LoggerFactory.getLogger(AccountDAOImplTest.class);

	@Before
	public void setUp() {
		Account account = AccountFixture.createEntity();
		Account insertedAccount = suite.insert(account);
		logger.debug("insertedAccount: {}", insertedAccount);
		validateEquals(account, insertedAccount);
	}

	@Test
	public void selectOne() {
		Account account = suite.selectOne(AccountFixture.getUserKey());
		logger.debug("account: {}", account);
		validateIsNotNull(account);
	}

	@Test
	public void update() {
		Account account = suite.selectOne(AccountFixture.getUserKey());
		account.setFirstName("SOMEONE");
		account.setLastName("ELSE");
		account.setRegistYmdt(DateUtils.addDays(account.getRegistYmdt(), -3));
		Integer updatedCount = suite.update(account);
		assertTrue(1 == updatedCount);
		logger.debug("updatedCount: {}", updatedCount);
	}

	@Test
	public void delete() {
		Account account = suite.selectOne(AccountFixture.getUserKey());
		Integer deletedCount = suite.delete(account.getUserKey());
		assertTrue(1 == deletedCount);
	}

	@Override
	protected Class<AccountDAOImpl> getRepsitoryClass() {
		return AccountDAOImpl.class;
	}

	@Override
	protected void validateIsNotNull(Account target) {
		assertNotNull(target);
		assertNotNull(target.getUserKey());
		assertNotNull(target.getEmail());
	}

	@Override
	protected void validateEquals(Account target1, Account target2) {
		validateIsNotNull(target1);
		validateIsNotNull(target2);
		assertEquals(target1.getUserKey(), target2.getUserKey());
		assertEquals(target1.getEmail(), target2.getEmail());
	}

}
