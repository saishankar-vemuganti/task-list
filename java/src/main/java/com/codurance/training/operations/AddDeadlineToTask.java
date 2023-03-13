package com.codurance.training.operations;

import com.codurance.training.tasks.Task;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static com.codurance.training.manage.Manager.tasks;


public class AddDeadlineToTask implements OperationInterface {
    @Override
    public void operate(String commandLine, PrintWriter out) {
        String [] commandLineSplit = commandLine.split(" ");
        int taskId = Integer.parseInt(commandLineSplit[0]);
        String deadlineString = commandLineSplit[1];
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date deadLine = dateFormat.parse(deadlineString);
            for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
                for (Task task : project.getValue()) {
                    if (task.getId() == taskId) {
                        task.setDeadline(deadLine);
                        return;
                    }
                }
            }
            out.printf("Could not find a task with an ID of %d.", taskId);
            out.println();
        } catch (Exception e) {
            System.out.println("Invalid date format! Please enter date in DD/MM/YYYY format.");
        }
    }
}
