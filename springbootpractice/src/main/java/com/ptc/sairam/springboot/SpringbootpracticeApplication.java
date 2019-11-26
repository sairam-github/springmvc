package com.ptc.sairam.springboot;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootpracticeApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(SpringbootpracticeApplication.class, args);
		System.out.println("# of Beans: " + ctx.getBeanDefinitionCount());
		
		var Beans = ctx.getBeanDefinitionNames();
		Arrays.sort(Beans);
		Arrays.asList(Beans).forEach(System.out::println);
	}
}
