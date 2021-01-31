package com.email.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.email.notification.dto.MessageDto;
import com.email.notification.service.EmailNotificationService;

@RestController
@RequestMapping(value = "/api/email/notificaiton")
public class EmailNotificationController {
	//add comment by rakesh kumar 
	
	//add new feature_bug_110
	@Autowired
	private EmailNotificationService emailNotificationService;

	@PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String sendMail(@RequestBody MessageDto messageDto) {

		return emailNotificationService.sendMessage(messageDto);
	}

	@PostMapping(value = "/sendwithattached", consumes = "multipart/form-data")
	public String sendMailWithAttached(@RequestPart(value = "file") MultipartFile file,
			@RequestPart("profile") MessageDto messageDto) throws Exception {

		return emailNotificationService.sendAttachedMessage(file, messageDto);
	}
}
