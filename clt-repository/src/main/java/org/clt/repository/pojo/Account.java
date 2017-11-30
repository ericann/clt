package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(fetch=FetchType.EAGER)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private Boolean master;
	
	@Basic(fetch=FetchType.EAGER)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	//bi-directional many-to-one association to Contact
	@OneToMany(mappedBy="account")
	private Set<Contact> contacts;

	//bi-directional many-to-one association to LiveAgent
	@OneToMany(mappedBy="account")
	private Set<LiveAgent> liveagents;

	//bi-directional many-to-one association to WechatAccount
	@OneToMany(mappedBy="account")
	private Set<WechatAccount> wechatAccounts;

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

	public Set<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(Set<Contact> contacts) {
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

	public Set<LiveAgent> getLiveagents() {
		return this.liveagents;
	}

	public void setLiveagents(Set<LiveAgent> liveagents) {
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

	public Set<WechatAccount> getWechatAccounts() {
		return this.wechatAccounts;
	}

	public void setWechatAccounts(Set<WechatAccount> wechatAccounts) {
		this.wechatAccounts = wechatAccounts;
	}

	public WechatAccount addWechatAccount(WechatAccount wechatAccount) {
		getWechatAccounts().add(wechatAccount);
		wechatAccount.setAccount(this);

		return wechatAccount;
	}

	public WechatAccount removeWechatAccount(WechatAccount wechatAccount) {
		getWechatAccounts().remove(wechatAccount);
		wechatAccount.setAccount(null);

		return wechatAccount;
	}

}