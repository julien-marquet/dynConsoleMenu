package com.customoptions;

import  com.menu.option.Option;
import  com.menu.option.optionparams.OptionParams;

public class TestOption extends Option {
    public TestOption(OptionParams optionParam) {
        super(optionParam);
    }

    public void execute() {
        System.out.println("Test");
    }
}