package com.app.configuration;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

	@Configuration
	@EnableTransactionManagement
	public class HibernateConfig {

		@Bean
		public LocalContainerEntityManagerFactoryBean getEntityManager() {
			LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
			entityManager.setDataSource(getDataSource());
			entityManager.setPersistenceProviderClass(HibernatePersistenceProvider.class);
			entityManager.setPackagesToScan(new String[] { "com.app.entity" });
			entityManager.setJpaProperties(getProperties());
			return entityManager;

		}

		@Bean
		public PlatformTransactionManager getTransaction() {
			JpaTransactionManager transaction = new JpaTransactionManager();
			transaction.setEntityManagerFactory(getEntityManager().getObject());
			return transaction;

		}

		private Properties getProperties() {
			Properties properties = new Properties();
			properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
			properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.format_sql", "true");
			properties.put("hibernate.hbm2ddl.auto", "create");

			return properties;
		}

		private DataSource getDataSource() {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/app");
			dataSource.setUsername("root");
			dataSource.setPassword("Welcome123");
			return dataSource;
		}
	}

		/*@Autowired
		private Environment env;
		
		@Bean
	    public DataSource dataSource() {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(env.getProperty( "spring.datasource.driver-class-name"));
			dataSource.setUsername(env.getProperty("spring.datasource.username"));
			dataSource.setPassword(env.getProperty("spring.datasource.password"));
			dataSource.setUrl(env.getProperty("spring.datasource.url"));
			return dataSource;
	    }
		@Bean
		public PlatformTransactionManager transactionManager() {
			JpaTransactionManager transactionManager = new JpaTransactionManager();
			transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
			return transactionManager;

	}
		@Bean
		public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
			LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
			entityManagerFactoryBean.setDataSource(dataSource());
			entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
			entityManagerFactoryBean.setPackagesToScan(new String[] { "com.server.entity" });
			entityManagerFactoryBean.setJpaProperties(addProperties());
			return entityManagerFactoryBean;
		}
		
		private Properties addProperties()
		{
			Properties properties = new Properties();
			properties.setProperty("hibernate.hbm2ddl.auto",env.getProperty("spring.jpa.hibernate.ddl-auto"));
		    properties.setProperty("hibernate.dialect",env.getProperty("spring.jpa.properties.hibernate.dialect"));
			properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
			properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
			
			return properties;
			
		}
	}*/
}
