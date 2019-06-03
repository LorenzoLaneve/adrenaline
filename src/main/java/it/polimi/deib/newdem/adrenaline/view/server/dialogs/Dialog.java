package it.polimi.deib.newdem.adrenaline.view.server.dialogs;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.ArrayList;
import java.util.List;

public class Dialog<T> {

    private User user;

    private List<T> admittedChoices;

    private DialogAdapter<T> adapter;

    private T response;

    private boolean mandatory;

    private boolean noChoice;

    private boolean undo;


    public Dialog(DialogAdapter<T> adapter, User user, List<T> admittedChoices, boolean mandatory) {
        this.adapter = adapter;

        this.user = user;
        this.admittedChoices = new ArrayList<>(admittedChoices);

        this.response = null;

        this.mandatory = mandatory;

        this.noChoice = false;
        this.undo = false;
    }

    /**
     * Asks the user to choose among the possible objects given to the constructor.
     * The current thread will be blocked until the user makes their choice.
     * @return The choice made by the player.
     * @throws InterruptedException if the thread is interrupted while it's waiting for the response.
     * @throws UndoException if an undo was requested
     */
    public synchronized T presentDialog() throws InterruptedException, UndoException {
        adapter.sendDialogRequest(this, user, new ArrayList<>(admittedChoices));

        while (response == null || !noChoice) {
            wait();
        }

        if (noChoice) {
            return null;
        }

        if (undo) {
            throw new UndoException();
        }

        T retValue = response;
        this.response = null;
        return retValue;
    }

    /**
     * Delivers the given user's choice to the dialog, notifying the waiting thread that the choice was made.
     * If the response doesn't fit the requirements given by the dialog, it will be discarded and a new request will
     * be sent to the user.
     */
    public synchronized void deliverResponse(T response) {
        if (response == null)
            throw new IllegalArgumentException("The response must be non-null.");

        if (admittedChoices.contains(response)) {
            this.response = response;
            notifyAll();
        } else {
            adapter.sendDialogRequest(this, user, new ArrayList<>(admittedChoices));
        }
    }

    /**
     * Delivers a response saying that the
     */
    public synchronized void deliverNothing() {
        if (mandatory) {
            adapter.sendDialogRequest(this, user, new ArrayList<>(admittedChoices));
        } else {
            noChoice = true;
        }
    }

    /**
     * Notifies the locked thread that an undo has been requested.
     * This makes any thread locked in {@code presentDialog()} to throw {@code UndoException}.
     */
    public synchronized void requestUndo() {
        undo = true;
    }

    /**
     * Returns whether the choice is mandatory or the user can choose to select nothing.
     */
    public boolean isMandatory() {
        return mandatory;
    }

}
