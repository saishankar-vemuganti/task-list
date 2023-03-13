package com.codurance.training.help;

import java.io.PrintWriter;

public class Help implements HelpInterface{
    public void showHelp(PrintWriter out) {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  deadline <task ID> dd/mm/yyyy");
        out.println("  today");
        out.println("  customid <task ID> <custom ID>");
        out.println("  delete <task ID>");
        out.println("  viewby date dd/mm/yyyy");
        out.println("  viewby deadline dd/mm/yyyy");
        out.println();
    }
}
