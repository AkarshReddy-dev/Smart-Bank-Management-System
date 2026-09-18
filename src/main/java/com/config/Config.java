package com.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com")
@EnableTransactionManagement
public class Config 
{
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dmds=new DriverManagerDataSource();
		dmds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dmds.setUrl("jdbc:mysql://localhost:3306/smart_bank_db");
		dmds.setUsername("root");
		dmds.setPassword("root");
		return dmds;
	}
	
	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean()
	
	{
		LocalSessionFactoryBean sessionFactoryBean=new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan("com.model");
		sessionFactoryBean.setHibernateProperties(hibProperties());
		return sessionFactoryBean;				
		
	}
	
	@Bean
	public Properties hibProperties()
	{
		Properties props=new Properties();
		props.put("hibernate.show_sql",true);
		props.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.hbm2ddl.auto", "create");
		return props;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager tManager=new HibernateTransactionManager();
		tManager.setSessionFactory(sessionFactory);
		return tManager;
		
	}
}
