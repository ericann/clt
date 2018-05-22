package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the sfdc database table.
 * 
 */
@Entity
@NamedQuery(name="Sfdc.findAll", query="SELECT s FROM Sfdc s")
public class Sfdc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="system-guid")
	@GenericGenerator(name="system-guid", strategy = "guid")
	private String id;

	private String description;

	private String domain;

	private String orgId;

	@Column(name="package")
	private String package_;

	private String password;

	private String securityToken;

	private String username;

	//bi-directional many-to-one association to LiveAgent
	@OneToMany(mappedBy="sfdc", cascade=CascadeType.PERSIST)
	private List<LiveAgent> liveagents = new ArrayList<LiveAgent>();

	public Sfdc() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPackage_() {
		return this.package_;
	}

	public void setPackage_(String package_) {
		this.package_ = package_;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurityToken() {
		return this.securityToken;
	}

	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<LiveAgent> getLiveagents() {
		return this.liveagents;
	}

//	public void setLiveagents(List<LiveAgent> liveagents) {
//		this.liveagents = liveagents;
//	}

	public LiveAgent addLiveagent(LiveAgent liveagent) {
		getLiveagents().add(liveagent);
		liveagent.setSfdc(this);

		return liveagent;
	}

	public LiveAgent removeLiveagent(LiveAgent liveagent) {
		getLiveagents().remove(liveagent);
		liveagent.setSfdc(null);

		return liveagent;
	}

}