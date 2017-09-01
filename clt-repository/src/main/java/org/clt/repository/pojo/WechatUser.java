package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the wechatuser database table.
 * 
 */
@Entity
@NamedQuery(name="WechatUser.findAll", query="SELECT w FROM WechatUser w")
public class WechatUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String bindTicket;

	private String city;

	private String country;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private String headimgurl;

	private String language;

	private String nickname;

	private String openId;

	private String province;

	private String remark;

	private Boolean sex;

	@Temporal(TemporalType.TIMESTAMP)
	private Date subscribeTime;

	private String unionid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	//bi-directional many-to-one association to WechatTicket
	@OneToMany(mappedBy="wechatuser")
	private List<WechatTicket> wechattickets;

	//bi-directional many-to-one association to Contact
	@ManyToOne
	@JoinColumn(name="contactId")
	private Contact contact;

	//bi-directional many-to-one association to WechatAccount
	@ManyToOne
	@JoinColumn(name="wechatAccount")
	private WechatAccount wechataccount;

	public WechatUser() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBindTicket() {
		return this.bindTicket;
	}

	public void setBindTicket(String bindTicket) {
		this.bindTicket = bindTicket;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHeadimgurl() {
		return this.headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getSex() {
		return this.sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public Date getSubscribeTime() {
		return this.subscribeTime;
	}

	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getUnionid() {
		return this.unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<WechatTicket> getWechattickets() {
		return this.wechattickets;
	}

	public void setWechattickets(List<WechatTicket> wechattickets) {
		this.wechattickets = wechattickets;
	}

	public WechatTicket addWechatticket(WechatTicket wechatticket) {
		getWechattickets().add(wechatticket);
		wechatticket.setWechatuser(this);

		return wechatticket;
	}

	public WechatTicket removeWechatticket(WechatTicket wechatticket) {
		getWechattickets().remove(wechatticket);
		wechatticket.setWechatuser(null);

		return wechatticket;
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

}