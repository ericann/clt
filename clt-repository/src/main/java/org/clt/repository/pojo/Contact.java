package org.clt.repository.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the contact database table.
 * 
 */
@Entity
@NamedQuery(name="Contact.findAll", query="SELECT c FROM Contact c")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String email;

	private String mobile;

	private String name;
	
	private String password;
	
	private Boolean master;
	
	private String sessionId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date sessionUpdateTime;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="accountId")
	private Account account;

	public Contact() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getMaster() {
		return master;
	}

	public void setMaster(Boolean master) {
		this.master = master;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getSessionUpdateTime() {
		return sessionUpdateTime;
	}

	public void setSessionUpdateTime(Date sessionUpdateTime) {
		this.sessionUpdateTime = sessionUpdateTime;
	}
}