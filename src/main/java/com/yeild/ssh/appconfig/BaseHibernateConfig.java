package com.yeild.ssh.appconfig;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({BaseDatabaseConfig.class})
@EnableTransactionManagement
public class BaseHibernateConfig {
	@Value("${hibernate.hbm2ddl.auto}")
	String hibernate_hbm2ddl_auto;
	@Value("${hibernate.dialect}")
	String hibernate_dialect;
	@Value("${hibernate.show_sql}")
	String hibernate_show_sql;
	@Value("${hibernate.format_sql}")
	String hibernate_format_sql;
	@Value("${hibernate.autoReconnect}")
	String hibernate_autoReconnect;
	@Value("${hibernate.temp.use_jdbc_metadata_defaults}")
	String hibernate_temp_use_jdbc_metadata_defaults;
	@Value("${hibernate.current_session_context_class}")
	String hibernate_current_session_context_class;
	
	/**
	 * 解析资源文件
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Resource(name="dataSource")
	public DataSource dataSource;
	
	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean localSessionFactoryBean() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[]{""});
		
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
		hibernateProperties.setProperty("hibernate.dialect", hibernate_dialect);
		hibernateProperties.setProperty("hibernate.show_sql", hibernate_show_sql);
		hibernateProperties.setProperty("hibernate.format_sql", hibernate_format_sql);
		hibernateProperties.setProperty("hibernate.autoReconnect", hibernate_autoReconnect);
		hibernateProperties.setProperty("hibernate.temp.use_jdbc_metadata_defaults"
				, hibernate_temp_use_jdbc_metadata_defaults);
//		hibernateProperties.setProperty("hibernate.current_session_context_class"
//				, hibernate_current_session_context_class);
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}
	
	@Bean(name="transactionManager")
	@Autowired
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
	
	@Bean(name="persistenceExceptionTranslationPostProcessor")
	public PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
