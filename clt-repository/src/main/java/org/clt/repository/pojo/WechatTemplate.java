package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the wechattemplate database table.
 * 
 */
@Entity
@NamedQuery(name="WechatTemplate.findAll", query="SELECT w FROM WechatTemplate w")
public class WechatTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date createTime;

	private String description;

	private String name;

	private String templateId;

	private String templateJSON;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date updateTime;

	//bi-directional many-to-one association to WechatAccount
	@ManyToOne
	@JoinColumn(name="wechatAccount", referencedColumnName="wechatAccount")
	private WechatAccount wechataccount;

	public WechatTemplate() {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateJSON() {
		return this.templateJSON;
	}

	public void setTemplateJSON(String templateJSON) {
		this.templateJSON = templateJSON;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public WechatAccount getWechataccount() {
		return this.wechataccount;
	}

	public void setWechataccount(WechatAccount wechataccount) {
		this.wechataccount = wechataccount;
	}

}