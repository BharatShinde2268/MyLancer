package com.MyLancer.service;

import org.springframework.stereotype.Service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class EmailService {

	
	
	public boolean sendEmail(String subject,String message,String to)
	{
		// rest of the code..
		boolean f=false;
		
		String from="bharatshinde258@gmail.com";
		
		// variable for the email 
		
		String host="smtp.gmail.com";
		
		//get the system properties
		
		Properties properties=System.getProperties();
		System.out.println("Propoerties"+properties);
		
		// setting important information to propoerties object
		
		// host set
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		// step 1: to get the session object
		
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("bharatshinde258@gmail.com", "albbalwszjvjpqnh");
			}
		}

		);
		
		session.setDebug(true);
		
		// step 2: compose the message (text, multi media)
		MimeMessage m=new MimeMessage(session);
		
		
		try {
			
			// from email
			m.setFrom();
			
			// adding recipient to message
			
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			
			// addint subject to message
			m.setSubject(subject);
			
			//adding text to meaage
			
			m.setText(message);
			
			
			// sent mail 
			
			Transport.send(m);
			
			System.out.println("Sent Otp Successfully");
			f=true;
			
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	

	}

	
}
