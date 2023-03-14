package com.codurance.training.show;

import com.codurance.training.tasks.Task;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static com.codurance.training.manage.Manager.tasks;


public class ShowByDeadline implements ShowByDateInterface {

    @Override
    public void show(PrintWriter out, String date) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            Date requiredDate = dateFormatter.parse(date);
            for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
                out.println(project.getKey());
                for (Task task : project.getValue()) {
                    if (task.getDeadline() != null && LocalDate.from(task.getDeadline().toInstant().atZone(ZoneId.systemDefault())).isEqual(LocalDate.from(requiredDate.toInstant().atZone(ZoneId.systemDefault()))))
                        out.printf("    [%c] %d( %s ): %s %s%n", (task.isDone() ? 'x' : ' '), task.getId(), (task.getCustomId() != null ? task.getCustomId() : ""), task.getDescription(), (task.getDeadline() != null ? dateFormatter.format(task.getDeadline()) : ""));
                }
                out.println();
            }
        } catch (Exception e) {
            System.out.println("Invalid date format! Please enter date in DD/MM/YYYY format.");
        }
    }
}
