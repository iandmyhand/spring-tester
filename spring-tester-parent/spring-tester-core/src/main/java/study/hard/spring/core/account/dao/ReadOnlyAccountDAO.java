package study.hard.spring.core.account.dao;

import java.util.List;

import study.hard.spring.core.account.entity.Account;

/**
 * 
 * @author iandmyhand@gmail.com
 */
public interface ReadOnlyAccountDAO {

	/**
	 * 
	 * @return Account list
	 */
	public List<Account> selectAccountAll();

}
