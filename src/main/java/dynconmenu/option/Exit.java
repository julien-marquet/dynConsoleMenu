package	dynconmenu.option;

import	dynconmenu.option.optionparams.*;
import	dynconmenu.exception.OptionExitException;

public class Exit extends Option {
	public Exit(ExitOptionParams optionParam) {
		super(optionParam);
	}
	public void execute()
	throws OptionExitException {
		throw new OptionExitException();
	}
}