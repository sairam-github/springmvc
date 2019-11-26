package com.ptc.sairam.springboot;

import static org.junit.Assert.assertSame;

import java.text.NumberFormat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootpracticeApplicationTests {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	@Qualifier("usCurrencyFormat")
	private NumberFormat nf;
	
	@Test
	void contextLoads() {
		System.out.println("There are " + context.getBeanDefinitionCount() + " number of beans.");
		
//		for (String name : context.getBeanDefinitionNames()) {
//			System.out.println(name);
//		}
	}	
	
	@Test
	public void testUsCurrency( ) {
		double amount = 1234567.89012;
		System.out.println(nf.format(amount));
	}
	
	@Test
	public void demoSingleton() {
		var nf1 = context.getBean("usCurrencyFormat", NumberFormat.class);
		var nf2 = context.getBean("usCurrencyFormat", NumberFormat.class);
		assertSame(nf1, nf2);
	}
}
