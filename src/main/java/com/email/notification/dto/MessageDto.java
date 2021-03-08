package com.email.notification.dto;

import java.util.List;
/*
 This cleass is created for Holding the message information 
*/
public class MessageDto {

	// msg.setTo("kumarrakesh.java@gmail.com", "rksinghyadav1995@gmail.com");
	// msg.setSubject("Testing from Spring Boot");
	/// msg.setText("Hello World \n Spring Boot Email");

	private List<String> toEmailId;
	private String subject;
	private String message;

	public List<String> getToEmailId() {
		return toEmailId;
	}

	public void setToEmailId(List<String> toEmailId) {
		this.toEmailId = toEmailId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageDto [toEmailId=" + toEmailId + ", subject=" + subject + ", message=" + message + "]";
	}

}
