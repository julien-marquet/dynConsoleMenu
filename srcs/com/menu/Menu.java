package	com.menu;
import	com.menu.action.*;
import com.menu.exception.*;

import	java.io.CharArrayReader;
import	java.io.CharArrayWriter;
import	java.io.StringReader;
import	java.io.StringWriter;
import	java.util.Enumeration;
import	java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
	private Hashtable<String, Action> actions;

	private	Hashtable<String, Action> getDefaultActions() {
		Hashtable<String, Action> actions = new Hashtable<String, Action>();
		actions.put("exit", new Exit());
		actions.put("repeat", new Repeat());
		return (actions);
	}

	public Menu() {
		this.actions = getDefaultActions();
	}

	public Menu(Hashtable<String, Action> actions) {
		this.actions = actions;
	}

	private Action getActionByIndex(int userIndex, Scanner in)
	throws NoSuchElementException, IllegalStateException, NullPointerException {
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
	public Action selectAction(Scanner in)
	throws ActionInputException {
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
				System.out.println("Executing " + action);
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
		writer.append("MENU\n");
		while (e.hasMoreElements()) {
			writer.append("  " + i + " : " + e.nextElement().toString() + "\n");
			i++;
		}
		return (writer.toString());
	}
}