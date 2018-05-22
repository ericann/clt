package org.clt.data.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.clt.service.TokenService;
import org.clt.service.base.GenericService;
import org.hibernate.id.IdentifierGenerationException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Controller
public abstract class DataListenerImpl<T extends Serializable, PK extends Serializable> implements DataListener<T, PK> {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TokenService tokenService;
	
	protected GenericService<T, PK> genericService;
	
	protected String userId;
	
	protected String token;
	
	@SuppressWarnings("unused")
	private Class<T> cls;
	
	@SuppressWarnings("unchecked")
	public DataListenerImpl(GenericService<T, PK> genericService) {
		this.genericService = genericService;
		
		@SuppressWarnings("rawtypes")
		Class clz = this.getClass();
		ParameterizedType type = (ParameterizedType) clz.getGenericSuperclass();
		Type[] types = type.getActualTypeArguments();
		cls = (Class<T>) types[0];
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findAll(@RequestHeader("CLT-ACCESS-TOKEN") String token) {
		this.token = token;
		return JSONObject.valueToString(this.genericService.findAllByContactId(this.getUserId()));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findById(@RequestHeader("CLT-ACCESS-TOKEN") String token, 
			@PathVariable("id") String id) {
		return JSONObject.valueToString(this.genericService.findById(id));
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String save(@RequestHeader("CLT-ACCESS-TOKEN") String token, 
			@RequestBody T t) {
		
		JSONObject result = new JSONObject();

		t = this.genericService.save(t);
		result.put("code", 0);
		result.put("id", new JSONObject(t).getString("id"));
		result.put("msg", "Save successd.");
		
		return result.toString();
//		T obj = null;
//		JSONObject result = new JSONObject();
//		try {
//			obj = this.genericService.save(this.convert(body, cls));
//			result.put("code", 0);
//			result.put("id", new JSONObject(obj).getString("id"));
//			result.put("msg", "Save successd.");
//		} catch(UnrecognizedPropertyException upe) {
//			result.put("code", 1);
//			result.put("msg", "Unknown properties.");
//		} catch(IdentifierGenerationException ige) {
//			result.put("code", 2);
//			result.put("msg", ige.getCause());
//		} catch(MySQLIntegrityConstraintViolationException ex) {
//			result.put("code", 3);
//			result.put("msg", ex.getCause());
//		} catch(Exception ex) {
//			result.put("code", -1);
//			result.put("msg", ex.getMessage());
//		}
		
		//return result.toString();
	}
	
	public String getUserId() {
		return userId != null ? userId : (String)tokenService.getTokenInfo(token, "jti");
	}
	
	@SuppressWarnings("unused")
	private T convert (String body, Class<T> beanType) 
			throws UnrecognizedPropertyException, IdentifierGenerationException,
			MySQLIntegrityConstraintViolationException, Exception {
		final ObjectMapper MAPPER = new ObjectMapper(); 
        T t = MAPPER.readValue(body, beanType);
        return t;
	}
	
}
