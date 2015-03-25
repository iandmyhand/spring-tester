package study.hard.spring.core.commons.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jmx.export.annotation.ManagedAttribute;

public class ManagedBasicDataSource extends BasicDataSource {
	@ManagedAttribute
	@Override
	public synchronized int getMaxActive() {
		return super.getMaxActive();
	};

	@ManagedAttribute
	@Override
	public synchronized int getNumActive() {
		return super.getNumActive();
	}

	@ManagedAttribute
	@Override
	public synchronized int getNumIdle() {
		return super.getNumIdle();
	}

	@ManagedAttribute
	@Override
	public synchronized int getInitialSize() {
		return super.getInitialSize();
	};

	@ManagedAttribute
	@Override
	public synchronized boolean isPoolPreparedStatements() {
		return super.isPoolPreparedStatements();
	};

}
