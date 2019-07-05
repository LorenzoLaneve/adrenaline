package it.polimi.deib.newdem.adrenaline.view.client.gui;

/**
 * Interface for objects that receive callbacks containing the user choice for the username.
 */
public interface GUIUsernamePromptReceiver {

    void deliverUsername(String username);

}
