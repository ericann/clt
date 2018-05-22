package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the button database table.
 * 
 */
@Entity
@NamedQuery(name="Button.findAll", query="SELECT b FROM Button b")
public class Button implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="system-guid")
	@GenericGenerator(name="system-guid", strategy = "guid")
	private String id;

	private String buttonId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date createTime;

	private String displayInfo;

	private Boolean isDefault;

	private int limitCount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date updateTime;

	//bi-directional many-to-one association to LiveAgent
	@ManyToOne
	@JoinColumn(name="laId", referencedColumnName="id")
	private LiveAgent liveagent;

	//bi-directional many-to-one association to ChatMessage
	@OneToMany(mappedBy="button")
	private List<ChatMessage> chatmessages;

	public Button() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getButtonId() {
		return this.buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDisplayInfo() {
		return this.displayInfo;
	}

	public void setDisplayInfo(String displayInfo) {
		this.displayInfo = displayInfo;
	}

	public Boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public int getLimitCount() {
		return this.limitCount;
	}

	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public LiveAgent getLiveagent() {
		return this.liveagent;
	}

	public void setLiveagent(LiveAgent liveagent) {
		this.liveagent = liveagent;
	}

	public List<ChatMessage> getChatmessages() {
		return this.chatmessages;
	}
//
//	public void setChatmessages(List<ChatMessage> chatmessages) {
//		this.chatmessages = chatmessages;
//	}

	public ChatMessage addChatmessage(ChatMessage chatmessage) {
		getChatmessages().add(chatmessage);
		chatmessage.setButton(this);

		return chatmessage;
	}

	public ChatMessage removeChatmessage(ChatMessage chatmessage) {
		getChatmessages().remove(chatmessage);
		chatmessage.setButton(null);

		return chatmessage;
	}

}