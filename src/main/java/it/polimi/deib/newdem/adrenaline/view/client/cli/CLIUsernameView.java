package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.view.UsernameView;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Logger;

public class CLIUsernameView implements UsernameView {

    private Logger log;
    private PrintStream out;
    private InputStream in;

    public CLIUsernameView(Logger log, PrintStream out, InputStream in) {
        this.log = log;
        this.out = out;
        this.in = in;
    }

    @Override
    public String prompt() {
        Scanner sc = new Scanner(in);

        out.print("Enter your username: ");
        return sc.next();
    }

    @Override
    public void reportError(String message) {
        out.println("Error with your username: "+ message);
    }

    @Override
    public void reportSuccess() {
        out.println("Username accepted.");
    }

}
