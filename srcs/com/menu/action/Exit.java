package com.menu.action;

import com.menu.action.actionparam.*;
import com.menu.exception.ActionExitException;

public class Exit extends Action {
	public Exit(ExitActionParam actionParam) {
		super(actionParam);
	}
	public void execute()
	throws ActionExitException {
		throw new ActionExitException();
	}
}