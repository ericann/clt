package org.clt.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XML {
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> parse(String xml) {
		Map<String, String> result = new HashMap<String, String>();
		
		Document doc;
		try {
			doc = DocumentHelper.parseText(URLDecoder.decode(xml, "utf-8"));
			Element root = doc.getRootElement();
			
			for(Element e : (List<Element>)root.elements()) {
				result.put(e.getName(), e.getText());
			}
		} catch (UnsupportedEncodingException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
