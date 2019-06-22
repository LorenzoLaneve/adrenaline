package it.polimi.deib.newdem.adrenaline.controller;

import java.util.HashMap;
import java.util.Map;

public class TimedExecutor implements TimerListener {

    private static final Map<Thread, TimedExecutor> executorThreads = new HashMap<>();

    private Timer timer;

    private TimerListener listener;

    private Runnable r;

    private boolean isOver;

    private boolean timeout;

    private boolean abort;

    public TimedExecutor(Runnable r) {
        this(r, null);
    }

    public TimedExecutor(Runnable r, TimerListener listener) {
        this.r = r;
        this.isOver = false;
        this.timeout = false;
        this.abort = false;
        this.listener = listener;
    }

    public synchronized void execute(int seconds) throws TimeoutException, AbortedException {
        Thread executionThread = new Thread(this::run);
        synchronized (executorThreads) {
            executorThreads.put(executionThread, this);
        }
        executionThread.start();

        timer = new Timer(1, this);
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

        synchronized (executorThreads) {
            executorThreads.remove(executionThread);
        }
        timer = null;

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


    public static void abortExecution() {
        throw new InterruptExecutionException("Abort requested by execution thread.");
    }

    public static void pauseTimer() {
        TimedExecutor executor;
        synchronized (executorThreads) {
            executor = executorThreads.get(Thread.currentThread());
        }
        if (executor != null) {
            executor.timer.pause();
        }
    }

    public static void resumeTimer() {
        TimedExecutor executor;
        synchronized (executorThreads) {
            executor = executorThreads.get(Thread.currentThread());
        }
        if (executor != null) {
            executor.timer.resume();
        }
    }


    @Override
    public void timerWillStart(int secondsLeft) {
        if (listener != null) listener.timerWillStart(secondsLeft);
    }

    @Override
    public void timerSync(int secondsLeft) {
        if (listener != null) listener.timerSync(secondsLeft);
    }

    @Override
    public synchronized void timerDidFinish() {
        timeout = true;
        notifyAll();

        if (listener != null) listener.timerDidFinish();
    }

    @Override
    public void timerDidAbort() {
        if (listener != null) listener.timerDidAbort();
    }

}
