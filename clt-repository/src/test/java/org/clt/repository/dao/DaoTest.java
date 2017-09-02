package org.clt.repository.dao;

import java.util.UUID;

import org.clt.repository.pojo.WechatAccount;
import org.clt.repository.pojo.WechatTicket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class DaoTest {
	
	@Autowired
	private WechatAccountDao waDao;
	
	@Autowired
	private WechatTicketDao wechatTicketDao;
	
    @Test
    @Transactional
    public void testSave() {
    	
		try {
			WechatAccount wa = this.waDao.findAndLiveAgentById("753d8d1c-973e-4de6-aa4e-0e9c00b9990f");
			System.out.println(wa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    @Test
    public void testWTQuery() {
    	WechatTicket wt = this.wechatTicketDao.findByTicket("gQHj7zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyRkpSV01pZHplRWkxQ0M4R05wMVAAAgQuSKpZAwR4AAAA");
    	System.out.println("wt : "+ wt);
    }
    
    public static void main(String[] args) {
    	UUID uuid = UUID.randomUUID();
    	System.out.println("uuid: " + uuid);
    }
}
