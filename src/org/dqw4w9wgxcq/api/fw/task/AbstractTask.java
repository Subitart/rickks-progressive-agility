package org.dqw4w9wgxcq.api.fw.task;

public abstract class AbstractTask implements Task{
    private boolean running = true;

    @Override
    public boolean running() {
        return running;
    }

    public void stopRunning(){
        running = false;
    }
}
