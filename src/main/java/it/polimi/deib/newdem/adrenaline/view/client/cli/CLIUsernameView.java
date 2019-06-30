package it.polimi.deib.newdem.adrenaline.view.client.cli;

import it.polimi.deib.newdem.adrenaline.view.UsernameView;

import java.io.PrintStream;

public class CLIUsernameView implements UsernameView {

    private PrintStream out;
    private CLIReader in;

    public CLIUsernameView(PrintStream out, CLIReader in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public String prompt() {
        out.print("Enter your username: ");
        return in.nextLine();
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
