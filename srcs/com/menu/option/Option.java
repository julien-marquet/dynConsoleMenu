package	com.menu.option;

import	com.menu.option.optionparams.OptionParams;
import	com.menu.exception.*;

public abstract class Option {
	protected OptionParams optionParam;

	public Option(OptionParams optionParam) {
		this.optionParam = optionParam;
	}
	@Override
	public String toString() {
		return (this.optionParam.optionName);
	}
	public abstract void execute() throws OptionExecutionException, OptionExitException;
}