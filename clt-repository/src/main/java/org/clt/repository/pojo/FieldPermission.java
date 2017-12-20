package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the fieldpermission database table.
 * 
 */
@Entity
@NamedQuery(name="FieldPermission.findAll", query="SELECT f FROM FieldPermission f")
public class FieldPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Boolean add;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private Boolean del;

	private Boolean edit;

	private String field;

	private Boolean read;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	//bi-directional many-to-one association to ObjectPermission
	@ManyToOne
	@JoinColumn(name="objectId")
	private ObjectPermission objectpermission;

	public FieldPermission() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getAdd() {
		return this.add;
	}

	public void setAdd(Boolean add) {
		this.add = add;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getDel() {
		return this.del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	public Boolean getEdit() {
		return this.edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Boolean getRead() {
		return this.read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public ObjectPermission getObjectpermission() {
		return this.objectpermission;
	}

	public void setObjectpermission(ObjectPermission objectpermission) {
		this.objectpermission = objectpermission;
	}

}