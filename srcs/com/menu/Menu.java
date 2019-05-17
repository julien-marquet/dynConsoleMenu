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
		actions.put("exit", new Exit("exit"));
		actions.put("repeat", new Repeat("repeat"));
		return (actions);
	}

	public Menu() {
		this.actions = getDefaultActions();
	}
	public Menu(Hashtable<String, Action> actions) {
		this.actions = actions;
	}
	public Action selectAction(Scanner in)
	throws ActionInputException {
		String userKey;

		try {
			userKey = in.nextLine();
			if (this.actions.containsKey(userKey)) {
				return (this.actions.get(userKey));
			}
		} catch (NoSuchElementException | IllegalStateException | NullPointerException e) {
			throw new ActionInputException("Input error");
		}
		throw new ActionInputException("No match");
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
		Enumeration	e = actions.elements();
		StringWriter writer = new StringWriter();
		while (e.hasMoreElements()) {
			writer.append(e.nextElement().toString() + "\n");
		}
		return (writer.toString());
	}
}