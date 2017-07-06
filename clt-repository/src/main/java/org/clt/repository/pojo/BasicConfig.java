package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the basicconfig database table.
 * 
 */
@Entity
@NamedQuery(name="BasicConfig.findAll", query="SELECT b FROM BasicConfig b")
public class BasicConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private Boolean firstTimeRefresh;

	private int limitCount;

	private String liveAgentDeploymentId;

	private String liveAgentEndPoint;

	private String liveAgentOrgId;

	private Boolean refreshByOthers;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	private String wechatAccessToken;

	private String wechatAccount;

	private String wechatAppId;

	private String wechatAppSecret;

	private String wechatToken;
	
	private String accountId;

	//bi-directional many-to-one association to Button
	@OneToMany(mappedBy="basicConfig")
	private List<Button> buttons;

	public BasicConfig() {
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

	public String getLiveAgentDeploymentId() {
		return this.liveAgentDeploymentId;
	}

	public void setLiveAgentDeploymentId(String liveAgentDeploymentId) {
		this.liveAgentDeploymentId = liveAgentDeploymentId;
	}

	public String getLiveAgentEndPoint() {
		return this.liveAgentEndPoint;
	}

	public void setLiveAgentEndPoint(String liveAgentEndPoint) {
		this.liveAgentEndPoint = liveAgentEndPoint;
	}

	public String getLiveAgentOrgId() {
		return this.liveAgentOrgId;
	}

	public void setLiveAgentOrgId(String liveAgentOrgId) {
		this.liveAgentOrgId = liveAgentOrgId;
	}

	public Boolean getRefreshByOthers() {
		return this.refreshByOthers;
	}

	public void setRefreshByOthers(Boolean refreshByOthers) {
		this.refreshByOthers = refreshByOthers;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public List<Button> getButtons() {
		return this.buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	public Button addButton(Button button) {
		getButtons().add(button);
		button.setBasicconfig(this);

		return button;
	}

	public Button removeButton(Button button) {
		getButtons().remove(button);
		button.setBasicconfig(null);

		return button;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}