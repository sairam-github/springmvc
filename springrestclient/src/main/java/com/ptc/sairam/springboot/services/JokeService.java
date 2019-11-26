package com.ptc.sairam.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ptc.sairam.springboot.model.JokeResponse;

@Service
public class JokeService {
	private RestTemplate restTemplate;
	
	@Autowired
	public JokeService(RestTemplateBuilder builder) {
		restTemplate = builder.build();
	}
	
	public String getJoke() {
		String url = "http://api.icndb.com/jokes/random?limitTo=[nerdy]";
		
		JokeResponse response = restTemplate.getForObject(url, JokeResponse.class);
		return response.getJokeValue().getJoke();
	}
}
