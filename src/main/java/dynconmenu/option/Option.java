package	dynconmenu.option;

import	dynconmenu.option.optionparams.OptionParams;

import java.util.IllegalFormatException;

import	dynconmenu.exception.*;

public abstract class Option {
	protected OptionParams optionParam;
	protected String optionLayout;

	public Option(OptionParams optionParam, String optionLayout) {
		this.optionParam = optionParam;
		this.optionLayout = optionLayout;
	}
	public String getOptionString(int index) {
		try {
			return (String.format(optionLayout, index, this.optionParam.optionName));
		} catch(IllegalFormatException e) {
			return ("OptionLayout of " + this.optionParam.optionName + " not properly formatted");
		}
	}
	public abstract void execute() throws OptionExecutionException, OptionExitException;
}