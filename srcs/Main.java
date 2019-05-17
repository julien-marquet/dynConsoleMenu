import com.menu.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] argv) {
		Menu menu = new Menu();
		Scanner in = new Scanner(System.in);
		System.out.print(menu.toString());
		menu.selectAction(in);
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