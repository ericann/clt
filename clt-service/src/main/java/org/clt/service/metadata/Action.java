package org.clt.service.metadata;

public class Action {
	
	/**
	 * label of field
	 */
	private String label;
	
	/**
	 * default click function
	 */
	private String func;
	
	/**
	 * description of field
	 */
	private String help;
	
	/**
	 * display or not
	 */
	private String display;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
}
