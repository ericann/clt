package org.clt.sfdc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sforce.soap.metadata.AsyncResult;
import com.sforce.soap.metadata.DescribeMetadataObject;
import com.sforce.soap.metadata.DescribeMetadataResult;
import com.sforce.soap.metadata.Metadata;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.metadata.ReadResult;
import com.sforce.soap.metadata.RetrieveRequest;
import com.sforce.soap.partner.LoginResult;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class RetrieveMetadata {
	
	private MetadataConnection metadataConnection;
	private Map<String, String> components;
	
	public RetrieveMetadata() throws ConnectionException {
		this.metadataConnection = this.getMetadataConnection();
		this.components = new HashMap<String, String>();
	}
	
	public MetadataConnection getMetadataConnection() throws ConnectionException {
		LoginSFDC login = new LoginSFDC();
		ConnectorConfig config = new ConnectorConfig();
		
		config.setUsername("eric.an@test.rest");
        config.setPassword("Pa$$w0rd." + "auZmPb30h3eAb8JMkYZtfQzIe");
        config.setAuthEndpoint("https://login.salesforce.com/services/Soap/u/39.0/");
        
		LoginResult loginResult = login.login(config);
		return login.getMetadataConnection(loginResult);
	}
	
	public DescribeMetadataResult desribe() throws ConnectionException {
		DescribeMetadataResult dmr = this.metadataConnection.describeMetadata(39.0);
//		
//		DescribeMetadataObject[] objects = dmr.getMetadataObjects();
		for(DescribeMetadataObject obj : dmr.getMetadataObjects()) {
//			for(String s : obj.getChildXmlNames()) {
//				System.out.println("\t\t-- obj.getChildXmlNames() " + s);
//			}
//			
//			System.out.println("\t-- obj.getDirectoryName() " + obj.getDirectoryName());
//			System.out.println("\t-- obj.getInFolder() " + obj.getInFolder());
//			System.out.println("\t-- obj.getMetaFile() " + obj.getMetaFile());
//			System.out.println("\t-- obj.getSuffix() " + obj.getSuffix());
			//metadata component
			System.out.println("\t-- obj.getXmlName() " + obj.getXmlName());
//			System.out.println("");
		}
//		
//		System.out.println("-- dmr.getOrganizationNamespace: " + dmr.getOrganizationNamespace());
//		System.out.println("-- dmr.getPartialSaveAllowed: " + dmr.getPartialSaveAllowed());
//		System.out.println("-- dmr.getTestRequired: " + dmr.getTestRequired());
		return dmr;
	}
	
	public List<Metadata> retrieveComponentSync(String component, String[] elements) {
		Metadata[] mdInfo = null;
		try {
			ReadResult readResult = this.metadataConnection.readMetadata(this.getComponent(component), elements);

			mdInfo = readResult.getRecords();
//			//System.out.println("Number of component info returned: " + mdInfo.length);
//			for (Metadata md : mdInfo) {
//				if (md != null) {
//					CustomObject obj = (CustomObject) md;
////					System.out.println("Custom object full name: " + obj.getFullName());
////					System.out.println("Label: " + obj.getLabel());
////					System.out.println("Number of custom fields: " + obj.getFields().length);
////					for(CustomField f : obj.getFields()) {
//////						System.out.println("---------------");
//////						System.out.println("\tField datatype: " + f.getCustomDataType());
//////						System.out.println("\tField Description: " + f.getDescription());
//////						System.out.println("\tField Label: " + f.getLabel());
//////						System.out.println("\tField ReferenceTargetField: " + f.getReferenceTargetField());
//////						System.out.println("\tField Required: " + f.getRequired());
//////						System.out.println("\tField type: " + f.getType());
////					}
////					System.out.println("Sharing model: " + obj.getSharingModel());
//				} else {
//					System.out.println("Empty metadata.");
//				}
//			}
		} catch (ConnectionException ex) {
			ex.printStackTrace();
		}
		
		return Arrays.asList(mdInfo);
	}
	
	public AsyncResult retrieve(RetrieveRequest request) {
		AsyncResult result = null;
		
		try {
			result = this.metadataConnection.retrieve(request);
		} catch(Exception ex) {
			
		}
		
		return result;
	}
	
	public String getComponent(String componentName) {
		return this.components.get(componentName);
	}
	
	public static void main(String[] args) throws ConnectionException {
		RetrieveMetadata rm = new RetrieveMetadata();
		//rm.retrieveComponentSync(null, null);
		//rm.desribe();
		
		RetrieveRequest request = new RetrieveRequest();
		request.setApiVersion(39.0);
		System.out.println(rm.retrieve(request));
	}
}
