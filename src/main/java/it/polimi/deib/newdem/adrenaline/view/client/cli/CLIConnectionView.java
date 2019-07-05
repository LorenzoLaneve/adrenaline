package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.view.ConnectionView;

import java.io.PrintStream;
import java.util.logging.Logger;

/**
 * Command Line Interface implementation of {@code ConnectionView}
 * @see ConnectionView for the semantics of the view methods.
 */
public class CLIConnectionView implements ConnectionView {

    private Logger log;
    private PrintStream out;
    private CLIReader in;

    private String host;
    private int port;
    private ConnectionType type;

    public CLIConnectionView(Logger log, PrintStream out, CLIReader in) {
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

        out.print("Server host: ");
        host = in.nextLine();

        boolean portOk = false;
        do {
            try {
                out.print("Server port: ");
                port = Integer.valueOf(in.nextLine());
                portOk = true;
            } catch (Exception x) {
                out.println("Error: port must be a valid number.");
            }
        } while (!portOk);


        do {
            out.print("(S)ockets or (R)MI? ");

            switch (in.nextLine().toLowerCase()) {
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
