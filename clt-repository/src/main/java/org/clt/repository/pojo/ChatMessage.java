package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the chatmessage database table.
 * 
 */
@Entity
@NamedQuery(name="ChatMessage.findAll", query="SELECT c FROM ChatMessage c")
public class ChatMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String affinityToken;

	//@Column(insertable=false, updatable=false)
	private String bcId;

	//@Column(insertable=false, updatable=false)
	private String buttonId;

	private String openId;

	private int sequence;

	private String sessionId;

	private String sessionKey;

	private String wechatAccount;

	public ChatMessage() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAffinityToken() {
		return this.affinityToken;
	}

	public void setAffinityToken(String affinityToken) {
		this.affinityToken = affinityToken;
	}

	public String getBcId() {
		return this.bcId;
	}

	public void setBcId(String bcId) {
		this.bcId = bcId;
	}

	public String getButtonId() {
		return this.buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getSequence() {
		return this.sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionKey() {
		return this.sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getWechatAccount() {
		return this.wechatAccount;
	}

	public void setWechatAccount(String wechatAccount) {
		this.wechatAccount = wechatAccount;
	}

}