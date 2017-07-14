package org.clt.sfdc;

import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.LoginResult;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class LoginSFDC {
	
	public PartnerConnection getPC(ConnectorConfig config) throws ConnectionException {
		return Connector.newConnection(config);
	}
	
	public LoginResult login(PartnerConnection con, ConnectorConfig config) throws ConnectionException {
		return con.login(config.getUsername(), config.getPassword());
	}
	
	public LoginResult login(ConnectorConfig config) throws ConnectionException {
		return Connector.newConnection(config).login(config.getUsername(), config.getPassword());
	}
	
	public MetadataConnection getMetadataConnection(LoginResult loginResult) throws ConnectionException {
		ConnectorConfig metadataConfig = new ConnectorConfig();
		metadataConfig.setServiceEndpoint(loginResult.getMetadataServerUrl());
		metadataConfig.setSessionId(loginResult.getSessionId());
		return new MetadataConnection(metadataConfig);
	}
	
	public MetadataConnection getMetadataConnection(ConnectorConfig config) throws ConnectionException {
		LoginResult loginResult = this.login(config);
		ConnectorConfig metadataConfig = new ConnectorConfig();
		metadataConfig.setServiceEndpoint(loginResult.getMetadataServerUrl());
		metadataConfig.setSessionId(loginResult.getSessionId());
		return new MetadataConnection(metadataConfig);
	}

}
