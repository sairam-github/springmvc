package com.oreilly.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oreilly.demo.entities.Greeting;

@RestController
public class HelloRestController {
	@GetMapping("/rest")
	public Greeting greet(@RequestParam(required = false, defaultValue = "World") String name) {
		return new Greeting("Hello, " + name + "!");
	}
}
