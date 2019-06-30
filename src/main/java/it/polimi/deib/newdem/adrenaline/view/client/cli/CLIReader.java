package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.view.client.ClosedException;

import java.io.InputStream;
import java.util.Scanner;

public class CLIReader {

    private String line;

    private Scanner scanner;

    public CLIReader(InputStream in) {
        this.line = null;
        this.scanner = new Scanner(in);
    }

    public void start() {
        (new Thread(this::runReader)).start();
    }

    private void runReader() {
        while (scanner.hasNext()) {
            String newLine = scanner.next();
            synchronized (this) {
                this.line = newLine;
                notifyAll();
            }
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
        scanner.close();
    }

}
