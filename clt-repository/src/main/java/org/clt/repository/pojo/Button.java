package org.clt.repository.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the button database table.
 * 
 */
@Entity
@NamedQuery(name="Button.findAll", query="SELECT b FROM Button b")
public class Button implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String buttonId;

	private String displayInfo;

	private Boolean isDefault;
	
	private int limitCount;

	//bi-directional many-to-one association to BasicConfig
	@ManyToOne
	@JoinColumn(name="bcId")
	private BasicConfig basicConfig;

	public Button() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getButtonId() {
		return this.buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	public String getDisplayInfo() {
		return this.displayInfo;
	}

	public void setDisplayInfo(String displayInfo) {
		this.displayInfo = displayInfo;
	}

	public Boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public BasicConfig getBasicconfig() {
		return this.basicConfig;
	}

	public void setBasicconfig(BasicConfig basicconfig) {
		this.basicConfig = basicconfig;
	}

	public int getLimitCount() {
		return this.limitCount;
	}

	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}

}