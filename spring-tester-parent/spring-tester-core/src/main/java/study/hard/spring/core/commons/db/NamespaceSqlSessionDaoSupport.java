package study.hard.spring.core.commons.db;

import javax.annotation.Resource;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

@SuppressWarnings("restriction")
public class NamespaceSqlSessionDaoSupport extends DaoSupport {
	protected String namespace;
	protected SqlSessionFactory sqlSessionFactory;
	protected ExecutorType executorType;

	protected SqlSession sqlSession;

	public NamespaceSqlSessionDaoSupport() {
		String shortClassName = ClassUtils.getShortClassName(getClass());
		this.namespace = StringUtils.uncapitalize(StringUtils.removeEnd(shortClassName, "DAOImpl")) + ".";
	}

	public NamespaceSqlSessionDaoSupport(ExecutorType executorType) {
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

	@Autowired(required = false)
	@Resource(name = "sqlSessionFactory")
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Autowired(required = false)
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSession = sqlSessionTemplate;
	}

	protected void checkDaoConfig() {
	}

	protected SqlSessionTemplate createSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) {
		if (executorType != null) {
			return new SqlSessionTemplate(sqlSessionFactory, executorType);
		} else {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
	}

	@Override
	protected void initDao() throws Exception {
		if (sqlSession == null) {
			Assert.notNull(this.sqlSessionFactory, "Property 'sqlSessionFactory' is required");
			this.sqlSession = createSqlSessionTemplate(sqlSessionFactory, executorType);
		}
		Assert.notNull(this.sqlSession, "Property 'sqlSessionFactory' or 'sqlSessionTemplate' is required");
	}
}