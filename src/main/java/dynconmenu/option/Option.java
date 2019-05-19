package	dynconmenu.option;

import	dynconmenu.option.optionparams.OptionParams;
import	dynconmenu.exception.option.*;

import	java.util.IllegalFormatException;

public abstract class Option {
	protected OptionParams params;
	protected String layout;
	protected boolean hidden;
	protected String name;

	public Option(OptionParams params, String layout) {
		this.params = params;
		this.layout = layout;
		this.hidden = false;
		this.name = params.optionName;
	}

	public Option(OptionParams params, String layout, boolean hidden) {
		this.params = params;
		this.layout = layout;
		this.hidden = hidden;
		this.name = params.optionName;
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

	public String getName() {
		return (this.name);
	}

	public OptionParams getParams() {
		return (this.params);
	}

	public String getLayout() {
		return (this.layout);
	}

	public String getString(int index) {
		try {
			return (String.format(this.layout, index, this.name));
		} catch(IllegalFormatException e) {
			return ("OptionLayout of " + this.name + " not properly formatted");
		}
	}

	public abstract void execute() throws OptionExecutionException, OptionExitException;
}