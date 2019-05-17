package com.menu.action;

import com.menu.exception.ActionExitException;

public class Exit extends Action {
	public Exit() {
		this.name = "exit";
	}
	public Exit(String name) {
		this.name = name;
	}
	public void execute()
	throws ActionExitException {
		throw new ActionExitException();
	}
}