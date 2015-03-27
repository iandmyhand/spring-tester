package study.hard.spring.core.account.dao;

import study.hard.spring.core.account.entity.Account;

/**
 * Handling database with User class.
 * @author iandmyhand@gmail.com
 */
public interface AccountDAO {

	/**
	 * 
	 * @param account
	 * @return
	 */
	Account insert(Account account);

	/**
	 * 
	 * @param userKey
	 * @return
	 */
	Account selectOne(String userKey);

	/**
	 * 
	 * @param account
	 * @return
	 */
	Integer update(Account account);

	/**
	 * 
	 * @param userKey
	 * @return
	 */
	Integer delete(String userKey);
}
