package	com.menu;
import	com.menu.action.*;
import	java.io.CharArrayReader;
import	java.io.CharArrayWriter;
import	java.io.StringReader;
import	java.io.StringWriter;
import	java.util.Enumeration;
import	java.util.Hashtable;
import java.util.Scanner;

public class Menu {
	private Hashtable<String, Action>	actions;

	public Menu() {
		this.actions = new Hashtable<String, Action>();
		this.actions.put("exit", new Exit("exit"));
		this.actions.put("repeat", new Repeat("repeat"));
	}
	public String toString() {
		Enumeration	e = actions.elements();
		StringWriter writer = new StringWriter();
		while (e.hasMoreElements()) {
			writer.append(e.nextElement().toString() + "\n");
		}
		return (writer.toString());
	}
	public String selectAction(Scanner in) {
		return (in.nextLine());
	}
}