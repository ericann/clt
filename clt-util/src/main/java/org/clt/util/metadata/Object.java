package org.clt.util.metadata;

import java.util.LinkedHashSet;
import java.util.Set;

public class Object {
	
	/**
	 * fields of current page
	 */
	private Set<Field> fields = new LinkedHashSet<Field>();
	
	/**
	 * actions of current object
	 */
	private Set<String> buttons = new LinkedHashSet<String>();

	public Set<Field> getFields() {
		return fields;
	}

	public void setFields(Set<Field> fields) {
		this.fields = fields;
	}

	public Set<String> getButtons() {
		return buttons;
	}

	public void setButtons(Set<String> buttons) {
		this.buttons = buttons;
	} 
	
	public Set<String> addButton(String button) {
		this.buttons.add(button);
		return this.buttons;
	}
	
	public Set<Field> addField(Field field) {
		this.fields.add(field);
		return this.fields;
	}
	
	public String toString() {
		return " Object: " +
				"\n fields: " + fields.toString() +
				"\n buttons: " + buttons.toString();
	}
}
