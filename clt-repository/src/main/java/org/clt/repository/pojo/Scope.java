package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the scope database table.
 * 
 */
@Entity
@NamedQuery(name="Scope.findAll", query="SELECT s FROM Scope s")
public class Scope implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date createTime;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date updateTime;

	//bi-directional many-to-one association to ConnectApp
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="appId")
	private ConnectApp connectApp;

	//bi-directional many-to-one association to FunctionPermission
	@ManyToOne
	@JoinColumn(name="functionPermissionId")
	private FunctionPermission functionpermission;

	public Scope() {
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

	public ConnectApp getConnectApp() {
		return this.connectApp;
	}

	public void setConnectApp(ConnectApp connectApp) {
		this.connectApp = connectApp;
	}

	public FunctionPermission getFunctionpermission() {
		return this.functionpermission;
	}

	public void setFunctionpermission(FunctionPermission functionpermission) {
		this.functionpermission = functionpermission;
	}

}