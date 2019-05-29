package it.polimi.deib.newdem.adrenaline.controller;

public class TimedExecutor implements TimerListener {

    private Runnable r;

    private boolean isOver;

    private boolean timeout;

    private boolean abort;


    public TimedExecutor(Runnable r) {
        this.r = r;
        this.isOver = false;
        this.timeout = false;
        this.abort = false;
    }

    public synchronized void execute(int seconds) throws TimeoutException, AbortedException {
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

        if (timeout || abort) {
            executionThread.interrupt();
            try {
                executionThread.join();
            } catch (InterruptedException x) {
                Thread.currentThread().interrupt();
            }
        }

        if (timeout && !isOver) {
            throw new TimeoutException("The runnable object ran out of time.");
        }

        if (abort && !isOver) {
            throw new AbortedException("The execution was aborted by an external thread.");
        }
    }

    private void run() {
        try {
            this.r.run();
        } catch (InterruptExecutionException x) {
            Thread.currentThread().interrupt();
        }

        synchronized (this) {
            isOver = true;
            notifyAll();
        }
    }

    public synchronized void abort() {
        if (!isOver && !timeout) {
            this.abort = true;
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
