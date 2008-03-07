package com.bitbakery.plugin.arc.repl;

import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.ui.ConsoleView;

/**
 * TODO: Describe the role(s) and responsibilit(ies)
 */
public class Repl2 extends OSProcessHandler {
    public Repl2(Process process, String commandLine) {
        super(process, commandLine);
    }


    public void dummy() {

        /*
         TODO - This is more like what we want - then we just need to intercept key events and send them to our process

         TODO - We'll want to have a "ColoredProcessHandler" similar to the Ruby plugin, so that our REPL looks nice
         */


        TextConsoleBuilderFactory factory = TextConsoleBuilderFactory.getInstance();
        TextConsoleBuilder builder = factory.createBuilder(null); // TODO - Plug in ou project
        ConsoleView view = builder.getConsole();
    }


}
