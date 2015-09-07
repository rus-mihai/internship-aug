package com.acc.internship.model;

public class UserMessage {

	private String date;
	private String name;
	private String usermessage;

	public UserMessage() {
	
	}

	public UserMessage(String date, String name, String usermessage) {
		super();
		this.date = date;
		this.name = name;
		this.usermessage = usermessage;
	}
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsermessage() {
		return usermessage;
	}

	public void setUsermessage(String usermessage) {
		this.usermessage = usermessage;
	}

}
