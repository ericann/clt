package org.clt.util.inter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public interface AopGeneric {
	
	/**
	 * 
	 * @param joinPoint
	 */
	void before(JoinPoint jp);
	
	/**
	 * 
	 * @param joinPoint
	 */
	void after(JoinPoint jp);
	
	/**
	 * 
	 * @param joinPoint
	 */
	void around(ProceedingJoinPoint jp) throws Throwable;
	
	/**
	 * 
	 * @param joinPoint
	 */
	void afterThrow(JoinPoint jp);
	
	/**
	 * 
	 * @param joinPoint
	 */
	void afterReturn(JoinPoint jp);
}
