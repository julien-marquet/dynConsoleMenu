package dynconmenu;

import  dynconmenu.option.*;
import  dynconmenu.exception.menu.*;
import  dynconmenu.exception.option.*;


import  java.util.Scanner;

public class DynConMenuOnce extends DynConMenu{
    public DynConMenuOnce() {
        super();
    }

    public void	interract(Scanner in) {
		Option option = null;

        
        try {
            System.out.print(this.getMenu());
            option = this.userGetOption(in);
            try {
                option.execute();
            } catch (OptionExecutionException eExec) {
                System.err.println(eExec.getMessage());
            } catch (OptionExitException eExit) {
                return ;
            }
        } catch (MenuInputException | MenuDisplayException e) {
            System.err.println(e.getMessage());
        }
    }
}