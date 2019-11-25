package com.ptc.sairam.springboot.model;

public class Greeting {
	private String message = "Hello, World!";

	public String getMessage() {
		return message;
	}

	public Greeting() {
		
	}

	public Greeting(String message) {
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
