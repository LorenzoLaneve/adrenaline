package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

/**
 * Interface for a generic lobby state.
 * This is a variant of the State pattern that also provides collateral methods.
 */
public interface LobbyState {

    /**
     * Returns a new state for the lobby following a new user event.
     */
    LobbyState userDidEnterLobby(User user, LobbyController lobby);

    /**
     * Returns a new state for the lobby following a user leave event.
     */
    LobbyState userDidExitLobby(User user, LobbyController lobby);

    /**
     * Notifies that the lobby will enter this state.
     */
    void lobbyWillEnterState(LobbyController lobby);

    /**
     * Notifies that the lobby did change state and leave this state.
     */
    void lobbyDidExitState(LobbyController lobby);

    /**
     * Returns whether the lobby accepts new users in this state.
     */
    boolean acceptsNewUsers();
}
