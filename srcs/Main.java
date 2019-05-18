import com.menu.*;
import com.menu.action.Action;
import com.menu.exception.*;

import java.util.Scanner;

public class Main {
	public static void main(String[] argv) {
		Menu menu = new Menu(Menu.DEFAULT_ACTIONS, "Menu = \n%s\nChoix ? ", " %d: %s", "\n");
        Scanner in = new Scanner(System.in);
        menu.interract(in);
        in.close();
	}
}

/**
 * App goal
 * 
 * Dynamic console menu
 * - Set Option
 * - Associate Option and Action
 * - Print Actions/options
 * - Get User input
 * - (export / save menu configuration)
 */

 /**
  * Classe Menu
  *	hashtable d'objets Action
  *
  * Classe Action = superClasse abstraite
  *	printAction()
  * executeAction()
  */