package it.polimi.deib.newdem.adrenaline.view.server.dialogs;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.events.PlayerSelectionRequest;
import it.polimi.deib.newdem.adrenaline.view.server.ServerConnectionReceiver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerDialog {

    private ServerConnectionReceiver receiver;

    private List<Player> players;

    private Player response;

    private User user;

    /**
     * Initializes a dialog with a list of selectable players.
     */
    public PlayerDialog(ServerConnectionReceiver receiver, List<Player> players) {
        this.receiver = receiver;
        this.players = new ArrayList<>(players);

        this.response = null;
    }

    /**
     * Asks the given user to choose among the possible players given to the constructor.
     * The current thread will be blocked until the user makes their choice
     * @return The choice sent by the player.
     * @throws InterruptedException if the thread is interrupted while it's waiting for the response.
     */
    public synchronized Player requestPlayer(User user) throws InterruptedException {
        this.user = user;
        ask(false);

        while (response == null) {
            wait();
        }

        Player retValue = response;
        this.response = null;
        this.user = null;
        return retValue;
    }

    /**
     * Delivers the given user's choice to the dialog, notifying the waiting thread that the choice was made.
     * If the response doesn't fit the requirements given by the dialog, it will be discared and the user
     * will be asked to send a new response.
     */
    public synchronized void deliver(Player response) {
        if (response == null)
            throw new IllegalArgumentException("The response must be non-null.");

        if (players.contains(response)) {
            this.response = response;
            notifyAll();
        } else {
            ask(true); // re-ask the user, telling him there was an error.
        }
    }


    private void ask(boolean error) { // maybe making this method abstract is better.

        List<PlayerColor> playerColors = players.stream().map(Player::getColor).collect(Collectors.toList());

        user.sendEvent(new PlayerSelectionRequest(playerColors, error));
    }

}
