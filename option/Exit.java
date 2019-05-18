package	com.dynconmenu.option;

import	com.dynconmenu.option.optionparams.*;
import	com.dynconmenu.exception.OptionExitException;

public class Exit extends Option {
	public Exit(ExitOptionParams optionParam) {
		super(optionParam);
	}
	public void execute()
	throws OptionExitException {
		throw new OptionExitException();
	}
}