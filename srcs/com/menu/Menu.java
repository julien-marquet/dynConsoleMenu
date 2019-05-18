package	com.menu;

import	com.menu.action.*;
import com.menu.action.actionparam.*;
import	com.menu.exception.*;

import	java.io.StringWriter;
import	java.util.Enumeration;
import	java.util.Hashtable;
import	java.util.IllegalFormatException;
import	java.util.InputMismatchException;
import	java.util.NoSuchElementException;
import	java.util.Scanner;

public class Menu {
	public static final String DEFAULT_MENU_LAYOUT = "Menu :\n%s\nVotre choix = ";
	public static final String DEFAULT_ACTION_LAYOUT = "\t%d : %s\n";
	public static final Hashtable<String, Action> DEFAULT_ACTIONS = getDefaultActions();
	public static final String DEFAULT_ACTIONS_SEPARATOR = "\n";

	private Hashtable<String, Action> actions;
	private String menuLayout;
	private String actionLayout;
	private String actionSeparator;

	private static Hashtable<String, Action> getDefaultActions() {
		Hashtable<String, Action> actions = new Hashtable<String, Action>();
		actions.put("exit" , new Exit(new ExitActionParam("exit")));
		actions.put("repeat", new Repeat(new RepeatActionParam("repeat")));
		return (actions);
	}

	public Menu() {
		this.actions = DEFAULT_ACTIONS;
		this.menuLayout = DEFAULT_MENU_LAYOUT;
		this.actionLayout = DEFAULT_ACTION_LAYOUT;
		this.actionSeparator = DEFAULT_ACTIONS_SEPARATOR;
	}
	public Menu(Hashtable<String, Action> actions) {
		this.actions = actions;
		this.menuLayout = DEFAULT_MENU_LAYOUT;
		this.actionLayout = DEFAULT_ACTION_LAYOUT;
		this.actionSeparator = DEFAULT_ACTIONS_SEPARATOR;
	}
	public Menu(Hashtable<String, Action> actions, String menuLayout) {
		this.actions = actions;
		this.menuLayout = menuLayout;
		this.actionLayout = DEFAULT_ACTION_LAYOUT;
		this.actionSeparator = DEFAULT_ACTIONS_SEPARATOR;
	}
	public Menu(Hashtable<String, Action> actions, String menuLayout, String actionLayout) {
		this.actions = actions;
		this.menuLayout = menuLayout;
		this.actionLayout = actionLayout;
		this.actionSeparator = DEFAULT_ACTIONS_SEPARATOR;
	}
	public Menu(Hashtable<String, Action> actions, String menuLayout, String actionLayout, String actionSeparator) {
		this.actions = actions;
		this.menuLayout = menuLayout;
		this.actionLayout = actionLayout;
		this.actionSeparator = actionSeparator;
	}

	private Action getActionByIndex(int userIndex, Scanner in)
	throws NoSuchElementException {
		int		i;
		Action	action;
	
		userIndex = in.nextInt();
		Enumeration<Action> eht = actions.elements();
		i = 1;
		while (eht.hasMoreElements()) {
			action = eht.nextElement();
			if (i == userIndex) {
				return (action);
			}
			i++;
		}
		throw new NoSuchElementException();
	}
	
	public Action selectAction(Scanner in) throws ActionInputException {
		String	userKey = null;
		int		userIndex = -1;

		try {
			return (getActionByIndex(userIndex, in));
		} catch (NoSuchElementException notAnInt) {
			try {
				userKey = in.nextLine();
			} catch (NoSuchElementException notAString) {
				throw new ActionInputException("Empty input");
			}
			if (this.actions.containsKey(userKey)) {
				return (this.actions.get(userKey));
			}
		} catch (IllegalStateException | NullPointerException inputError) {
			throw new ActionInputException("Input error");
		}
		throw new ActionInputException("No actions matched the input");
	}

	public void	interract(Scanner in) {
		Action action = null;

		while (true) {
            while (action == null)
            {
                System.out.print(this.toString());
                try {
                    action = this.selectAction(in);
                } catch (ActionInputException e) {
                    System.err.println(e.getMessage());
                }
            }
            try {
                action.execute();
            } catch (ActionExecutionException e) {
                System.err.println(e.getMessage());
            } catch (ActionExitException e) {
                break ;
            }
            action = null;
        }
	}

	public String toString() {
		Enumeration<Action>	e = actions.elements();
		StringWriter writer = new StringWriter();
		int i = 1;

		try {
			while (e.hasMoreElements()) {
				if (i != 1)
					writer.append(this.actionSeparator);
				writer.append(String.format(this.actionLayout, i, e.nextElement().toString()));
				i++;
			}
		} catch (IllegalFormatException eActionLayout) {
			return ("actionLayout not properly formatted\n");
		}
		try {
			return (String.format(this.menuLayout, writer.toString()));
		} catch (IllegalFormatException eMenuLayout) {
			return ("menuLayout not properly formatted\n");
		}
	}
}