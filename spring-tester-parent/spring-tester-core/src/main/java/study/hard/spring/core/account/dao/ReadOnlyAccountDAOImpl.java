package study.hard.spring.core.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import study.hard.spring.core.account.entity.Account;
import study.hard.spring.core.commons.db.ReadOnlyNamespaceSqlSessionDaoSupport;

/**
 * 
 * @author iandmyhand@gmail.com
 */
@Repository
public class ReadOnlyAccountDAOImpl extends ReadOnlyNamespaceSqlSessionDaoSupport implements ReadOnlyAccountDAO {

	/**
	 * {@inheritDoc}
	 */
	public List<Account> selectAccountAll() {
		return getSqlSession().selectList(namespace + "selectAccountAll");
	}

}
