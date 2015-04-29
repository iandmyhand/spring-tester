package study.hard.spring.core.account.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import study.hard.spring.core.account.entity.Account;
import study.hard.spring.core.account.fixture.AccountFixture;
import study.hard.spring.core.commons.db.ReadOnlyBaseDAOTest;

public class ReadOnlyAccountDAOImplTest extends ReadOnlyBaseDAOTest<ReadOnlyAccountDAOImpl, Account> {

	private Logger logger = LoggerFactory.getLogger(ReadOnlyAccountDAOImplTest.class);

	@Autowired
	private AccountDAOImpl masterSuite;

	@Autowired
	private ReadOnlyAccountDAOImpl suite;

	@Before
	public void setUp() {
		Account account = AccountFixture.createEntity();
		Account insertedAccount = masterSuite.insert(account);
		logger.debug("insertedAccount: {}", insertedAccount);
		validateEquals(account, insertedAccount);
	}

	@Test
	public void selectAccountAll() {
		List<Account> accountList = suite.selectAccountAll();
		assertTrue(0 < accountList.size());
		for (Account entity : accountList) {
			validateIsNotNull(entity);
		}
	}

	@Override
	protected Class<ReadOnlyAccountDAOImpl> getRepsitoryClass() {
		return ReadOnlyAccountDAOImpl.class;
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
