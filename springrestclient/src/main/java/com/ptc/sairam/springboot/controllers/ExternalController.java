package com.ptc.sairam.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptc.sairam.springboot.services.JokeService;

@RestController
public class ExternalController {
	
	@Autowired
	private JokeService jokeService;
	
	@GetMapping("/joke")
	public String GetJoke() {
		String joke = jokeService.getJoke();
		System.out.println(joke);
		return joke;
	}
}
