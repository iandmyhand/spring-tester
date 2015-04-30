package study.hard.spring.core.account.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import study.hard.spring.core.account.dao.AccountDAO;

/**
 * A implement class of AccountService.
 * @author iandmyhand@gmail.com
 */
public class AccountServiceImpl implements AccountService {

	private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	AccountDAO accountDAO;

	public String getNewUserKey() {
		String newUserKey = null;
		do {
			newUserKey = UUID.randomUUID().toString();
		} while (accountDAO.selectOneIsExist(newUserKey));

		logger.info("Generate new user key: {}", newUserKey);
		return newUserKey;
	}

}
