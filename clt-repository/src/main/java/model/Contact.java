package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private String email;

	private Boolean master;

	private String mobile;

	private String name;

	private String openId;

	private String password;

	private String sessionId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date sessionUpdateTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="accountId")
	private Account account;

	//bi-directional many-to-one association to UserApp
	@OneToMany(mappedBy="contact")
	private List<UserApp> userapps;

	public Contact() {
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getMaster() {
		return this.master;
	}

	public void setMaster(Boolean master) {
		this.master = master;
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

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getSessionUpdateTime() {
		return this.sessionUpdateTime;
	}

	public void setSessionUpdateTime(Date sessionUpdateTime) {
		this.sessionUpdateTime = sessionUpdateTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<UserApp> getUserapps() {
		return this.userapps;
	}

	public void setUserapps(List<UserApp> userapps) {
		this.userapps = userapps;
	}

	public UserApp addUserapp(UserApp userapp) {
		getUserapps().add(userapp);
		userapp.setContact(this);

		return userapp;
	}

	public UserApp removeUserapp(UserApp userapp) {
		getUserapps().remove(userapp);
		userapp.setContact(null);

		return userapp;
	}

}