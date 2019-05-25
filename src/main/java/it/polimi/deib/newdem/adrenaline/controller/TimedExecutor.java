package it.polimi.deib.newdem.adrenaline.controller;

public class TimedExecutor implements TimerListener {

    private Runnable r;

    private boolean isOver;

    private boolean timeout;


    public TimedExecutor(Runnable r) {
        this.r = r;
        this.isOver = false;
    }

    public synchronized void execute(int seconds) throws TimeoutException {
        Thread executionThread = new Thread(this::run);
        executionThread.start();

        Timer timer = new Timer(1, this);
        timer.start(seconds);

        try {
            while (!isOver && !timeout) wait();
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
        }

        if (isOver) {
            timer.abort();
        }

        if (timeout) {
            executionThread.interrupt();
        }

        if (timeout && !isOver) {
            throw new TimeoutException("The runnable object run out of time.");
        }
    }

    private void run() {
        this.r.run();

        synchronized (this) {
            isOver = true;
            notifyAll();
        }
    }

    @Override
    public void timerWillStart(int secondsLeft) {
        // TODO
    }

    @Override
    public void timerSync(int secondsLeft) {
        // TODO
    }

    @Override
    public synchronized void timerDidFinish() {
        timeout = true;
        notifyAll();
    }

    @Override
    public void timerDidAbort() {
        // TODO
    }
}
