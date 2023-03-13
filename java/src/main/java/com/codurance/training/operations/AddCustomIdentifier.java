package com.codurance.training.operations;

import com.codurance.training.tasks.Task;
import static com.codurance.training.manage.Manager.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class AddCustomIdentifier implements OperationInterface {
    @Override
    public void operate(String commandLine,PrintWriter out) {
        String [] commandLineSplit = commandLine.split(" ",2);
        int taskId = Integer.parseInt(commandLineSplit[0]);
        String customid = commandLineSplit[1];
        if (!customid.matches("[a-zA-Z0-9]+")) {
            out.println("Invalid custom id.Only alphanumeric characters are allowed.");
            return;
        }
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == taskId) {
                    task.setCustomId(customid);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", taskId);
    }
}
