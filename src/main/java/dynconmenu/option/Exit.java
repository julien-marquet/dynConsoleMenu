package	dynconmenu.option;

import	dynconmenu.option.optionparams.*;
import	dynconmenu.exception.option.OptionExitException;

public class Exit extends Option {
	public Exit(ExitOptionParams params, String layout) {
		super(params, layout);

	}
	public void execute()
	throws OptionExitException {
		throw new OptionExitException();
	}
}