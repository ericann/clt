package org.clt.util.inter;

import org.aopalliance.intercept.Joinpoint;

public interface AopGeneric {
	
	void before(Joinpoint joinPoint);
	void after(Joinpoint joinPoint);
}
