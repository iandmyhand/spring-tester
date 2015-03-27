package study.hard.spring.core.account.dao;

import org.springframework.stereotype.Repository;

import study.hard.spring.core.account.entity.Account;
import study.hard.spring.core.commons.db.NamespaceSqlSessionDaoSupport;

/**
 * 
 * @author iandmyhand@gmail.com
 */
@Repository
public class AccountDAOImpl extends NamespaceSqlSessionDaoSupport implements AccountDAO {

	/**
	 * {@inheritDoc}
	 */
	public Account insert(Account account) {
		getSqlSession().insert(namespace + "insert", account);
		return account;
	}

	/**
	 * {@inheritDoc}
	 */
	public Account selectOne(String userKey) {
		return getSqlSession().selectOne(namespace + "selectOne", userKey);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer update(Account account) {
		return getSqlSession().update(namespace + "update", account);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer delete(String userKey) {
		return getSqlSession().delete(namespace + "delete", userKey);
	}
}
