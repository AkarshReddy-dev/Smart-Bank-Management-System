package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public String sendMail(String toEmail,String otp) {
		
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setFrom("smartbankproject@gmail.com");
		mail.setTo(toEmail);
		mail.setSubject("BANK OTP VERIFICATION");
		mail.setText(otp);
		
		javaMailSender.send(mail);
		return "OTP is sent";
	}
}
