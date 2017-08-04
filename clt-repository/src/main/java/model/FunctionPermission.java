package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the functionpermission database table.
 * 
 */
@Entity
@NamedQuery(name="FunctionPermission.findAll", query="SELECT f FROM FunctionPermission f")
public class FunctionPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	private String url;

	//bi-directional many-to-one association to FunctionObject
	@OneToMany(mappedBy="functionpermission")
	private List<FunctionObject> functionobjects;

	//bi-directional many-to-one association to Scope
	@OneToMany(mappedBy="functionpermission")
	private List<Scope> scopes;

	public FunctionPermission() {
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<FunctionObject> getFunctionobjects() {
		return this.functionobjects;
	}

	public void setFunctionobjects(List<FunctionObject> functionobjects) {
		this.functionobjects = functionobjects;
	}

	public FunctionObject addFunctionobject(FunctionObject functionobject) {
		getFunctionobjects().add(functionobject);
		functionobject.setFunctionpermission(this);

		return functionobject;
	}

	public FunctionObject removeFunctionobject(FunctionObject functionobject) {
		getFunctionobjects().remove(functionobject);
		functionobject.setFunctionpermission(null);

		return functionobject;
	}

	public List<Scope> getScopes() {
		return this.scopes;
	}

	public void setScopes(List<Scope> scopes) {
		this.scopes = scopes;
	}

	public Scope addScope(Scope scope) {
		getScopes().add(scope);
		scope.setFunctionpermission(this);

		return scope;
	}

	public Scope removeScope(Scope scope) {
		getScopes().remove(scope);
		scope.setFunctionpermission(null);

		return scope;
	}

}