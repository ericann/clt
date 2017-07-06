package org.clt.repository.dao;

import java.util.List;
import java.util.UUID;

import org.clt.repository.pojo.BasicConfig;
import org.clt.repository.pojo.ChatMessage;
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
	private BasicConfigDao bcDao;
	@Autowired
	private ChatMessageDao cmDao;
	@Autowired
	private ButtonDao buttonDao;
	   
    @Test
    @Transactional
    public void testSave() {
    	
		BasicConfig bc = null;
		//ChatMessage cm = null;
		List<String> wechatTokens = null;
		try {
			bc = this.bcDao.findByWechatAccount("gh_c55f730e90a2");
			wechatTokens = this.bcDao.findWechatToken();
			
			ChatMessage cm1 = new ChatMessage();
			cm1.setId("12222");
			cm1.setOpenId("openId");
			//cm1.setButtonId("Live Agent Button Id");
			//cm1.setWechatAccount("gh_c55f730e90a2");
			
			//cm = this.cmDao.save(cm1);
			//System.out.println("save cm: " + cm);
			//System.out.println("query cm: " + this.cmDao.findByOpenId(cm.getOpenId()));
			//System.out.println("delete cm: " + this.cmDao.deleteByOpenId(cm.getOpenId()));
			
			System.out.println("--------------------");
			System.out.println("bc: " + bc);
			//System.out.println("bc.buttons: " + bc.getButtons());
			System.out.println("wechatTokens: " + wechatTokens);
			System.out.println("--------------------");
			
			Integer c = this.cmDao.findCountByButtonId("");
			System.out.println("-- c:" + c);
			//System.out.println("button:" + buttonDao.findByIsDefaultAndBasicConfigId(true, bc.getId()).getIsDefault());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    public static void main(String[] args) {
    	UUID uuid = UUID.randomUUID();
    	System.out.println("uuid: " + uuid);
    }
}
