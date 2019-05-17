package com.menu.action;
import com.menu.exception.*;

public abstract class Action {
	protected String name;

	@Override
	public String toString() {
		return (this.name);
	}
	public abstract void execute() throws ActionExecutionException, ActionExitException;
}