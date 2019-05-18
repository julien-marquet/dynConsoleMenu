package com.dynconmenu;

import  com.dynconmenu.option.*;
import  com.dynconmenu.exception.*;

import  java.util.Scanner;

public class DynConMenuOnce extends DynConMenu{
    public DynConMenuOnce() {
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