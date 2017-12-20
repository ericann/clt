package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the wechataccount database table.
 * 
 */
@Entity
@NamedQuery(name="WechatAccount.findAll", query="SELECT w FROM WechatAccount w")
public class WechatAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private Boolean firstTimeRefresh;

	private int limitCount;

	private Boolean refreshByUs;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	private Boolean useDefault;

	private String wechatAccessToken;

	private String wechatAccount;

	private String wechatAppId;

	private String wechatAppSecret;

	private String wechatToken;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="accountId")
	private Account account;

	//bi-directional many-to-one association to LiveAgent
	@ManyToOne
	@JoinColumn(name="laId")
	private LiveAgent liveagent;

	//bi-directional many-to-one association to WechatTicket
	@OneToMany(mappedBy="wechataccount")
	private List<WechatTicket> wechattickets;

	//bi-directional many-to-one association to WechatUser
	@OneToMany(mappedBy="wechataccount")
	private List<WechatUser> wechatusers;

	public WechatAccount() {
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

	public Boolean getFirstTimeRefresh() {
		return this.firstTimeRefresh;
	}

	public void setFirstTimeRefresh(Boolean firstTimeRefresh) {
		this.firstTimeRefresh = firstTimeRefresh;
	}

	public int getLimitCount() {
		return this.limitCount;
	}

	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}

	public Boolean getRefreshByUs() {
		return this.refreshByUs;
	}

	public void setRefreshByUs(Boolean refreshByUs) {
		this.refreshByUs = refreshByUs;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getUseDefault() {
		return this.useDefault;
	}

	public void setUseDefault(Boolean useDefault) {
		this.useDefault = useDefault;
	}

	public String getWechatAccessToken() {
		return this.wechatAccessToken;
	}

	public void setWechatAccessToken(String wechatAccessToken) {
		this.wechatAccessToken = wechatAccessToken;
	}

	public String getWechatAccount() {
		return this.wechatAccount;
	}

	public void setWechatAccount(String wechatAccount) {
		this.wechatAccount = wechatAccount;
	}

	public String getWechatAppId() {
		return this.wechatAppId;
	}

	public void setWechatAppId(String wechatAppId) {
		this.wechatAppId = wechatAppId;
	}

	public String getWechatAppSecret() {
		return this.wechatAppSecret;
	}

	public void setWechatAppSecret(String wechatAppSecret) {
		this.wechatAppSecret = wechatAppSecret;
	}

	public String getWechatToken() {
		return this.wechatToken;
	}

	public void setWechatToken(String wechatToken) {
		this.wechatToken = wechatToken;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public LiveAgent getLiveagent() {
		return this.liveagent;
	}

	public void setLiveagent(LiveAgent liveagent) {
		this.liveagent = liveagent;
	}

	public List<WechatTicket> getWechattickets() {
		return this.wechattickets;
	}

	public void setWechattickets(List<WechatTicket> wechattickets) {
		this.wechattickets = wechattickets;
	}

	public WechatTicket addWechatticket(WechatTicket wechatticket) {
		getWechattickets().add(wechatticket);
		wechatticket.setWechataccount(this);

		return wechatticket;
	}

	public WechatTicket removeWechatticket(WechatTicket wechatticket) {
		getWechattickets().remove(wechatticket);
		wechatticket.setWechataccount(null);

		return wechatticket;
	}

	public List<WechatUser> getWechatusers() {
		return this.wechatusers;
	}

	public void setWechatusers(List<WechatUser> wechatusers) {
		this.wechatusers = wechatusers;
	}

	public WechatUser addWechatuser(WechatUser wechatuser) {
		getWechatusers().add(wechatuser);
		wechatuser.setWechataccount(this);

		return wechatuser;
	}

	public WechatUser removeWechatuser(WechatUser wechatuser) {
		getWechatusers().remove(wechatuser);
		wechatuser.setWechataccount(null);

		return wechatuser;
	}

}