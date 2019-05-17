package com.menu.action;

public abstract class Action {
	protected String name;

	@Override
	public String toString() {
		return (this.name);
	}
}