package com.ptc.sairam.springboot.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

class HelloControllerUnitTest {

	@Test
	public void sayHello() {
		HelloController controller = new HelloController();
		Model model = new BindingAwareModelMap(); 
		String result = controller.sayHello("Sairam", model);
		assertEquals("Sairam", model.asMap().get("user"));
		assertEquals("hello", result);
	}

}
