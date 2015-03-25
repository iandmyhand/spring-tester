package study.hard.spring.core.account.dao;

import study.hard.spring.core.account.entity.Account;

/**
 * Handling database with User class.
 * @author iandmyhand@gmail.com
 */
public interface AccountDAO {

	/**
	 * 
	 * @param user
	 * @return
	 */
	Account insert(Account user);

	/**
	 * 
	 * @param userKey
	 * @return
	 */
	Account selectOne(String userKey);

	/**
	 * 
	 * @param user
	 * @return
	 */
	Integer update(Account user);

	/**
	 * 
	 * @param userKey
	 * @return
	 */
	Integer delete(String userKey);
}
