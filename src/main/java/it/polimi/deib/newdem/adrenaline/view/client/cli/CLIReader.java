package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.view.client.ClosedException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CLIReader {

    private String line;

    private InputStream in;

    private Thread readerThread;

    public CLIReader(InputStream in) {
        this.line = null;
        this.in = in;
    }

    public void start() {
        readerThread = (new Thread(this::runReader));
        readerThread.start();
    }

    private void runReader() {
        try {
            BufferedReader scanner = new BufferedReader(new InputStreamReader(in));
            while (true) {
                String newLine = scanner.readLine();
                synchronized (this) {
                    this.line = newLine;
                    notifyAll();
                }
            }
        } catch (Exception x) {
            // just end the thread.
        }
    }

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


    public void close() {
        try {
            in.close();
        } catch (Exception x) {
            //no problem
        }
        if (readerThread.isAlive()) readerThread.interrupt();
    }

}
