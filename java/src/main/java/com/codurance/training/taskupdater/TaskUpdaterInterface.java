package com.codurance.training.taskupdater;

import com.codurance.training.tasks.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public interface TaskUpdaterInterface {
    public void updateTask(String idString, boolean done, PrintWriter out);
}
