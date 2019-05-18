package	dynconmenu.option;

import	dynconmenu.option.optionparams.*;
import	dynconmenu.exception.OptionExitException;

public class Exit extends Option {
	public Exit(ExitOptionParams optionParams, String optionLayout) {
		super(optionParams, optionLayout);

	}
	public void execute()
	throws OptionExitException {
		throw new OptionExitException();
	}
}