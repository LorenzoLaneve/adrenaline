package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.view.client.ConnectionView;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Logger;

public class CLIConnectionView implements ConnectionView {

    private Logger log;
    private PrintStream out;
    private InputStream in;

    private String host;
    private int port;
    private ConnectionType type;

    public CLIConnectionView(Logger log, PrintStream out, InputStream in) {
        this.log = log;
        this.out = out;
        this.in = in;
    }

    @Override
    public String getServerHost() {
        return host;
    }

    @Override
    public int getServerPort() {
        return port;
    }

    @Override
    public ConnectionType getConnectionType() {
        return type;
    }

    @Override
    public void prompt() {
        type = null;

        Scanner sc = new Scanner(in);

        out.print("Server host: ");
        host = sc.next();

        out.print("Server port: ");
        port = sc.nextInt();

        do {
            out.print("(S)ockets or (R)MI? ");

            switch (sc.next().toLowerCase()) {
                case "s":
                    type = ConnectionType.SOCKETS;
                    break;
                case "r":
                    type = ConnectionType.RMI;
                    break;
                default: break;
            }
        } while (type == null);

    }

    @Override
    public void reportError(String message) {
        log.severe("A connection error occurred: "+ message);
    }

    @Override
    public void reportSuccess() {
        log.info("Connection OK.");
        out.println("Successfully connected to "+ host +":"+ port);
    }

}
