package	dynconmenu;

import	dynconmenu.option.*;
import	dynconmenu.option.optionparams.*;
import	dynconmenu.exception.menu.*;
import	dynconmenu.exception.option.*;

import	java.io.StringWriter;
import	java.util.Enumeration;
import	java.util.Hashtable;
import	java.util.IllegalFormatException;
import	java.util.InputMismatchException;
import	java.util.NoSuchElementException;
import	java.util.Scanner;

public class DynConMenu {
	public static final String DEFAULT_MENU_LAYOUT = "Menu :\n%s\nVotre choix = ";
	public static final String DEFAULT_OPTION_LAYOUT = "\t%d : %s";
	public static final Hashtable<String, Option> DEFAULT_OPTIONS = getDefaultOptions();
	public static final String DEFAULT_OPTIONS_SEPARATOR = "\n";

	private Hashtable<String, Option> options;
	private String menuLayout;
	private String optionSeparator;

	private static Hashtable<String, Option> getDefaultOptions() {
		Hashtable<String, Option> options = new Hashtable<String, Option>();
		options.put("exit" , new Exit(new ExitOptionParams("exit"), DEFAULT_OPTION_LAYOUT));
		return (options);
	}

	public DynConMenu() {
		this.options = DEFAULT_OPTIONS;
		this.menuLayout = DEFAULT_MENU_LAYOUT;
		this.optionSeparator = DEFAULT_OPTIONS_SEPARATOR;
	}
	public DynConMenu(Hashtable<String, Option> options) {
		this.options = options;
		this.menuLayout = DEFAULT_MENU_LAYOUT;
		this.optionSeparator = DEFAULT_OPTIONS_SEPARATOR;
	}
	public DynConMenu(Hashtable<String, Option> options, String menuLayout) {
		this.options = options;
		this.menuLayout = menuLayout;
		this.optionSeparator = DEFAULT_OPTIONS_SEPARATOR;
	}
	public DynConMenu(Hashtable<String, Option> options, String menuLayout, String optionSeparator) {
		this.options = options;
		this.menuLayout = menuLayout;
		this.optionSeparator = optionSeparator;
	}

	public Hashtable<String, Option> getOptions() {
		return (this.options);
	}

	public String getMenuLayout() {
		return (this.menuLayout);
	}

	public String getOptionSeparator() {
		return (this.optionSeparator);
	}

	public void setOptions(Hashtable<String, Option> options) {
		this.options = options;
	}

	public void setMenuLayout(String menuLayout) {
		this.menuLayout = menuLayout;
	}

	public void setOptionSeparator(String optionSeparator) {
		this.optionSeparator = optionSeparator;
	}
	
	public Option getDisplayableOption(int index) throws NoSuchElementException {
		Enumeration<Option> eht = options.elements();
		int i = 1;
		Option	option;

		while (eht.hasMoreElements()) {
			option = eht.nextElement();
			if (option.isHidden() == false)
			{
				if (i == index) {
					return (option);
				}
				i++;
			}
		}
		throw new NoSuchElementException();
	}
	public Option getDisplayableOption(String key) throws NoSuchElementException {
		Option option;
		
		if (this.options.containsKey(key)) {
			option = this.options.get(key);
			if (option.isHidden() == false)
				return (option);
		}
		throw new NoSuchElementException();
	}

	public Option getOption(int index) throws NoSuchElementException {
		Enumeration<Option> eht = options.elements();
		int i = 1;
		Option	option;

		while (eht.hasMoreElements()) {
			option = eht.nextElement();
			if (i == index) {
				return (option);
			}
			i++;
		}
		throw new NoSuchElementException();
	}
	public Option getOption(String key) throws NoSuchElementException {
		if (this.options.containsKey(key)) {
			return (this.options.get(key));
		}
		throw new NoSuchElementException();
	}

	public Option userGetOption(Scanner in) throws MenuInputException {
		String	userKey = null;
		int		userIndex = -1;

		try {
			return (getDisplayableOption(in.nextInt()));
		} catch (NoSuchElementException notAnInt) {
			try {
				return (getDisplayableOption(in.nextLine()));
			} catch (NoSuchElementException notAString) {
				throw new MenuInputException("No options matched the input");
			}
		} catch (IllegalStateException | NullPointerException inputError) {
			throw new MenuInputException("Input error");
		}
	}

	public void	interract(Scanner in) {
		Option option = null;

		while (true) {
            while (option == null)
            {
                try {
					System.out.print(this.getMenu());
                    option = this.userGetOption(in);
				} catch (MenuInputException e) {
					System.err.println(e.getMessage());
				} catch (MenuDisplayException e) {
					System.err.println(e.getMessage());
					return ;
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

	public String getMenu() throws MenuDisplayException {
		Enumeration<Option>	e = options.elements();
		StringWriter writer = new StringWriter();
		int i = 1;
		Option option;

		while (e.hasMoreElements()) {
			option = e.nextElement();
			if (option.isHidden() == false) {
				if (i != 1)
					writer.append(this.optionSeparator);
				writer.append(option.getOptionString(i));
				i++;
			}
		}
		if (i == 1)
			throw new MenuDisplayException("No option to display");
		try {
			return (String.format(this.menuLayout, writer.toString()));
		} catch (IllegalFormatException eMenuLayout) {
			return ("menuLayout not properly formatted\n");
		}
	}
}