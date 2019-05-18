import dynconmenu.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        DynConMenu menu = new DynConMenuOnce();
        Scanner in = new Scanner(System.in);
        menu.interract(in);
    }
}