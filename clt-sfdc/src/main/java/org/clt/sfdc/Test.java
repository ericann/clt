package org.clt.sfdc;

import com.sforce.soap.partner.*;
import com.sforce.ws.*;

public class Test {
	
	public static void main(String[] args) throws ConnectionException {
		ConnectorConfig config = new ConnectorConfig();
        config.setUsername("eric.an@test.rest");
        config.setPassword("Pa$$w0rd.");
        config.setAuthEndpoint("https://login.salesforce.com/services/Soap/u/39.0/");

        PartnerConnection con = Connector.newConnection(config);
        System.out.println("-- con: " + con);
	}
}
