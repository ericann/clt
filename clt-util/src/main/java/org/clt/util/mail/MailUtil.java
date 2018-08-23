package org.clt.util.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailUtil {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender javaMailSender; 
	
	@Autowired
	private SimpleMailMessage simpleMailMessage;
	
	private final String ENCODE = "UTF-8";
	
	public Map<String, Object> send(String[] addresses, String subject, String content) {
		
        return doSend(addresses, subject, content);
	}
	
	public List<Map<String, Object>> sendBatch(String[] addresses, String subject, String content) {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		for(String address : addresses) {
			Map<String, Object> result = doSend(new String[]{address}, subject, content);
			result.put("address", address);
			results.add(result);
		}
		
		return results;
	}
	
	public Map<String, Object> doSend(String[] addresses, String subject, String content) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
             helper = new MimeMessageHelper(message, true, ENCODE);
             helper.setFrom(simpleMailMessage.getFrom());
             helper.setSubject(subject);
             helper.setTo(addresses);
             helper.setText(content, true);
             javaMailSender.send(message);
             
             result.put("code", 0);
             result.put("msg", "Send successd.");
        } catch (MessagingException e) {
        	result.put("code", 1001);
            result.put("msg", "Send Failed.");
            result.put("reason", e.getMessage());
        }
		
        return result;
	}
	
//	public boolean checkMailInfor(MailInfor mail){  
//        if (mail == null) {  
//        	logger.warn("@@@ warn mailBean is null (Thread name="   
//                    + Thread.currentThread().getName() + ") ");  
//            return false;  
//        }  
//        if (mail.getSubject() == null) {  
//        	logger.warn("@@@ warn mailBean.getSubject() is null (Thread name="   
//                    + Thread.currentThread().getName() + ") ");  
//            return false;  
//        }  
//        if (mail.getToEmails() == null) {  
//        	logger.warn("@@@ warn mailBean.getToEmails() is null (Thread name="   
//                    + Thread.currentThread().getName() + ") ");  
//            return false;  
//        }  
//        if (mail.getTemplate() == null) {  
//        	logger.warn("@@@ warn mailBean.getTemplate() is null (Thread name="   
//                    + Thread.currentThread().getName() + ") ");  
//            return false;  
//        }  
//        return true;  
//    }  
}
