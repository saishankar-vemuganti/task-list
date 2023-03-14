package com.codurance.training.commanderror;

import java.io.PrintWriter;

public interface CommandErrorInterface {
    public void error(String command, PrintWriter out);
}
