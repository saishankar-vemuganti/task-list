package com.codurance.training.manage;

import com.codurance.training.operations.*;
import com.codurance.training.commanderror.CommandError;
import com.codurance.training.commanderror.CommandErrorInterface;
import com.codurance.training.help.Help;
import com.codurance.training.help.HelpInterface;
import com.codurance.training.show.*;
import com.codurance.training.tasks.Task;
import com.codurance.training.taskupdater.TaskUpdater;
import com.codurance.training.taskupdater.TaskUpdaterInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Manager implements Runnable {
    private static final String QUIT = "quit";

    public static Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private final BufferedReader in;
    private final PrintWriter out;

    public ShowInterface show = new Show();
    public HelpInterface help = new Help();
    public OperationInterface addProject = new AddProject();
    public OperationInterface addTask = new AddTask();
    public TaskUpdaterInterface taskUpdater = new TaskUpdater();
    public CommandErrorInterface commandError = new CommandError();
    public OperationInterface addDeadlineToTask = new AddDeadlineToTask();
    public OperationInterface addCustomidToTask= new AddCustomIdentifier();
    public OperationInterface deleteTask =new DeleteTask();
    public ShowByDateInterface showByDateCreated = new ShowByCreatedDate();
    public ShowByDateInterface showByDeadline = new ShowByDeadline();
    public ShowByProjectIdInterface showByProjectId = new ShowByProjectId();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new Manager(in, out).run();
    }

    public Manager(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show.show(out);
                break;
            case "add" :
                String[] subcommandRest = commandRest[1].split(" ", 2);
                String subcommand = subcommandRest[0];
                if (subcommand.equals("project")) {
                    addProject.operate(commandRest[1],out);
                } else if (subcommand.equals("task")) {
                    addTask.operate(commandRest[1],out);
                }
                break;
            case "check":
                taskUpdater.updateTask(commandRest[1],true,out);
                break;
            case "uncheck":
                taskUpdater.updateTask(commandRest[1],false,out);
                break;
            case "deadline" :
                addDeadlineToTask.operate(commandRest[1],out);
                break;
            case "today" :
                showByDeadline.show(out, formatter.format(new Date()));
                break;
            case "customid":
                addCustomidToTask.operate(commandRest[1],out);
                break;
            case "delete":
                deleteTask.operate(commandRest[1],out);
                break;
            case "help":
                help.showHelp(out);
                break;
            case "viewby" :
               subcommandRest = commandRest[1].split(" ", 2);
                subcommand = subcommandRest[0];
                switch (subcommand) {
                    case "date":
                        showByDateCreated.show(out, subcommandRest[1]);
                        break;
                    case "deadline":
                        showByDeadline.show(out, subcommandRest[1]);
                        break;
                    case "project":
                        showByProjectId.show(out,subcommandRest[1]);
                        break;
                }
                break;
            default:
                commandError.error(command,out);
                break;
        }
    }
}
