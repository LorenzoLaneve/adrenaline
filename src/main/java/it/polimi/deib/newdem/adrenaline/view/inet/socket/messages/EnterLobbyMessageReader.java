package it.polimi.deib.newdem.adrenaline.view.inet.socket.messages;

import it.polimi.deib.newdem.adrenaline.view.inet.events.EnterLobbyEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.socket.InvalidMessageException;

import java.io.DataInputStream;
import java.io.IOException;

import static it.polimi.deib.newdem.adrenaline.view.inet.socket.StreamHelper.readString;

public class EnterLobbyMessageReader implements SocketMessageReader {


    @Override
    public UserEvent make(DataInputStream input) throws IOException, InvalidMessageException {
        return new EnterLobbyEvent( readString(input) );
    }
}
