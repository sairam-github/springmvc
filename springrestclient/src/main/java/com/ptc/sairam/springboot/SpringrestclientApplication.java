package com.ptc.sairam.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ptc.sairam.springboot.services.JokeService;

@SpringBootApplication
public class SpringrestclientApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(SpringrestclientApplication.class, args);
//		AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext();
//		cxt.scan("com.ptc.sairam.springboot.services");
//		cxt.refresh();
//		
//		var svc = cxt.getBean(JokeService.class);
//		System.out.println(svc.getJoke("Sairam", "Madem"));
	}

}
