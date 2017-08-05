package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the functionobject database table.
 * 
 */
@Entity
@NamedQuery(name="FunctionObject.findAll", query="SELECT f FROM FunctionObject f")
public class FunctionObject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	//bi-directional many-to-one association to FunctionPermission
	@ManyToOne
	@JoinColumn(name="functionPermissionId")
	private FunctionPermission functionpermission;

	//bi-directional many-to-one association to ObjectPermission
	@ManyToOne
	@JoinColumn(name="objectPermissionId")
	private ObjectPermission objectpermission;

	public FunctionObject() {
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

	public FunctionPermission getFunctionpermission() {
		return this.functionpermission;
	}

	public void setFunctionpermission(FunctionPermission functionpermission) {
		this.functionpermission = functionpermission;
	}

	public ObjectPermission getObjectpermission() {
		return this.objectpermission;
	}

	public void setObjectpermission(ObjectPermission objectpermission) {
		this.objectpermission = objectpermission;
	}

}