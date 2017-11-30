package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.ForeignKey;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the liveagent database table.
 * 
 */
@Entity
@NamedQuery(name="LiveAgent.findAll", query="SELECT l FROM LiveAgent l")
public class LiveAgent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private String deploymentId;

	private String endPoint;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	//bi-directional many-to-one association to Button
	@OneToMany(mappedBy="liveagent")
	private Set<Button> buttons;

	//bi-directional many-to-one association to ChatMessage
	@OneToMany(mappedBy="liveagent")
	private Set<ChatMessage> chatmessages;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="accountId")
	private Account account;

	//bi-directional many-to-one association to Sfdc
	@ManyToOne
	@JoinColumn(name="orgId", referencedColumnName="orgId")
	private Sfdc sfdc;

	//bi-directional many-to-one association to WechatAccount
	@OneToMany(mappedBy="liveagent")
	private Set<WechatAccount> wechataccounts;

	public LiveAgent() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeploymentId() {
		return this.deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getEndPoint() {
		return this.endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Set<Button> getButtons() {
		return this.buttons;
	}

	public void setButtons(Set<Button> buttons) {
		this.buttons = buttons;
	}

	public Button addButton(Button button) {
		getButtons().add(button);
		button.setLiveagent(this);

		return button;
	}

	public Button removeButton(Button button) {
		getButtons().remove(button);
		button.setLiveagent(null);

		return button;
	}

	public Set<ChatMessage> getChatmessages() {
		return this.chatmessages;
	}

	public void setChatmessages(Set<ChatMessage> chatmessages) {
		this.chatmessages = chatmessages;
	}

	public ChatMessage addChatmessage(ChatMessage chatmessage) {
		getChatmessages().add(chatmessage);
		chatmessage.setLiveagent(this);

		return chatmessage;
	}

	public ChatMessage removeChatmessage(ChatMessage chatmessage) {
		getChatmessages().remove(chatmessage);
		chatmessage.setLiveagent(null);

		return chatmessage;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Sfdc getSfdc() {
		return this.sfdc;
	}

	public void setSfdc(Sfdc sfdc) {
		this.sfdc = sfdc;
	}

	public Set<WechatAccount> getWechataccounts() {
		return this.wechataccounts;
	}

	public void setWechataccounts(Set<WechatAccount> wechataccounts) {
		this.wechataccounts = wechataccounts;
	}

	public WechatAccount addWechataccount(WechatAccount wechataccount) {
		getWechataccounts().add(wechataccount);
		wechataccount.setLiveagent(this);

		return wechataccount;
	}

	public WechatAccount removeWechataccount(WechatAccount wechataccount) {
		getWechataccounts().remove(wechataccount);
		wechataccount.setLiveagent(null);

		return wechataccount;
	}

}