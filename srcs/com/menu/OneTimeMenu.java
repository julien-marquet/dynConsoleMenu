package com.menu;
import	com.menu.action.*;
import com.menu.exception.*;
import java.util.Scanner;

public class OneTimeMenu extends Menu{
    public OneTimeMenu() {
        super();
    }

    public void	interract(Scanner in) {
		Action action = null;

        System.out.print(this.toString());
        try {
            action = this.selectAction(in);
            try {
                action.execute();
            } catch (ActionExecutionException | ActionExitException e) {
                System.err.println(e.getMessage());
            }
        } catch (ActionInputException e) {
            System.err.println(e.getMessage());
        }
    }
}