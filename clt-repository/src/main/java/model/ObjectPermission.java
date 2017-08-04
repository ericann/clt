package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the objectpermission database table.
 * 
 */
@Entity
@NamedQuery(name="ObjectPermission.findAll", query="SELECT o FROM ObjectPermission o")
public class ObjectPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Boolean add;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private Boolean del;

	private Boolean edit;

	private String Boolean;

	private Boolean read;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	//bi-directional many-to-one association to FieldPermission
	@OneToMany(mappedBy="objectpermission")
	private List<FieldPermission> fieldpermissions;

	//bi-directional many-to-one association to FunctionObject
	@OneToMany(mappedBy="objectpermission")
	private List<FunctionObject> functionobjects;

	public ObjectPermission() {
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

	public Boolean getEdid() {
		return this.edit;
	}

	public void setEdid(Boolean edid) {
		this.edit = edid;
	}

	public String getObject() {
		return this.Boolean;
	}

	public void setObject(String Boolean) {
		this.Boolean = Boolean;
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

	public List<FieldPermission> getFieldpermissions() {
		return this.fieldpermissions;
	}

	public void setFieldpermissions(List<FieldPermission> fieldpermissions) {
		this.fieldpermissions = fieldpermissions;
	}

	public FieldPermission addFieldpermission(FieldPermission fieldpermission) {
		getFieldpermissions().add(fieldpermission);
		fieldpermission.setObjectpermission(this);

		return fieldpermission;
	}

	public FieldPermission removeFieldpermission(FieldPermission fieldpermission) {
		getFieldpermissions().remove(fieldpermission);
		fieldpermission.setObjectpermission(null);

		return fieldpermission;
	}

	public List<FunctionObject> getFunctionobjects() {
		return this.functionobjects;
	}

	public void setFunctionobjects(List<FunctionObject> functionobjects) {
		this.functionobjects = functionobjects;
	}

	public FunctionObject addFunctionobject(FunctionObject functionobject) {
		getFunctionobjects().add(functionobject);
		functionobject.setObjectpermission(this);

		return functionobject;
	}

	public FunctionObject removeFunctionobject(FunctionObject functionobject) {
		getFunctionobjects().remove(functionobject);
		functionobject.setObjectpermission(null);

		return functionobject;
	}

}