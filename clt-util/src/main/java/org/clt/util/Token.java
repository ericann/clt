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
	public static Map<String, Object> parse(String token) {
		Map<String, Object> result = null;
	    try {
	    	result = (Map<String, Object>)parser().setSigningKey(KEY).parse(token).getBody();
	    	//return body;
	    } catch (JwtException ex) {
	    	ex.printStackTrace();
	    }
	    
	    return result;
	}
	
	public static void main(String[] args) {
		String at = generateAccessToken(1000 * 60 * 60, "eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAI1UTW_VQAz8KyjnGq3309tbizhUghNCXJ6EvN7dkkLzUF8KSFX_O05SldL28HKL5RnP2JPcDVfzOJwOd7tB9tPXia_bbjjdDe9vRtkNJ2v1oq6liIgU0EJlZvAOO1DIAXoupVhkxJRXCOYuvQhDSaiNyRCwI4HSmKlxwkpFGXUkZWTh6iGYmsDHSpBDE3DVYcKSnO9x68RgY3aRAHPw4E0ywLE08LlQELSUvNs69-WqyXxYXu5VzKOjjzzxZbtu07yKHDdTx_EuRCEY21M04G0L4GsIwLkESDZb02vJncMLBbvhTGR_u8zUYclR99ELJMl1oUiQo7WQqRtH5EOzfhX3bj_NLBuoZ4vJcQUMonO7KqCoOxJjbG4SRa-xKXy0es6H9uZi6vuba57H_fTU8HE2Frpu0GQUC8xVOz11PSgZSD3UxFYv5OQVw1-afOP5P9tI2WTWkcVGJWIEbsZDtaWRL1VSx1XiBv18aDcrjnqlSi5AzlJUqktQgkavRYPFSHPex2fON4anfo9zsZD4XI2NPUOMjsCjL7qZFKHUwDU78dm9jNhu-DD-ameX7cErlZgCaqI7Fs0-Wr2W7W35UtAwRWOIV3mfepUN4QgbW4ZaURNIUT-UoNgSnSvLpS3aFXF-O8_LLZcjVgxLNnWjRjS13kLxPUDJuk4yzlR6nolt3r-9HOf2Xp_hZBgPB_1HyI_57e_x-7gUeB5OVYMjn5wLJ0P78_OhEEx24f4vovz92lgEAAA.8zxtBd0nyN_wkMGGGD2P3qHDaQ-tMB89tOVfO3XCL9g");
		Map<String, Object> result = parse("eyJhbGciOiJIUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAI1UTW_VQAz8KyjnGq13vbve3lrEoRKcEOLyJOT9Kik0D_WlgFT1v-MkVSltDy-3WJ7xjD3J3XA1j8PpcLcbyn76Osl12w2nu-H9zVh2w8lavahrKSAie7RQRQTIYQf2yUNPOWeLghjTCsHUS89FIEfUxmgYxHGB3ES4ScTKWRl1JCeUIpXAmxqBQmVIvhVw1WHEHB31sHWityG5wIDJE5CJBiTkBpQy-4KWI7mtc5-vWpkPy8u9inl09FEmuWzXbZpXkeNm6jjehch7Y3sMBsg2D1S9B0nZQ7TJml5z6uJfKNgNZ6Xsb5eZOiw67hSoQCypLhQRUrAWEnfjmMk3S6u4d_tplrKBerIYnVRAX3RuVwUcdEfFGJtaCUWvsSl8tHouh_bmYur7m2uZx_301PBxNha6btAkLBZEqnYSdz0oG4jd1yhWL-TKK4a_tPJN5v9sIyeTREdmG5RIEKQZgmpzY8q1xI6rxA36-dBuVhz3ypWdh5RKVqkuQvYavRYMZlOaIwrPnG8MT_0e52IhoVSNDT1BCI6BkLJuJgbI1UtNrlByLyO2Gz6Mv9rZZXvwyjlEj5rojlmzj1avZXtbvhQ0wsEYllXep17LhnCMTaxAragJ5KAfildsDs7l5dIW7Yo4v53n5ZbLESv6JZu6UVM0tWQhU_eQk66TjTOVn2dim_dvL8e5vddnOBnGw0H_EeXH_Pb3-H1cCjIPp6rBMbGleDK0Pz8fCh6Z4v1fzP3ovlgEAAA.zfEAJLKDbMDOCYabQ2BiqPjhA699iNu9hwWeVtPynyY");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(at);
		System.out.println(result);
		//Integer iat = (Integer)result.get("iat");
		//Integer exp = (Integer)result.get("exp");
		//System.out.println(simpleDateFormat.format(new Date(iat)));
		//System.out.println(simpleDateFormat.format(new Date(exp)));
	}
}
