package org.clt.sfdc;

import com.sforce.soap.partner.*;
import com.sforce.soap.partner.sobject.*;
import com.sforce.ws.*;

public class Test {
	
	public static void main(String[] args) throws ConnectionException {
		ConnectorConfig config = new ConnectorConfig();
        config.setUsername("username");
        config.setPassword("password");

        PartnerConnection connection = Connector.newConnection(config);
        SObject account = new SObject();
        account.setType("Account");
        account.setField("Name", "My Account");
        connection.create(new SObject[]{account});
	}
}
