package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

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
	@GeneratedValue(generator="system-guid")
	@GenericGenerator(name="system-guid", strategy = "guid")
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date createTime;

	private String description;

	private Boolean firstTimeRefresh;

	private int limitCount;

	private String name;

	private Boolean refreshByUs;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date updateTime;

	private Boolean useDefault;

	private String wechatAccessToken;

	private String wechatAccount;

	private String wechatAppId;

	private String wechatAppSecret;

	private String wechatToken;

	//bi-directional many-to-one association to WechatTemplate
	@OneToMany(mappedBy="wechataccount")
	private List<WechatTemplate> wechattemplates;
	
	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="accountId")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name="laId")
	private LiveAgent liveAgent;

	public WechatAccount() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getFirstTimeRefresh() {
		return this.firstTimeRefresh;
	}

	public void setFirstTimeRefresh(Boolean firstTimeRefresh) {
		this.firstTimeRefresh = firstTimeRefresh;
	}

	public LiveAgent getLiveAgent() {
		return this.liveAgent;
	}

	public void setLiveAgent(LiveAgent liveAgent) {
		this.liveAgent = liveAgent;
	}

	public int getLimitCount() {
		return this.limitCount;
	}

	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<WechatTemplate> getWechattemplates() {
		return this.wechattemplates;
	}

	public void setWechattemplates(List<WechatTemplate> wechattemplates) {
		this.wechattemplates = wechattemplates;
	}

	public WechatTemplate addWechattemplate(WechatTemplate wechattemplate) {
		getWechattemplates().add(wechattemplate);
		wechattemplate.setWechataccount(this);

		return wechattemplate;
	}

	public WechatTemplate removeWechattemplate(WechatTemplate wechattemplate) {
		getWechattemplates().remove(wechattemplate);
		wechattemplate.setWechataccount(null);

		return wechattemplate;
	}

}