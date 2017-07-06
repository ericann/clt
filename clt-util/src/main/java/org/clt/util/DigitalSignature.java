package org.clt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigitalSignature {
	
	public static String SHA1(String original) throws NoSuchAlgorithmException {
		MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
		digest.update(original.getBytes());
		byte messageDigest[] = digest.digest();
		StringBuffer hexString = new StringBuffer();
		
		for (int i = 0; i < messageDigest.length; i++) {
			String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexString.append(0);
			}
			hexString.append(shaHex);
		}
		
		return hexString.toString();
	}
	
	public static String MD5() {
		return null;
	}
}
