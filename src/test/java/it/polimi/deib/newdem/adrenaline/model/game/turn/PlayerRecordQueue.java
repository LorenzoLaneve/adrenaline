package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.ArrayDeque;
import java.util.Deque;

public class PlayerRecordQueue {

    private Deque<PlayerRecordType> playerRecordTypeDeque;
    private Deque<Player> playerObjectDeque;

    public PlayerRecordQueue() {
        this.playerRecordTypeDeque = new ArrayDeque<>();
        playerObjectDeque = new ArrayDeque<>();
    }

    public void pushPlayer(Player player) {
        playerRecordTypeDeque.push(PlayerRecordType.ACTUAL_PLAYER);
        playerObjectDeque.push(player);
    }

    public void pushNull() {
        playerRecordTypeDeque.push(PlayerRecordType.NULL);
    }

    public void pushUndo() {
        playerRecordTypeDeque.push(PlayerRecordType.UNDO);
    }

    public Player pop() throws UndoException {
        switch (playerRecordTypeDeque.pop()) {
            case UNDO:
                throw new UndoException();
            case NULL:
                return null;
            case ACTUAL_PLAYER:
                return playerObjectDeque.pop();
            default:
                return null;
        }
    }
}
