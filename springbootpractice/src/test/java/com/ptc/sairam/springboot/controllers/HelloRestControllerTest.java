package com.ptc.sairam.springboot.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ptc.sairam.springboot.model.Greeting;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloRestControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void greetWithoutName() {
		ResponseEntity<Greeting> entity = restTemplate.getForEntity("/rest", Greeting.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
		Greeting response = entity.getBody();
		assertEquals("Hello, World!", response.getMessage());
	}
	
	@Test
	public void greetWithNameHeaders() {
		ResponseEntity<Greeting> entity = restTemplate.getForEntity("/rest?name=Sairam", Greeting.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
		Greeting response = entity.getBody();
		assertEquals("Hello, Sairam!", response.getMessage());
	}
	
	@Test
	public void greetWithName() {
		Greeting response = restTemplate.getForObject("/rest?name=Sairam", Greeting.class);
		assertEquals("Hello, Sairam!", response.getMessage());
	}
}
