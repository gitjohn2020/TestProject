package com.grossery.util;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
@Configuration
public class MailConfig {
	
	
//    public JavaMailSender getMailSender(String uname,String password){
//        
//    }

	@Bean
	public JavaMailSender getSender() {
		// TODO Auto-generated method stub
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        //Using gmail
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("arnabpaul830@gmail.com");
        mailSender.setPassword("Tom&jerry1107");
         
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.ssl.trust", "*");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen
        
          
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
	}

}
