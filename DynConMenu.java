package	com.dynconmenu;

import	com.dynconmenu.option.*;
import	com.dynconmenu.option.optionparams.*;
import	com.dynconmenu.exception.*;

import	java.io.StringWriter;
import	java.util.Enumeration;
import	java.util.Hashtable;
import	java.util.IllegalFormatException;
import	java.util.InputMismatchException;
import	java.util.NoSuchElementException;
import	java.util.Scanner;

public class DynConMenu {
	public static final String DEFAULT_MENU_LAYOUT = "Menu :\n%s\nVotre choix = ";
	public static final String DEFAULT_OPTION_LAYOUT = "\t%d : %s\n";
	public static final Hashtable<String, Option> DEFAULT_OPTIONS = getDefaultOptions();
	public static final String DEFAULT_OPTIONS_SEPARATOR = "\n";

	private Hashtable<String, Option> options;
	private String menuLayout;
	private String optionLayout;
	private String optionSeparator;

	private static Hashtable<String, Option> getDefaultOptions() {
		Hashtable<String, Option> options = new Hashtable<String, Option>();
		options.put("exit" , new Exit(new ExitOptionParams("exit")));
		return (options);
	}

	public DynConMenu() {
		this.options = DEFAULT_OPTIONS;
		this.menuLayout = DEFAULT_MENU_LAYOUT;
		this.optionLayout = DEFAULT_OPTION_LAYOUT;
		this.optionSeparator = DEFAULT_OPTIONS_SEPARATOR;
	}
	public DynConMenu(Hashtable<String, Option> options) {
		this.options = options;
		this.menuLayout = DEFAULT_MENU_LAYOUT;
		this.optionLayout = DEFAULT_OPTION_LAYOUT;
		this.optionSeparator = DEFAULT_OPTIONS_SEPARATOR;
	}
	public DynConMenu(Hashtable<String, Option> options, String menuLayout) {
		this.options = options;
		this.menuLayout = menuLayout;
		this.optionLayout = DEFAULT_OPTION_LAYOUT;
		this.optionSeparator = DEFAULT_OPTIONS_SEPARATOR;
	}
	public DynConMenu(Hashtable<String, Option> options, String menuLayout, String optionLayout) {
		this.options = options;
		this.menuLayout = menuLayout;
		this.optionLayout = optionLayout;
		this.optionSeparator = DEFAULT_OPTIONS_SEPARATOR;
	}
	public DynConMenu(Hashtable<String, Option> options, String menuLayout, String optionLayout, String optionSeparator) {
		this.options = options;
		this.menuLayout = menuLayout;
		this.optionLayout = optionLayout;
		this.optionSeparator = optionSeparator;
	}

	private Option getOptionByIndex(int userIndex, Scanner in)
	throws NoSuchElementException {
		int		i;
		Option	option;
	
		userIndex = in.nextInt();
		Enumeration<Option> eht = options.elements();
		i = 1;
		while (eht.hasMoreElements()) {
			option = eht.nextElement();
			if (i == userIndex) {
				return (option);
			}
			i++;
		}
		throw new NoSuchElementException();
	}
	
	public Option selectOption(Scanner in) throws OptionInputException {
		String	userKey = null;
		int		userIndex = -1;

		try {
			return (getOptionByIndex(userIndex, in));
		} catch (NoSuchElementException notAnInt) {
			try {
				userKey = in.nextLine();
			} catch (NoSuchElementException notAString) {
				throw new OptionInputException("Empty input");
			}
			if (this.options.containsKey(userKey)) {
				return (this.options.get(userKey));
			}
		} catch (IllegalStateException | NullPointerException inputError) {
			throw new OptionInputException("Input error");
		}
		throw new OptionInputException("No options matched the input");
	}

	public void	interract(Scanner in) {
		Option option = null;

		while (true) {
            while (option == null)
            {
                System.out.print(this.toString());
                try {
                    option = this.selectOption(in);
                } catch (OptionInputException e) {
                    System.err.println(e.getMessage());
                }
            }
            try {
                option.execute();
            } catch (OptionExecutionException e) {
                System.err.println(e.getMessage());
            } catch (OptionExitException e) {
                break ;
            }
            option = null;
        }
	}

	public String toString() {
		Enumeration<Option>	e = options.elements();
		StringWriter writer = new StringWriter();
		int i = 1;

		try {
			while (e.hasMoreElements()) {
				if (i != 1)
					writer.append(this.optionSeparator);
				writer.append(String.format(this.optionLayout, i, e.nextElement().toString()));
				i++;
			}
		} catch (IllegalFormatException eOptionLayout) {
			return ("optionLayout not properly formatted\n");
		}
		try {
			return (String.format(this.menuLayout, writer.toString()));
		} catch (IllegalFormatException eMenuLayout) {
			return ("menuLayout not properly formatted\n");
		}
	}
}