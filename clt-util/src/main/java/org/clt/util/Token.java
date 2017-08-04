package org.clt.util;

import static io.jsonwebtoken.Jwts.builder;
import static io.jsonwebtoken.Jwts.claims;
import static io.jsonwebtoken.Jwts.parser;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.impl.crypto.MacProvider.generateKey;
import static java.security.SecureRandom.getInstance;
import static org.clt.util.DateUtil.addSeconds;
import static org.springframework.util.Assert.hasLength;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Date;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;

public class Token {
	
	public static final String ALGORITHM = "SHA1PRNG";
	public static final String ALGORITHM_PROVIDER = "SUN";
	public static final String HEADING = "clt.wiki";
	
	private static SecureRandom RANDOM;
	static {
		try {
			RANDOM = getInstance(ALGORITHM, ALGORITHM_PROVIDER);
			RANDOM.setSeed(RANDOM.generateSeed(128));
		} catch (NoSuchAlgorithmException | NoSuchProviderException ignored) {
		}
	}

    public static final Key KEY = generateKey(HS256, RANDOM);
	
    /**
     * 生成access_token
     *
     * @param expiresIn 有效期
     * @param username 用户名
     * @return access_token
     */
	
	public static String generateHttpToken(int expiresIn, String username) {
		//hasLength(username, "username");
		final Date now = new Date();
		return builder().setClaims(claims().setId(username)).signWith(HS256, KEY).setIssuer(HEADING)
	        .setIssuedAt(now).setExpiration(addSeconds(now, expiresIn))
	        .compressWith(new GzipCompressionCodec()).compact();
	}
	
	public static String parse(String token) {
	    //hasLength(token, "token");
		String body = null;
	    try {
	    	body = parser().setSigningKey(KEY).parse(token).getBody().toString();
	    	//return body;
	    } catch (JwtException ex) {
	    
	    }
	    
	    return body;
	}
	
	public static void main(String[] args) {
		String at = generateHttpToken(3600, "123");
		System.out.println(at);
		System.out.println(parse(at));
	}
}
