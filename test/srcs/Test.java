import dynconmenu.*;
import dynconmenu.option.Option;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        DynConMenu menu = new DynConMenuOnce();
        Scanner in = new Scanner(System.in);
        menu.interract(in);
        try {
            Option option = menu.getOption(1);
            option.setLayout("%d -> %s\n");
        } catch(NoSuchElementException e) {
            System.out.println("no elem");
        }
        menu.interract(in);
        in.close();
    }
}