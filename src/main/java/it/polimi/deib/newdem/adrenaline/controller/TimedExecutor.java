package it.polimi.deib.newdem.adrenaline.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Object that executes a given runnable object within a certain period of time.
 * The timer will determine the time after which the execution will be interrupted.
 */
public class TimedExecutor implements TimerListener {

    private static final Map<Thread, TimedExecutor> executorThreads = new HashMap<>();

    private Timer timer;

    private TimerListener listener;

    private Runnable r;

    private boolean isOver;

    private boolean timeout;

    private boolean abort;

    /**
     * Initializes the TimedExecutor object with the runnable that needs to be executed.
     */
    public TimedExecutor(Runnable r) {
        this(r, null);
    }

    /**
     * Initializes the TimedExecutor object with the runnable that needs to be executed and a TimerListener object that
     * listens to the Timer keeping the time for the execution.
     */
    public TimedExecutor(Runnable r, TimerListener listener) {
        this.r = r;
        this.isOver = false;
        this.timeout = false;
        this.abort = false;
        this.listener = listener;
    }

    /**
     * Executes the runnable passed to the constructor. If the execution ends before the time is up,
     * the timer will be aborted and this method will return.
     * @param seconds The time limit given to finish the execution.
     * @throws TimeoutException If the time is up and the execution has not ended.
     * @throws AbortedException If abort() was called on this object during the execution.
     */
    public void execute(int seconds) throws TimeoutException, AbortedException {
        if (abort && !isOver) {
            throw new AbortedException("The execution was aborted by an external thread.");
        }

        Thread executionThread = new Thread(this::run);
        synchronized (executorThreads) {
            executorThreads.put(executionThread, this);
        }

        timer = new Timer(1, this);
        timer.start(seconds);

        executionThread.start();

        try {
            synchronized (this) {
                while (!isOver && !abort && !timeout) wait();
            }
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
        }

        if (isOver || abort) {
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

    /**
     * Aborts the execution, requesting an interrupt on the execution thread.
     */
    public synchronized void abort() {
        if (!isOver && !timeout) {
            this.abort = true;
            notifyAll();
        }
    }

    /**
     * Aborts the execution of the current thread and throws an {@code InterruptExecutionException}.
     */
    public static void abortExecution() {
        TimedExecutor executor;
        synchronized (executorThreads) {
            executor = executorThreads.get(Thread.currentThread());
        }
        if (executor != null) {
            executor.abort = true;
        }
        throw new InterruptExecutionException("Abort requested by execution thread.");
    }

    /**
     * Pauses the timer of the TimedExecutor associated to the current thread.
     * If the current thread has not been launched by a TimedExecutor this call will be ignored.
     */
    public static void pauseTimer() {
        TimedExecutor executor;
        synchronized (executorThreads) {
            executor = executorThreads.get(Thread.currentThread());
        }
        if (executor != null) {
            executor.timer.pause();
        }
    }

    /**
     * Resumes the timer of the TimedExecutor associated to the current thread.
     * If the current thread has not been launched by a TimedExecutor this call will be ignored.
     */
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
