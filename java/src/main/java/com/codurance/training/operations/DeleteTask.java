package com.codurance.training.operations;

import com.codurance.training.tasks.Task;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import static com.codurance.training.manage.Manager.tasks;

public class DeleteTask implements OperationInterface{
    @Override
    public void operate(String id,PrintWriter out) {
        for (List<Task> taskList : tasks.values()) {
            Iterator<Task> iterator = taskList.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task.getId() == Integer.parseInt(id)) {
                    iterator.remove();
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %s.", id);
    }
}
