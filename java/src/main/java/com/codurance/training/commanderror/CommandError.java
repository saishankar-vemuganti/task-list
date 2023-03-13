package com.codurance.training.commanderror;

import java.io.PrintWriter;

public class CommandError implements CommandErrorInterface{
    @Override
    public void error(String command, PrintWriter out) {
            out.printf("I don't know what the command \"%s\" is.", command);
            out.println();
    }
}
