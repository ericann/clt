package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private Boolean master;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	//bi-directional many-to-one association to Contact
	@OneToMany(mappedBy="account")
	private List<Contact> contacts;

	//bi-directional many-to-one association to LiveAgent
	@OneToMany(mappedBy="account")
	private List<LiveAgent> liveagents;

	//bi-directional many-to-one association to WechatAccount
	@OneToMany(mappedBy="account")
	private List<WechatAccount> wechataccounts;

	public Account() {
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

	public Boolean getMaster() {
		return this.master;
	}

	public void setMaster(Boolean master) {
		this.master = master;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Contact addContact(Contact contact) {
		getContacts().add(contact);
		contact.setAccount(this);

		return contact;
	}

	public Contact removeContact(Contact contact) {
		getContacts().remove(contact);
		contact.setAccount(null);

		return contact;
	}

	public List<LiveAgent> getLiveagents() {
		return this.liveagents;
	}

	public void setLiveagents(List<LiveAgent> liveagents) {
		this.liveagents = liveagents;
	}

	public LiveAgent addLiveagent(LiveAgent liveagent) {
		getLiveagents().add(liveagent);
		liveagent.setAccount(this);

		return liveagent;
	}

	public LiveAgent removeLiveagent(LiveAgent liveagent) {
		getLiveagents().remove(liveagent);
		liveagent.setAccount(null);

		return liveagent;
	}

	public List<WechatAccount> getWechataccounts() {
		return this.wechataccounts;
	}

	public void setWechataccounts(List<WechatAccount> wechataccounts) {
		this.wechataccounts = wechataccounts;
	}

	public WechatAccount addWechataccount(WechatAccount wechataccount) {
		getWechataccounts().add(wechataccount);
		wechataccount.setAccount(this);

		return wechataccount;
	}

	public WechatAccount removeWechataccount(WechatAccount wechataccount) {
		getWechataccounts().remove(wechataccount);
		wechataccount.setAccount(null);

		return wechataccount;
	}

}