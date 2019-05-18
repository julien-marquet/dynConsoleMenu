package	com.menu.option;

import	com.menu.option.optionparams.*;
import	com.menu.exception.OptionExitException;

public class Exit extends Option {
	public Exit(ExitOptionParams optionParam) {
		super(optionParam);
	}
	public void execute()
	throws OptionExitException {
		throw new OptionExitException();
	}
}