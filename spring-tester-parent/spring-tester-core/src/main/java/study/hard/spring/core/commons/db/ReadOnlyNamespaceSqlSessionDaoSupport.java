package study.hard.spring.core.commons.db;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

public class ReadOnlyNamespaceSqlSessionDaoSupport extends DaoSupport {

	protected String namespace;
	protected SqlSessionFactory rdbSqlSessionFactory;
	protected ExecutorType executorType;
	protected SqlSession sqlSession;

	/**
	 * Instantiates a new namespace sql session dao support.
	 */
	public ReadOnlyNamespaceSqlSessionDaoSupport() {
		String shortClassName = ClassUtils.getShortClassName(getClass());
		this.namespace = StringUtils.uncapitalize(StringUtils.removeEnd(StringUtils.removeStart(shortClassName, "ReadOnly"), "DAOImpl")) + ".";
	}

	public ReadOnlyNamespaceSqlSessionDaoSupport(ExecutorType executorType) {
		this();
		this.executorType = executorType;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setExecutorType(ExecutorType executorType) {
		this.executorType = executorType;
	}

	public SqlSession getSqlSession() {
		return this.sqlSession;
	}

	@Autowired
	@Qualifier("rdbSqlSessionFactory")
	public void setRdbSqlSessionFactory(SqlSessionFactory rdbSqlSessionFactory) {
		this.rdbSqlSessionFactory = rdbSqlSessionFactory;
	}

	@Autowired(required = false)
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSession = sqlSessionTemplate;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void checkDaoConfig() {
	}

	protected SqlSessionTemplate createSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) {
		if (executorType != null) {
			return new ReadOnlySqlSessionTemplate(sqlSessionFactory, executorType);
		} else {
			return new ReadOnlySqlSessionTemplate(sqlSessionFactory);
		}
	}

	/**
	 * @throws Exception
	 * @see org.springframework.dao.support.DaoSupport#initDao()
	 */
	@Override
	protected void initDao() throws Exception {
		if (sqlSession == null) {
			Assert.notNull(this.rdbSqlSessionFactory, "Property 'sqlSessionFactory' are required");

			this.sqlSession = createSqlSessionTemplate(rdbSqlSessionFactory, executorType);
		}

		Assert.notNull(this.sqlSession, "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
	}

	/**
	 * To restrict insert, update and delete functions.
	 */
	class ReadOnlySqlSessionTemplate extends SqlSessionTemplate {
		public ReadOnlySqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
			super(sqlSessionFactory);
		}

		public ReadOnlySqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) {
			super(sqlSessionFactory, executorType);
		}

		@Override
		public int insert(String statement) {
			throw new UnsupportedOperationException("Insert is not allowed for a readonly Repository.[" + ReadOnlyNamespaceSqlSessionDaoSupport.this.getClass().toString() + "]");
		}

		@Override
		public int update(String statement, Object parameter) {
			throw new UnsupportedOperationException("Update is not allowed for a readonly Repository.[" + ReadOnlyNamespaceSqlSessionDaoSupport.this.getClass().toString() + "]");
		}

		@Override
		public int update(String statement) {
			throw new UnsupportedOperationException("Update is not allowed for a readonly Repository.[" + ReadOnlyNamespaceSqlSessionDaoSupport.this.getClass().toString() + "]");
		}

		@Override
		public int delete(String statement) {
			throw new UnsupportedOperationException("Delete is not allowed for a readonly Repository.[" + ReadOnlyNamespaceSqlSessionDaoSupport.this.getClass().toString() + "]");
		}

		@Override
		public int delete(String statement, Object parameter) {
			throw new UnsupportedOperationException("Delete is not allowed for a readonly Repository.[" + ReadOnlyNamespaceSqlSessionDaoSupport.this.getClass().toString() + "]");
		}

	}
}
