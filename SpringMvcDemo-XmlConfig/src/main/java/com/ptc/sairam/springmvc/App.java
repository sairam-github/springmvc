package com.ptc.sairam.springmvc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    private static ApplicationContext context;

	public static void main( String[] args )
    {
        context = new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld objA = (HelloWorld) context.getBean("helloWorld");
        objA.getMessage();
        objA.setMessage("This is objA");
        
        HelloWorld objB = (HelloWorld) context.getBean("helloWorld");
        objB.setMessage("This is objB");
        
        objA.getMessage();
        objB.getMessage();
    }
}
