package com.yeild.ssh.appconfig;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource({"classpath:config/properties/hibernate.properties"})
public class BaseDatabaseConfig {
	private static final Logger log = Logger.getLogger(BaseDatabaseConfig.class);
	
	@Value("${driverClassName}")
	String driverClass;
	@Value("${url}")
	String jdbcUrl;
	@Value("${dbuser}")
	String username;
	@Value("${password}")
	String password;
	@Value("${maxPoolSize}")
	int maxPoolSize;
	@Value("${minPoolSize}")
	int minPoolSize;
	@Value("${initialPoolSize}")
	int initialPoolSize;
	@Value("${maxIdleTime}")
	int maxIdleTime;
	@Value("${preferredTestQuery}")
	String preferredTestQuery;
	@Value("${idleConnectionTestPeriod}")
	int idleConnectionTestPeriod;
	@Value("${testConnectionOnCheckin}")
	boolean testConnectionOnCheckin;
	@Value("${testConnectionOnCheckout}")
	boolean testConnectionOnCheckout;
	
	@Bean(name="dataSource",destroyMethod="close")
	public DataSource dataSource() {
		log.debug("init datasource started......");
		ComboPooledDataSource managerDataSource = new ComboPooledDataSource();
		try {
			managerDataSource.setDriverClass(driverClass);
			managerDataSource.setJdbcUrl(jdbcUrl);
			managerDataSource.setUser(username);
			managerDataSource.setPassword(password);
			managerDataSource.setMaxPoolSize(maxPoolSize);
			managerDataSource.setMinPoolSize(minPoolSize);
			managerDataSource.setInitialPoolSize(initialPoolSize);
			managerDataSource.setMaxIdleTime(maxIdleTime);
			managerDataSource.setPreferredTestQuery(preferredTestQuery);
			managerDataSource.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
			managerDataSource.setTestConnectionOnCheckin(testConnectionOnCheckin);
			managerDataSource.setTestConnectionOnCheckout(testConnectionOnCheckout);
		} catch (Exception e) {
			log.debug("init datasource ("+jdbcUrl+") failed:\n"+e.getMessage());
			throw new RuntimeException();
		}
		log.debug("init datasource success-----------");
		return managerDataSource;
	}
}
