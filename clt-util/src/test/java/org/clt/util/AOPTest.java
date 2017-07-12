package org.clt.util;

import org.clt.util.print.Printer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class AOPTest {
	
	@Autowired
	private Printer p;
	
    @Test
    public void testSave() {
    	p.print("AOP Test.");
    }
    
}
