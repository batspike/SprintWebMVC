package com.samcancode.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages= {"com.samcancode.repos"})
@EnableTransactionManagement // equivalent to <tx:annotation-driven/> in xml config
@ComponentScan(basePackages= {"com.samcancode"})
public class DataServiceConfig
{
	private static Logger logger = LoggerFactory.getLogger(DataServiceConfig.class);
	
	@Bean
	public DataSource dataSource() {
		try {
			EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
			return dbBuilder.setType(EmbeddedDatabaseType.H2).build();
		}
		catch(Exception e) {
			logger.error("Embedded DataSource bean cannot be created!",e);
			return null;
		}
	}
	
	@Bean
	public Properties hibernateProperties() {
		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.dialect", 			"org.hibernate.dialect.H2Dialect");
		hibernateProp.put("hibernate.hbm2ddl.auto", 	"create-drop");
		hibernateProp.put("hibernate.show_sql", 		false);
		hibernateProp.put("hibernate.max_fetch_depth", 	3);
		hibernateProp.put("hibernate.jebc.batch_size", 	10);
		hibernateProp.put("hibernate.jdbc.fetch_size", 	50);
		return hibernateProp;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPackagesToScan("com.samcancode.entities");
		factoryBean.setDataSource(dataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		factoryBean.setJpaProperties(hibernateProperties());
		factoryBean.afterPropertiesSet();
		return factoryBean.getNativeEntityManagerFactory();
	}
	
	
	
	
}
