package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.view.client.ClosedException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Object that implements an interruptible reader.
 * This encapsulation allows the string reading from streams like standard input
 * to be more user-friendly, while allowing concurrent thread to interrupt reading when needed.
 */
public class CLIReader {

    private String line;

    private InputStream in;

    private Thread readerThread;

    /**
     * Creates a new reader that takes input from the given InputStream.
     */
    public CLIReader(InputStream in) {
        this.line = null;
        this.in = in;
    }

    /**
     * Starts the reader.
     */
    public void start() {
        readerThread = (new Thread(this::runReader));
        readerThread.start();
    }

    private void runReader() {
        try {
            BufferedReader scanner = new BufferedReader(new InputStreamReader(in));

            String newLine;
            while ((newLine = scanner.readLine()) != null) {
                synchronized (this) {
                    this.line = newLine;
                    notifyAll();
                }
            }
        } catch (Exception x) {
            // just end the thread.
        }
    }

    /**
     * Reads a new line from the enclosing input stream.
     * @throws ClosedException if the caller thread gets interrupted while trying to read this exception.
     */
    public synchronized String nextLine() {
        this.line = null;

        try {
            while (line == null) wait();
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt();
            throw new ClosedException("Close requested.");
        }

        String ret = line;
        this.line = null;
        return ret;
    }

    /**
     * Closes the reader and the enclosing stream.
     */
    public void close() {
        try {
            in.close();
        } catch (Exception x) {
            //no problem
        }
        if (readerThread.isAlive()) readerThread.interrupt();
    }

}
