import  com.menu.*;
import  com.menu.option.Option;
import  com.menu.option.optionparams.OptionParams;
import  com.menu.exception.*;
import  com.customoptions.*;
import  java.util.Hashtable;
import  java.util.Scanner;

public class Main {
	public static void main(String[] argv) {
        Hashtable<String, Option> ht = new Hashtable<String, Option>();
        ht.put("test", new TestOption(new OptionParams("test")));
		Menu menu = new Menu(ht, "Menu = \n%s\nChoix ? ", " %d: %s", "\n");
        Scanner in = new Scanner(System.in);
        menu.interract(in);
        in.close();
	}
}
