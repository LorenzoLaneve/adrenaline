package it.polimi.deib.newdem.adrenaline.model.mgmt;

import java.util.ArrayList;
import java.util.List;

public class LobbyImpl implements Lobby {

    private List<User> users;

    private LobbyListener listener;

    private boolean timerActive;

    private int secondsLeft;


    /**
     * Creates a new and empty lobby.
     */
    public LobbyImpl() {
        this.users = new ArrayList<>();
        this.timerActive = false;
    }

    @Override
    public void setListener(LobbyListener listener) {
        this.listener = listener;
    }

    @Override
    public synchronized void addUser(User user) {
        this.users.add(user);

        // TODO listener callback
    }

    @Override
    public synchronized void removeUser(User user) {
        this.users.remove(user);

        // TODO listener callback
    }

    @Override
    public void startTimer(int secondsLeft) {
        this.timerActive = true;
        this.secondsLeft = secondsLeft;

        // TODO listener callback
    }

    @Override
    public void refreshTimer(int secondsLeft) {
        this.secondsLeft = secondsLeft;

        // TODO listener callback
    }

    @Override
    public void abortTimer() {
        this.timerActive = false;

        // TODO listener callback
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

}
