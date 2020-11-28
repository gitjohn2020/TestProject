package com.grossery.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MailContent {

	private String from;
	private List<String> to;
	private String subject;
	private String body;
	private String password;
	private String pathOfXL;
	
	
	public String getPathOfXL() {
		return pathOfXL;
	}
	public void setPathOfXL(String pathOfXL) {
		this.pathOfXL = pathOfXL;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public List<String> getTo() {
		return to;
	}
	public void setTo(List<String> to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "MailContent [from=" + from + ", to=" + to + ", subject="
				+ subject + ", body=" + body + "]";
	}
	
	
}
