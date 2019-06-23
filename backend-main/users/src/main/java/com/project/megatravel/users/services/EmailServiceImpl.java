package com.project.megatravel.users.services;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.core.io.InputStreamResource;

@Component
public class EmailServiceImpl implements EmailService {

	private final static Logger logger = Logger.getLogger(EmailServiceImpl.class.getName());
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Override
	@Async
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
        logger.info("Simple email sent");
	}

	@Override
	@Async
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
		
		MimeMessage message = emailSender.createMimeMessage();
	      
	    MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(message, true);
			
			helper.setTo(to);
		    helper.setSubject(subject);
		    helper.setText(text);
			
		    FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		    helper.addAttachment(file.getFilename(), file);
		    
		} catch (MessagingException e) {
			logger.info("Error occured while composing message");
			e.printStackTrace();
		}
	    	 
	    emailSender.send(message);
	    
	    logger.info("Email with attachment sent");
		
	}
	
	@Override
	@Async
	public void sendMessageWithAttachmentFromInputStream(String to, String subject, String text, InputStream inputStream) {
		
		MimeMessage message = emailSender.createMimeMessage();
	      
	    MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(message, true);
			
			helper.setTo(to);
		    helper.setSubject(subject);
		    helper.setText(text);
			
		    helper.addAttachment("reservation.html", new InputStreamResource(inputStream));
		    
		} catch (MessagingException e) {
			logger.info("Error occured while composing message");
			e.printStackTrace();
		}
	    	 
	    emailSender.send(message);
	    
	    logger.info("Email with attachment sent");
		
	}

}
