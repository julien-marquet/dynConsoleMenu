import dynconmenu.*;
import dynconmenu.option.Option;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Option option = null;
        DynConMenu menu = new DynConMenuOnce();
        Scanner in = new Scanner(System.in);
        menu.interract(in);
        try {
            option = menu.getOption(1);
        } catch(NoSuchElementException e) {
            System.out.println("no elem");
            return ;
        }
        option.setLayout("%d%d -> %s\n");
        menu.interract(in);
        option.hide();
        menu.interract(in);
        in.close();
    }
}