package com.email.notification.service;

import java.io.File;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.email.notification.dto.MessageDto;

@Service
public class EmailNotificationService {

	@Autowired
	private JavaMailSender javaMailSender;

	public String sendMessage(MessageDto message) {
		boolean emailSendStatus = false;
		SimpleMailMessage msg = new SimpleMailMessage();
		List<String> toEmail = message.getToEmailId();
		for (String to : toEmail) {
			msg.setTo(to);
			msg.setSubject(message.getSubject());
			msg.setText(message.getMessage());
			javaMailSender.send(msg);
		}
		msg.setSubject(message.getSubject());
		msg.setText(message.getMessage());
		javaMailSender.send(msg);
		emailSendStatus = true;
		if (emailSendStatus) {
			return "Email send succussfully";
		} else {
			return "Please check internet connection";
		}
	}

	public String sendAttachedMessage(MultipartFile file, MessageDto messageDto) throws Exception {
		boolean sendMessageStatus = false;
		MimeMessage msg = javaMailSender.createMimeMessage();
		// true = multipart message
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		List<String> toEmail = messageDto.getToEmailId();
		for (String to : toEmail) {
			helper.setTo(to);
			helper.setSubject(messageDto.getSubject());
			helper.setText(messageDto.getMessage());
			InputStreamSource attachement = new ByteArrayResource(file.getBytes());
			helper.addAttachment(file.getOriginalFilename(), attachement);
			javaMailSender.send(msg);
			sendMessageStatus = true;
		}
		if (sendMessageStatus) {
			return "message send successfully with payload";
		} else {
			return "error while sending email";
		}

	}

}
