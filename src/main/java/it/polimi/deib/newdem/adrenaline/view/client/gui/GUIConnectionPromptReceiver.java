package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.view.ConnectionView;

/**
 * Callback used to notify UI events from the GUIConnectionWindow class.
 */
public interface GUIConnectionPromptReceiver {

    void deliverData(String host, int port, ConnectionView.ConnectionType cType);

}
