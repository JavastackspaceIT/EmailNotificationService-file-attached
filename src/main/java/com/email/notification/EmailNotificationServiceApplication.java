package com.email.notification;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootApplication
public class EmailNotificationServiceApplication {

	@Autowired
	private JavaMailSender javaMailSender;

	public static void main(String[] args) {
		SpringApplication.run(EmailNotificationServiceApplication.class, args);
	}

	public void run(String... args) throws Exception {
		sendEmailWithAttachment();
	}

	void sendEmail() {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("kumarrakesh.java@gmail.com", "rksinghyadav1995@gmail.com");

		msg.setSubject("Testing from Spring Boot");
		msg.setText("Hello World \n Spring Boot Email");

	///	javaMailSender.send(msg);

	}

	void sendEmailWithAttachment() throws MessagingException, IOException {
		MimeMessage msg = javaMailSender.createMimeMessage();

		// true = multipart message
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo("kumarrakesh.java@gmail.com");

		helper.setSubject("Testing from Spring Boot");

		// default = text/plain
		// helper.setText("Check attachment for image!");

		// true = text/html
		helper.setText("<h1>Check attachment for image!</h1>", true);

	//	helper.addAttachment("1.png", new ClassPathResource("android.png"));
       helper.addAttachment("1.png", new File("C:\\Users\\Rakesh\\Desktop\\1.jpg"));
		javaMailSender.send(msg);

	}
}
