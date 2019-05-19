package	dynconmenu.option;

import	dynconmenu.option.optionparams.OptionParams;
import	dynconmenu.exception.option.*;

import	java.util.IllegalFormatException;

public abstract class Option {
	protected OptionParams params;
	protected String layout;
	protected boolean hidden;

	public Option(OptionParams params, String layout) {
		this.params = params;
		this.layout = layout;
		this.hidden = false;
	}

	public Option(OptionParams params, String layout, boolean hidden) {
		this.params = params;
		this.layout = layout;
		this.hidden = hidden;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public void setParams(OptionParams params) {
		this.params = params;
	}

	public void hide() {
		this.hidden = true;
	}

	public void show() {
		this.hidden = false;
	}

	public boolean isHidden() {
		return (this.hidden);
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