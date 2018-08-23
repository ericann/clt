package org.clt.util;

import java.util.Map;

import org.clt.util.mail.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class MailTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MailUtil mail;
	
    @Test
    public void testSave() {
    	String[] addresses = new String[]{"yxeysy@gmail.com","yxeysy@126.com"};
    	Map<String, Object> result = mail.send(addresses, "Test For CLT Mail Service", "This is a test email.");
    	for(String k : result.keySet()) {
    		logger.error("key: " + k + " - value: " + result.get(k));
    	}
    }
    
}
