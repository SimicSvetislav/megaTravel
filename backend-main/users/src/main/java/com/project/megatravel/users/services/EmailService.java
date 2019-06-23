package com.project.megatravel.users.services;

import java.io.InputStream;

public interface EmailService {
	
	void sendSimpleMessage(String to, String subject, String text);
	
	void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
	
	void sendMessageWithAttachmentFromInputStream(String to, String subject, String text, InputStream inputStream);

}
