package	dynconmenu.option;

import	dynconmenu.option.optionparams.OptionParams;
import	dynconmenu.exception.*;

import	java.util.IllegalFormatException;

public abstract class Option {
	protected OptionParams params;
	protected String layout;

	public Option(OptionParams params, String layout) {
		this.params = params;
		this.layout = layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public void setParams(OptionParams params) {
		this.params = params;
	}

	public OptionParams getOptionParams() {
		return (this.params);
	}

	public String getOptionLayout() {
		return (this.layout);
	}

	public String getOptionString(int index) {
		try {
			return (String.format(this.layout, index, this.params.optionName));
		} catch(IllegalFormatException e) {
			return ("OptionLayout of " + this.params.optionName + " not properly formatted");
		}
	}

	public abstract void execute() throws OptionExecutionException, OptionExitException;
}