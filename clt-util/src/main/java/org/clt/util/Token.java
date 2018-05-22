package org.clt.util;

import static io.jsonwebtoken.Jwts.builder;
import static io.jsonwebtoken.Jwts.claims;
import static io.jsonwebtoken.Jwts.parser;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.impl.crypto.MacProvider.generateKey;
import static java.security.SecureRandom.getInstance;
import static org.clt.util.DateUtil.addSeconds;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
	
	public static String generateAccessToken(int expiresIn, String info) {
		//hasLength(username, "username");
		final Date now = new Date();
		return builder().setClaims(claims().setId(info)).signWith(HS256, KEY).setIssuer(HEADING)
	        .setIssuedAt(now).setExpiration(addSeconds(now, expiresIn))
	        .compressWith(new GzipCompressionCodec()).compact();
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parse(String token) throws JwtException {
		Map<String, Object> result = (Map<String, Object>)parser().setSigningKey(KEY).parse(token).getBody();
	    return result;
	}
	
	public static void main(String[] args) {
		String at = generateAccessToken(1000 * 60 * 60, "1");
		Map<String, Object> result = parse(at);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(at);
		System.out.println(result);
		Integer iat = (Integer)result.get("iat");
		Integer exp = (Integer)result.get("exp");
		System.out.println(simpleDateFormat.format(new Date(iat)));
		System.out.println(simpleDateFormat.format(new Date(exp)));

		long expried = (Integer) result.get("exp");
		long i = 1529180110;
		System.out.println("i: \t\t" + i * 1000);
		System.out.println("expried: \t" + expried);
		System.out.println("expried * 1000: " + (expried * 1000));
		System.out.println("current: " + new Date().getTime());
		System.out.println("add: " + String.valueOf(expried * 1000 - new Date().getTime()));
		System.out.println("boolean: " + (((expried * 1000 - new Date().getTime()) / 1000 > 0) ? true : false));
	}
}
