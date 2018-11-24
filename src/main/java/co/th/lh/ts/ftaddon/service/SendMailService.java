package co.th.lh.ts.ftaddon.service;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("sendMailService")
public class SendMailService {
	
	@Autowired
	public JavaMailSender emailSender;
	
	public void sendSimpleMessage(String from, String[] to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}
	
	public void sendMessageWithAttachment(String from, String[] to, String subject, String text, String[] pathToAttachments) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
 			helper.setFrom(from);
 			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);

			for(String path : pathToAttachments) {
				FileSystemResource file = new FileSystemResource(new File(path));
				if(file.exists()) {
					helper.addAttachment(file.getFilename(), file);
				}
			}	

			emailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
