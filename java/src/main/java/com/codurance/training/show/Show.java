package com.codurance.training.show;

import com.codurance.training.tasks.Task;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static com.codurance.training.manage.Manager.tasks;

public class Show implements ShowInterface{
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    public void show(PrintWriter out) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d( %s ): %s %s%n", (task.isDone() ? 'x' : ' '), task.getId(),(task.getCustomId()!=null?task.getCustomId():""), task.getDescription(), (task.getDeadline()!= null ? dateFormatter.format(task.getDeadline()):""));
            }
            out.println();
        }
    }
}
