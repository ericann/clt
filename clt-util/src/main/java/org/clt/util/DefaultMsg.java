package org.clt.util;

import java.util.HashMap;
import java.util.Map;

public class DefaultMsg {
	
	public final static String BUTTON_LIMIT = " The connection is reach the limit, wait or contact service provider please.";
	public final static String ORG_LIMIT = " The connection is reach the limit, wait or contact service provider please.";
	
	//默认org、button限制连接数
	public final static String BUTTON_C_COUNT = "3";
	public final static String ORG_C_COUNT = "3";
	
	//默认 无在线客服 提示信息
	public final static String NO_AGENTS_ALERT = "There are currently no agents online, try again later pls.";
	
	//错误编码
	public final static String E_0 = "0";
	public final static String E__1 = "-1";
	//Failed.
	public final static String E_1 = "1";
	
	public final static String E_1001 = "1001";
	
	//Login information is not correct or unsign up.
	public final static String E_1011 = "1011";
	
	//Http Error
	public final static String H_400 = "2001";
	
	//Not Support Error
	public final static String N_0 = "3001";
	
	//JSON Error
	public final static String J_0 = "4001";
	
	//通用异常信息
	public final static String E_0_M = "Success.";
	public final static String E_1_M = "Unknown Error.";
	public final static String H_400_M = "400 Bad Request.Check your input information, please.";
	
	//public final static String E_1001 = "Refresh Tokens Error.";
	
	public final static String N_0_M = "Not Support.";
	
	public final static String J_0_M = "JSON Exception: {0}.";
	
	//SFDC Oauth2.0
	public final static String O_WS_CONFIRM = "authorize?response_type=code&client_id={0}&redirect_uri={1}";
	public final static String O_UA_CONFIRM = "authorize?response_type=token&client_id={0}&redirect_uri={1}";
	public final static String O_UP = "token?grant_type=password&client_id={0}&client_secret={1}&username={2}&password={3}";
	public final static String O_CODE = "token?grant_type=authorization_code&code={0}&client_id={1}&client_secret={2}&redirect_uri={3}";
	
	public final static String BASE_URL = "https://{0}.salesforce.com/services/oauth2/";
	
	//SFDC Live Agent URL
	public final static String LA_CREATE_SESSION = "/System/SessionId";
	public final static String LA_CHASITORINIT = "/Chasitor/ChasitorInit";
	public final static String LA_AVAILABILITY = "/Visitor/Availability";
	public final static String LA_CHATMESSAGE = "/Chasitor/ChatMessage";
	public final static String LA_MESSAGES = "/System/Messages";
	public final static String LA_VISITORID = "/Visitor/VisitorId";
	
	//SFDC Live Agent Header
	public final static String LA_H_VERSION = "X-LIVEAGENT-API-VERSION";
	public final static String LA_H_AFFINITY = "X-LIVEAGENT-AFFINITY";
	public final static String LA_H_KEY = "X-LIVEAGENT-SESSION-KEY";
	public final static String LA_H_SEQUENCE = "X-LIVEAGENT-SEQUENCE";
	
	//Wechat API URL
	public final static String WC_SENDMSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={0}";
	public final static String WC_CREATEQR = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={0}";
	public final static String WC_GETQRIMG = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={0}";
	//public final static String WC_GETQRIMG = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={0}";
	public final static String WC_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
	public final static String WC_QR_SHOW = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={ticked}";
	//Wechat template
	public final static String WC_T_TEXTMSG = "{\"touser\":\"\",\"msgtype\":\"text\",\"text\":{\"content\":\"\"}}";
	public final static String WC_T_QRSHORTLIVE = "{\"expire_seconds\": {0}, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": {1}}}}";
	
	//Object Mapping
	public final static String ACC = "Account";
	public final static String CON = "Contact";
	public final static String BC = "BasicConfig";
	public final static String B = "Button";
	public final static String CM = "ChatMessage";
	
	//Field Metadata
	public final static String M_FIELD = "{\"label\":null,\"type\":null,\"default\":null,\"error\":null,\"readonly\":null,\"help\":null,\"link\":null,\"ref\":null}";
	public final static String M_OBJECT = "{\"title\":null,\"buttons\":[],\"fields\":[]}";
	
	//Session Expire second
	public final static String SESSION_EXPIRE = "7200";
	
	//Login QR Image
	public final static String LOGIN_QR_EXPIRE = "120";
	
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
	
	public static Map<String, Object> getErrorResult(String code, String replaceStr, Object data) {
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
	
	public static Map<String, Object> getErrorResult(String code, Object data) {
		return getErrorResult(code, null, data);
	}
	
	public static Map<String, Object> getErrorResult(String code) {
		return getErrorResult(code, null, null);
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
