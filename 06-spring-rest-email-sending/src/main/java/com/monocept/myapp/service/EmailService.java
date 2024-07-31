package com.monocept.myapp.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.monocept.myapp.entity.MailStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@org.springframework.stereotype.Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String fromMail;
	
	public boolean sendEmail(String toMail,MailStructure mailStructure) {
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setFrom(fromMail);
		mailMessage.setTo(toMail);
		mailMessage.setText(mailStructure.getMessage());
		mailMessage.setSubject(mailStructure.getSubject());
		javaMailSender.send(mailMessage);
		return true;
	}
	
	public boolean sendEmailWithAttachment(String toMail, MailStructure mailStructure, String filePath) throws MessagingException {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromMail);
            helper.setTo(toMail);
            helper.setSubject(mailStructure.getSubject());
            helper.setText(mailStructure.getMessage());

            File file = new File(filePath);
            if (file.exists()) {
                helper.addAttachment(file.getName(), file);
            } 
            javaMailSender.send(message);
            return true;
    }
}
