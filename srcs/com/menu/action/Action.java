package com.menu.action;
import com.menu.action.actionparam.ActionParam;
import com.menu.exception.*;

public abstract class Action {
	protected ActionParam actionParam;

	public Action(ActionParam actionParam) {
		this.actionParam = actionParam;
	}
	@Override
	public String toString() {
		return (this.actionParam.actionName);
	}
	public abstract void execute() throws ActionExecutionException, ActionExitException;
}