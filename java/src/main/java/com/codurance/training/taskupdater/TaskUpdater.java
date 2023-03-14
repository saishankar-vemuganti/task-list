package com.codurance.training.taskupdater;

import com.codurance.training.tasks.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import static com.codurance.training.manage.Manager.tasks;

public class TaskUpdater implements TaskUpdaterInterface{
    @Override
    public void updateTask(String idString, boolean done,PrintWriter out) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }
}
