package org.clt.sfdc;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sforce.soap.metadata.AsyncResult;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.metadata.PackageTypeMembers;
import com.sforce.soap.metadata.RetrieveMessage;
import com.sforce.soap.metadata.RetrieveRequest;
import com.sforce.soap.metadata.RetrieveResult;
import com.sforce.soap.metadata.RetrieveStatus;
import com.sforce.soap.partner.LoginResult;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class RetrieveSample {
	// Binding for the metadata WSDL used for making metadata API calls
	private MetadataConnection metadataConnection;
	
	static BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));
	// one second in milliseconds
	private static final long ONE_SECOND = 1000;
	// maximum number of attempts to retrieve the results
	private static final int MAX_NUM_POLL_REQUESTS = 50;
	// manifest file that controls which components get retrieved
	private static final String MANIFEST_FILE = "package.xml";
	private static final double API_VERSION = 31.0;
	
	public static void main(String[] args) throws Exception {
		final String USERNAME = "user@company.com";
		// This is only a sample. Hard coding passwords in source files is a bad practice.
		final String PASSWORD = "password";
		final String URL = "https://login.salesforce.com/services/Soap/c/31.0";
		RetrieveSample sample = new RetrieveSample(USERNAME, PASSWORD, URL);
		sample.retrieveZip();
	}
	
	public RetrieveSample(String username, String password, String loginUrl) throws ConnectionException {
		Test.login(username, password, loginUrl);
		//createMetadataConnection(username, password, loginUrl);
	}
	
	private void retrieveZip() throws RemoteException, Exception {
		RetrieveRequest retrieveRequest = new RetrieveRequest();
		// The version in package.xml overrides the version in RetrieveRequest
		retrieveRequest.setApiVersion(API_VERSION);
		setUnpackaged(retrieveRequest);
		// Start the retrieve operation
		AsyncResult asyncResult = metadataConnection.retrieve(retrieveRequest);
		String asyncResultId = asyncResult.getId();
		// Wait for the retrieve to complete
		int poll = 0;
		long waitTimeMilliSecs = ONE_SECOND;
		RetrieveResult result = null;
		do {
			Thread.sleep(waitTimeMilliSecs);
			// Double the wait time for the next iteration
			waitTimeMilliSecs *= 2;
			if (poll++ > MAX_NUM_POLL_REQUESTS) {
			throw new Exception("Request timed out. If this is a large set " +
			"of metadata components, check that the time allowed " +
			"by MAX_NUM_POLL_REQUESTS is sufficient.");
			}
		
			result = metadataConnection.checkRetrieveStatus(asyncResultId, true);
			System.out.println("Retrieve Status: " + result.getStatus());
		} while (!result.isDone());
		
		if (result.getStatus() == RetrieveStatus.Failed) {
			throw new Exception(result.getErrorStatusCode() + " msg: " + result.getErrorMessage());
		} else if (result.getStatus() == RetrieveStatus.Succeeded) {
			// Print out any warning messages
			StringBuilder buf = new StringBuilder();
			if (result.getMessages() != null) {
				for (RetrieveMessage rm : result.getMessages()) {
					buf.append(rm.getFileName() + " - " + rm.getProblem());
				}
			}
			
			if (buf.length() > 0) {
				System.out.println("Retrieve warnings:\n" + buf);
			}
			
			// Write the zip to the file system
			System.out.println("Writing results to zip file");
			ByteArrayInputStream bais = new ByteArrayInputStream(result.getZipFile());
			File resultsFile = new File("retrieveResults.zip");
			FileOutputStream os = new FileOutputStream(resultsFile);
			try {
				ReadableByteChannel src = Channels.newChannel(bais);
				FileChannel dest = os.getChannel();
				copy(src, dest);
				System.out.println("Results written to " + resultsFile.getAbsolutePath());
			} finally {
				os.close();
			}
		}
	}
	/**
	* Helper method to copy from a readable channel to a writable channel,
	* using an in-memory buffer.
	*/
	private void copy(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
		// Use an in-memory byte buffer
		ByteBuffer buffer = ByteBuffer.allocate(8092);
		while (src.read(buffer) != -1) {
			buffer.flip();
			while(buffer.hasRemaining()) {
				dest.write(buffer);
			}
			buffer.clear();
		}
	}
	
	private void setUnpackaged(RetrieveRequest request) throws Exception {
		// Edit the path, if necessary, if your package.xml file is located elsewhere
		File unpackedManifest = new File(MANIFEST_FILE);
		System.out.println("Manifest file: " + unpackedManifest.getAbsolutePath());
		if (!unpackedManifest.exists() || !unpackedManifest.isFile())
			throw new Exception("Should provide a valid retrieve manifest " +
					"for unpackaged content. " +
					"Looking for " + unpackedManifest.getAbsolutePath());
		// Note that we populate the _package object by parsing a manifest file here.
		// You could populate the _package based on any source for your
		// particular application.
		com.sforce.soap.metadata.Package p = parsePackage(unpackedManifest);
		request.setUnpackaged(p);
	}
	
	private com.sforce.soap.metadata.Package parsePackage(File file) throws Exception {
		try {
			InputStream is = new FileInputStream(file);
			List<PackageTypeMembers> pd = new ArrayList<PackageTypeMembers>();
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Element d = db.parse(is).getDocumentElement();
			for (Node c = d.getFirstChild(); c != null; c = c.getNextSibling()) {
				if (c instanceof Element) {
					Element ce = (Element)c;
					//
					NodeList namee = ce.getElementsByTagName("name");
					if (namee.getLength() == 0) {
						// not
						continue;
					}
					
					String name = namee.item(0).getTextContent();
					NodeList m = ce.getElementsByTagName("members");
					List<String> members = new ArrayList<String>();
					for (int i = 0; i < m.getLength(); i++) {
						Node mm = m.item(i);
						members.add(mm.getTextContent());
					}
					PackageTypeMembers pdi = new PackageTypeMembers();
					pdi.setName(name);
					pdi.setMembers(members.toArray(new String[members.size()]));
					pd.add(pdi);
				}
			}
				
			com.sforce.soap.metadata.Package r = new com.sforce.soap.metadata.Package();
			r.setTypes(pd.toArray(new PackageTypeMembers[pd.size()]));
			r.setVersion(API_VERSION + "");
			return r;
		} catch (ParserConfigurationException pce) {
			throw new Exception("Cannot create XML parser", pce);
		} catch (IOException ioe) {
			throw new Exception(ioe);
		} catch (SAXException se) {
			throw new Exception(se);
		}
	}
	
	private void createMetadataConnection(final String username, final String password, final String loginUrl) 
			throws ConnectionException {
		final ConnectorConfig loginConfig = new ConnectorConfig();
		loginConfig.setAuthEndpoint(loginUrl);
		loginConfig.setServiceEndpoint(loginUrl);
		loginConfig.setManualLogin(true);
		//LoginResult loginResult = (new EnterpriseConnection(loginConfig)).login(
		//username, password);
//		final ConnectorConfig metadataConfig = new ConnectorConfig();
//		metadataConfig.setServiceEndpoint(loginResult.getMetadataServerUrl());
//		metadataConfig.setSessionId(loginResult.getSessionId());
//		this.metadataConnection = new MetadataConnection(metadataConfig);
		
	}
	//The sample client application retrieves the user's login credentials.
	// Helper function for retrieving user input from the console
	String getUserInput(String prompt) {
		System.out.print(prompt);
		try {
			return rdr.readLine();
		} catch (IOException ex) {
			return null;
		}
	}
}