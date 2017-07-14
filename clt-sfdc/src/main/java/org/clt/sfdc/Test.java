package org.clt.sfdc;

import com.sforce.soap.partner.*;
import com.sforce.ws.*;

public class Test {
	
	public static PartnerConnection login(String username, String password, String loginUrl) {
		
		PartnerConnection con = null;
		
		try {
			username = username != null && username.isEmpty() ? username : "eric.an@test.rest";
			password = password != null && password.isEmpty() ? password : "Pa$$w0rd." + "auZmPb30h3eAb8JMkYZtfQzIe";
			loginUrl = loginUrl != null && loginUrl.isEmpty() ? loginUrl : "https://login.salesforce.com/services/Soap/u/39.0/";
			
			ConnectorConfig config = new ConnectorConfig();
	        config.setUsername(username);
	        //incorrect type
	        //config.setPassword("Pa$$w0rd.1" + "auZmPb30h3eAb8JMkYZtfQzIe");
	        config.setPassword(password);
	        config.setAuthEndpoint(loginUrl);
	        
	        con = Connector.newConnection(config);
	        
	        System.out.println("-- con header: " + con.getSessionHeader());
	        System.out.println("-- con sessionId: " + con.getConfig().getSessionId());
	        System.out.println("-- con sessionId: " + con.getUserInfo().toString());
	        
	        LoginResult loginResult = con.login(username, password);
	        
	        System.out.println("-- loginResult getMetadataServerUrl: " + loginResult.getMetadataServerUrl());
	        System.out.println("-- loginResult sessionId: " + loginResult.getSessionId());
	        System.out.println("-- loginResult userInfo: " + loginResult.getUserInfo().toString());
	        
		} catch(SoapFaultException ex) {
			System.out.println("-- SoapFaultException");
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getClass());
			System.out.println(ex.getCause());
		}
		
		return con;
	} 
	
	public static void main(String[] args) throws ConnectionException {
		login(null, null, null);
	}
}
