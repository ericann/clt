package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


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
	private List<Button> buttons;

	//bi-directional many-to-one association to ChatMessage
	@OneToMany(mappedBy="liveagent")
	private List<ChatMessage> chatmessages;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="accountId")
	private Account account;

	//bi-directional many-to-one association to Sfdc
	@ManyToOne
	@JoinColumn(name="orgId", referencedColumnName="orgId")
	private Sfdc sfdc;

	//bi-directional many-to-one association to WechatAccount
	@OneToMany(mappedBy="liveAgent")
	private List<WechatAccount> wechatAccounts;

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

	public List<Button> getButtons() {
		return this.buttons;
	}

	public void setButtons(List<Button> buttons) {
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

	public List<ChatMessage> getChatmessages() {
		return this.chatmessages;
	}

	public void setChatmessages(List<ChatMessage> chatmessages) {
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

	public List<WechatAccount> getWechataccounts() {
		return this.wechatAccounts;
	}

	public void setWechataccounts(List<WechatAccount> wechataccounts) {
		this.wechatAccounts = wechataccounts;
	}

}