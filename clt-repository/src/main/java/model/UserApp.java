package model;

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

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	//bi-directional many-to-one association to ConnectApp
	@ManyToOne
	@JoinColumn(name="connectAppId")
	private ConnectApp connectapp;

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

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public ConnectApp getConnectapp() {
		return this.connectapp;
	}

	public void setConnectapp(ConnectApp connectapp) {
		this.connectapp = connectapp;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}