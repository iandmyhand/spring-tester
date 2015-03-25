package study.hard.spring.core.commons.db;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.transaction.Transaction;
import org.mybatis.spring.transaction.SpringManagedTransaction;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;

public class SpringManagedTransactionExtendedFactory extends SpringManagedTransactionFactory {
	private static DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		SpringManagedTransactionExtendedFactory.dataSource = dataSource;
	}

	public Transaction newTransaction(Connection conn, boolean autoCommit) {
		return new SpringManagedTransaction(dataSource);
	}

	public void setProperties(Properties props) {
	}
}
