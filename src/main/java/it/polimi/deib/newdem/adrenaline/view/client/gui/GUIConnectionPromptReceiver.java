package it.polimi.deib.newdem.adrenaline.view.client.gui;

import it.polimi.deib.newdem.adrenaline.view.client.ConnectionView;

public interface GUIConnectionPromptReceiver {

    void deliverData(String host, int port, ConnectionView.ConnectionType cType);

}
