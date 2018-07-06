package org.dqw4w9wgxcq.api.fw.task;

public interface Task {
    boolean validate();
    boolean running();
    int execute();
}
