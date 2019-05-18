package dynconmenu;

import  dynconmenu.option.*;
import  dynconmenu.exception.*;

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
            } catch (OptionExecutionException eExec) {
                System.err.println(eExec.getMessage());
            } catch (OptionExitException eExit) {
                return ;
            }
        } catch (OptionInputException e) {
            System.err.println(e.getMessage());
        }
    }
}