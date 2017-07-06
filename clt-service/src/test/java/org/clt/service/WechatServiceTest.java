package org.clt.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class WechatServiceTest {
	
	@Autowired
	private LARWService larwService;
	   
    @Test
    public void testSave() {
    	
		Boolean flag = false;
		try {
			flag = this.larwService.verfiyAddress("KnZtN9mX0TEe", "timestamp", "nonce");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("--------------------");
		System.out.println("flag: " + flag);
		System.out.println("--------------------");
    }
}
