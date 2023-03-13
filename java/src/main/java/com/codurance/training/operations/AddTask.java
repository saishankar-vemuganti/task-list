package com.codurance.training.operations;

import com.codurance.training.tasks.Task;

import java.io.PrintWriter;
import java.util.List;
import static com.codurance.training.manage.Manager.tasks;


public class AddTask implements OperationInterface {
    private long lastId = 0;
    @Override
    public void operate(String commandLine, PrintWriter out) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String[] projectTask = subcommandRest[1].split(" ", 2);
        String project = projectTask[0];
        String description = projectTask[1];
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(++lastId, description, false));
    }
}
