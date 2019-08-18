package com.ptc.sairam.Spring.SpringMvc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping(path = "/hello")
	public String GetWeatherInfo(@RequestParam(required = false, defaultValue = "Boston") String city) {
		String weather = null;
		
		weather = "Hello " + city + "!";
		
		return weather;
	}
}
