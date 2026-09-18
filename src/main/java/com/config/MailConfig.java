package com.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	@Bean
	public JavaMailSenderImpl javaMailSenderImpl() {
		
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		
		mailSender.setUsername("akarshkommidi@gmail.com");
		mailSender.setPassword("gcqzsbmfuouthdto");
		
		Properties properties=mailSender.getJavaMailProperties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		return mailSender;
		
	}

}
