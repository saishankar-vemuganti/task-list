package com.codurance.training.operations;

import static com.codurance.training.manage.Manager.tasks;

import java.io.PrintWriter;
import java.util.ArrayList;

public class AddProject implements OperationInterface {
    @Override
    public void operate(String commandLine, PrintWriter out) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String name = subcommandRest[1];
        tasks.put(name, new ArrayList<>());
    }
}
