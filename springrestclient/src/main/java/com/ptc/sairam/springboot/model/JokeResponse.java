package com.ptc.sairam.springboot.model;

public class JokeResponse {
	public String type;
	public JokeValue value;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public JokeValue getJokeValue() {
		return value;
	}
	
	public void setJokeValue(JokeValue joke) {
		this.value = joke;
	}
}
