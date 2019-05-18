package com.menu;

import  com.menu.option.*;
import  com.menu.exception.*;

import  java.util.Scanner;

public class OneShotMenu extends Menu{
    public OneShotMenu() {
        super();
    }

    public void	interract(Scanner in) {
		Option option = null;

        System.out.print(this.toString());
        try {
            option = this.selectOption(in);
            try {
                option.execute();
            } catch (OptionExecutionException | OptionExitException e) {
                System.err.println(e.getMessage());
            }
        } catch (OptionInputException e) {
            System.err.println(e.getMessage());
        }
    }
}