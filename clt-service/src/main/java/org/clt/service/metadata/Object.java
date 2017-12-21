package org.clt.service.metadata;

import java.util.LinkedHashSet;
import java.util.Set;

public class Object {
	
	private String title;
	
	/**
	 * fields of current page
	 */
	private Set<Field> fields = new LinkedHashSet<Field>();
	
	/**
	 * actions of current object
	 */
	private Set<Action> buttons = new LinkedHashSet<Action>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Field> getFields() {
		return fields;
	}

	public void setFields(Set<Field> fields) {
		this.fields = fields;
	}

	public Set<Action> getButtons() {
		return buttons;
	}

	public void setButtons(Set<Action> buttons) {
		this.buttons = buttons;
	} 
	
	public Set<Action> addButton(Action button) {
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
