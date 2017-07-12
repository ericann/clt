package org.clt.util.print;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Printer {
	
	public void print(Object o) {
		System.out.println("-- Printer print: " + o);
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	        
		Printer p = ctx.getBean("printer", Printer.class);
		
	    p.print("AOP Test.");
	}
}
