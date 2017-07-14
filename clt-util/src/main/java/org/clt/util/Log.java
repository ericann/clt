package org.clt.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class Logger {//implements AopGeneric {
	
	//private (org.slf4j.Logger) Logger logger;
	
	public Logger() {
		//logger = LoggerFactory.getLogger(this.getClass());
		System.out.println("-- Logger init");
	}
	
	public void debug(JoinPoint jp) {
		//logger.debug(o);
		System.out.println("-- Logger debug: " + jp);
	}
	
	public void info() {
		//logger.info(o);
		System.out.println("-- Logger info: ");
	}
	
	//params have to JoinPoint only.
	public void error() {
		//logger.error(o);
		System.out.println("-- Logger error: ");
	}
	
	public void before() {
		System.out.println("-- before: ");
	}
	
	public void after() {
		System.out.println("-- after: ");
	}

	public void afterReturn() {
		System.out.println("-- afterReturn: ");
	}
	
	public void afterThrow() {
		System.out.println("-- afterThrow: ");
	}
	
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("-- around: ");
		
		Object[] args = jp.getArgs();  
		
		Object rvt = jp.proceed(args);   
		  
		return rvt;  

	}

//	@Override
//	public void before(Joinpoint joinPoint) {
//		// TODO Auto-generated method stub
//		this.debug(null);
//	}
//
//	@Override
//	public void after(Joinpoint joinPoint) {
//		// TODO Auto-generated method stub
//		this.info(null);
//	}
}
