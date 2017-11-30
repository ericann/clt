package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the connectapp database table.
 * 
 */
@Entity
@NamedQuery(name="ConnectApp.findAll", query="SELECT c FROM ConnectApp c")
public class ConnectApp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String clientId;

	private String clientSecret;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private String redirectURI;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	//bi-directional many-to-one association to Scope
	@OneToMany(mappedBy="connectApp")
	private Set<Scope> scopes;

	//bi-directional many-to-one association to UserApp
	@OneToMany(mappedBy="connectApp")
	private Set<UserApp> userapps;

	public ConnectApp() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return this.clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRedirectURI() {
		return this.redirectURI;
	}

	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Set<Scope> getScopes() {
		return this.scopes;
	}

	public void setScopes(Set<Scope> scopes) {
		this.scopes = scopes;
	}

	public Scope addScope(Scope scope) {
		getScopes().add(scope);
		scope.setConnectApp(this);

		return scope;
	}

	public Scope removeScope(Scope scope) {
		getScopes().remove(scope);
		scope.setConnectApp(null);

		return scope;
	}

	public Set<UserApp> getUserapps() {
		return this.userapps;
	}

	public void setUserapps(Set<UserApp> userapps) {
		this.userapps = userapps;
	}

	public UserApp addUserApp(UserApp userApp) {
		getUserapps().add(userApp);
		userApp.setConnectApp(this);

		return userApp;
	}

	public UserApp removeUserApp(UserApp userApp) {
		getUserapps().remove(userApp);
		userApp.setConnectApp(null);

		return userApp;
	}

}