package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the wechatticket database table.
 * 
 */
@Entity
@NamedQuery(name="WechatTicket.findAll", query="SELECT w FROM WechatTicket w")
public class WechatTicket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date createTime;

	private String ticket;

	private String type;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date updateTime;

	//bi-directional many-to-one association to Contact
	@ManyToOne
	@JoinColumn(name="conId")
	private Contact contact;

	//bi-directional many-to-one association to WechatAccount
	@ManyToOne
	@JoinColumn(name="wechatAccount", referencedColumnName="wechatAccount")
	private WechatAccount wechataccount;

	//bi-directional many-to-one association to WechatUser
	@ManyToOne
	@JoinColumn(name="wechatUserId")
	private WechatUser wechatuser;
	
	private Boolean isValid;

	public WechatTicket() {
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

	public String getTicket() {
		return this.ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public WechatAccount getWechataccount() {
		return this.wechataccount;
	}

	public void setWechataccount(WechatAccount wechataccount) {
		this.wechataccount = wechataccount;
	}

	public WechatUser getWechatuser() {
		return this.wechatuser;
	}

	public void setWechatuser(WechatUser wechatuser) {
		this.wechatuser = wechatuser;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	
}