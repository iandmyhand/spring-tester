package study.hard.spring.core.account.fixture;

import java.util.Date;

import study.hard.spring.core.account.entity.Account;

public class AccountFixture {

	private static final String USER_KEY = "#TESTKEY#";
	private static final String EMAIL = "test@test.com";
	private static final String FIRST_NAME = "Tester";
	private static final String LAST_NAME = "Spring";
	private static final Date REGIST_YMDT = new Date();

	public static Account createEntity() {
		Account account = new Account();
		account.setUserKey(USER_KEY);
		account.setEmail(EMAIL);
		account.setFirstName(FIRST_NAME);
		account.setLastName(LAST_NAME);
		account.setRegistYmdt(REGIST_YMDT);
		return account;
	}

	public static String getUserKey() {
		return USER_KEY;
	}

	public static String getEmail() {
		return EMAIL;
	}

	public static String getFirstName() {
		return FIRST_NAME;
	}

	public static String getLastName() {
		return LAST_NAME;
	}

	public static Date getRegistYmdt() {
		return REGIST_YMDT;
	}
}
