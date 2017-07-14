package org.clt.util.vo;

public class Field {
	/**
	 * label of field
	 */
	private String label;
	
	/**
	 * value type of field in html, such as div.
	 */
	private String type;
	
	/**
	 * default/current value of field in html. 
	 */
	private String value;
	
	/**
	 * value of field modify or not.
	 */
	private Boolean readonly;
	
	/**
	 * description of field
	 */
	private String help;
	
	/**
	 * link of field to explore.
	 */
	private String link;
	
	/**
	 * reference object type
	 */
	private String referenceTo;
	
	/**
	 * reference object id
	 */
	private String referenceId;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getReadonly() {
		return readonly;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getReferenceTo() {
		return referenceTo;
	}

	public void setReferenceTo(String referenceTo) {
		this.referenceTo = referenceTo;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	
}
