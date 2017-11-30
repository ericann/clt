package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the userapp database table.
 * 
 */
@Entity
@NamedQuery(name="UserApp.findAll", query="SELECT u FROM UserApp u")
public class UserApp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	//bi-directional many-to-one association to ConnectApp
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="connectAppId")
	private ConnectApp connectApp;

	//bi-directional many-to-one association to Contact
	@ManyToOne
	@JoinColumn(name="contactId")
	private Contact contact;

	public UserApp() {
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

	public ConnectApp getConnectapp() {
		return this.connectApp;
	}

	public void setConnectApp(ConnectApp connectApp) {
		this.connectApp = connectApp;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}