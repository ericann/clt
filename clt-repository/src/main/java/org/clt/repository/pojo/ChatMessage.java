package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date createTime;

	private String openId;

	private int sequence;

	private String sessionId;

	private String sessionKey;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date updateTime;
	
	private Boolean valid;

	private String wechatAccount;

	//bi-directional many-to-one association to Button
	@ManyToOne
	@JoinColumn(name="buttonId")
	private Button button;

	//bi-directional many-to-one association to LiveAgent
	@ManyToOne
	@JoinColumn(name="laId")
	private LiveAgent liveagent;

	public ChatMessage() {
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
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

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getWechatAccount() {
		return this.wechatAccount;
	}

	public void setWechatAccount(String wechatAccount) {
		this.wechatAccount = wechatAccount;
	}

	public Button getButton() {
		return this.button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public LiveAgent getLiveagent() {
		return this.liveagent;
	}

	public void setLiveagent(LiveAgent liveagent) {
		this.liveagent = liveagent;
	}

}