package org.clt.util;

import java.util.HashMap;
import java.util.Map;

public class DefaultMsg {
	
	private final static String BUTTON_LIMIT = " The connection is reach the limit, wait or contact service provider please.";
	private final static String ORG_LIMIT = " The connection is reach the limit, wait or contact service provider please.";
	
	//默认org、button限制连接数
	private final static String BUTTON_C_COUNT = "3";
	private final static String ORG_C_COUNT = "3";
	
	//默认 无在线客服 提示信息
	private final static String NO_AGENTS_ALERT = "There are currently no agents online, try again later pls.";
	
	//错误编码
	private final static String E_0 = "0";
	private final static String E_1 = "-1";
	
	//Http Error
	private final static String H_400 = "2001";
	
	//Not Support Error
	private final static String N_0 = "3001";
	
	//JSON Error
	private final static String J_0 = "4001";
	
	//通用异常信息
	private final static String E_0_M = "Success.";
	private final static String E_1_M = "Unknown Error.";
	private final static String H_400_M = "400 Bad Request.Check your input information, please.";
	
	private final static String E_1001 = "Refresh Tokens Error.";
	
	private final static String N_0_M = "Not Support.";
	
	private final static String J_0_M = "JSON Exception: {0}.";
	
	//SFDC Oauth2.0
	private final static String O_WS_CONFIRM = "authorize?response_type=code&client_id={0}&redirect_uri={1}";
	private final static String O_UA_CONFIRM = "authorize?response_type=token&client_id={0}&redirect_uri={1}";
	private final static String O_UP = "token?grant_type=password&client_id={0}&client_secret={1}&username={2}&password={3}";
	private final static String O_CODE = "token?grant_type=authorization_code&code={0}&client_id={1}&client_secret={2}&redirect_uri={3}";
	
	private final static String BASE_URL = "https://{0}.salesforce.com/services/oauth2/";
	
	//Object Mapping
	private final static String ACC = "Account";
	private final static String CON = "Contact";
	private final static String BC = "BasicConfig";
	private final static String B = "Button";
	private final static String CM = "ChatMessage";
	
	//Field Metadata
	private final static String M_FIELD = "{\"label\":null,\"type\":null,\"default\":null,\"error\":null,\"readonly\":null,\"help\":null,\"link\":null,\"ref\":null}";
	private final static String M_OBJECT = "{\"title\":null,\"buttons\":[],\"fields\":[]}";
	
	//Session Expire second
	private final static String SESSION_EXPIRE = "7200";
	
	public static String get(String name) {
		String result = null;
		
		try {
			result = (String) DefaultMsg.class.getDeclaredField(name).get(name);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Map<String, Object> initErrorResult(String code) {
		String errCode = get(code);
		String errMsg = get(code + "_M");
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(!(errCode.isEmpty() || errMsg.isEmpty())) {
			result.put("errCode", errCode);
			result.put("errMsg", errMsg);
		}
		
		return result;
	}
	
	public static Map<String, Object> getErrorResult(String code, Object data) {
		String errCode = get(code);
		String errMsg = get(code + "_M");
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(!(errCode.isEmpty() || errMsg.isEmpty() || data != null)) {
			result.put("errCode", errCode);
			result.put("errMsg", errMsg);
			result.put("data", data);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(DefaultMsg.get("button_limit"));
		System.out.println(DefaultMsg.get("button_limi"));
		
		//获取类名测试
		System.out.println("-- " + new HttpCall().getClass().getSimpleName());
		Object o = new HttpCall();
		System.out.println("-- " + o.getClass().getSimpleName());
	}
}
