package com.ptc.sairam.springboot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ptc.sairam.springboot.services.JokeService;

@RunWith(SpringRunner.class)
@SpringBootTest
class JokeServiceTest {
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(JokeServiceTest.class);
	
	@Autowired
	private JokeService service;
	
	@Test
	public void testGetJoke() {
		String joke = service.getJoke();
		logger.info("Joke: " + joke);
		//assertTrue(joke.contains("Craig") || joke.contains("Walls"));
		assertNotNull(joke);
	}
}
