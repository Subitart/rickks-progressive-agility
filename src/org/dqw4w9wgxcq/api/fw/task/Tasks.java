package org.dqw4w9wgxcq.api.fw.task;

public final class Tasks {
    private Tasks(){}

    public static Task never(){
        return new Task() {
            @Override
            public boolean validate() {
                return false;
            }

            @Override
            public boolean running() {
                return false;
            }

            @Override
            public int execute() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
