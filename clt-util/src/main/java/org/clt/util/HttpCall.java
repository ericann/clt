package org.clt.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class HttpCall {
	
	private static RestTemplate http;
	
	static {
		http = http == null ? new RestTemplate() : http;
		
		StringHttpMessageConverter m = new StringHttpMessageConverter(Charset.forName("UTF-8")); 
		List<HttpMessageConverter<?>> mL = new ArrayList<HttpMessageConverter<?>>();
		mL.add(m);
		http.setMessageConverters(mL);
	}
	
	private static <T, E> ResponseEntity<T> send(String url, HttpMethod method, HttpEntity<E> entity, Class<T> resType) {
		return http.exchange(url, method, entity, resType);
	}
	
	private static <T, E> ResponseEntity<T> post(String url, HttpEntity<E> entity, Class<T> resType) {
		return send(url, HttpMethod.POST, entity, resType);
	}
	
	private static <T, E> ResponseEntity<T> get(String url, HttpEntity<E> entity, Class<T> resType) {
		return send(url, HttpMethod.GET, entity, resType);
	}
	
	public static <T, E> ResponseEntity<T> post(String url, HttpHeaders headers, E body, Class<T> resType) {
		HttpEntity<E> entity = new HttpEntity<E>(body, headers);
		return post(url, entity, resType);
	}
	
	public static <T, E> ResponseEntity<T> get(String url, HttpHeaders headers, Class<T> resType) {
		HttpEntity<E> entity = new HttpEntity<E>(headers);
		return get(url, entity, resType);
	}
	
	public static <T, E> ResponseEntity<T> post(String url, Map<String, Object> params, HttpHeaders headers, E body, Class<T> resType) {
		HttpEntity<E> entity = new HttpEntity<E>(body, headers);
		return post(getURL(url, params), entity, resType);
	}
	
	public static <T, E> ResponseEntity<T> get(String url, Map<String, Object> params, HttpHeaders headers, Class<T> resType) {
		HttpEntity<E> entity = new HttpEntity<E>(headers);
		return get(getURL(url, params), entity, resType);
	}
	
	public static <E> ResponseEntity<String> post(String url, Map<String, Object> params, HttpHeaders headers, E body) {
		HttpEntity<E> entity = new HttpEntity<E>(body, headers);
		return post(getURL(url, params), entity, String.class);
	}
	
	public static <E> ResponseEntity<String> get(String url, Map<String, Object> params, HttpHeaders headers) {
		HttpEntity<E> entity = new HttpEntity<E>(headers);
		return get(getURL(url, params), entity, String.class);
	}
	
	private static String getURL(String url, Map<String, Object> params) {
		StringBuilder sb = new StringBuilder(url);
		sb.append(url.contains("?") ? "&" : "?");
		for(String k : params.keySet()) {
			sb.append(k).append("=").append(params.get(k)).append("&");
		}
		
		return sb.toString();
	}
}
