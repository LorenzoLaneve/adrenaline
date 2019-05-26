package it.polimi.deib.newdem.adrenaline.view.inet.socket.messages;

import it.polimi.deib.newdem.adrenaline.view.inet.events.ExitLobbyEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.socket.InvalidMessageException;

import java.io.DataInputStream;
import java.io.IOException;

import static it.polimi.deib.newdem.adrenaline.view.inet.socket.StreamHelper.readString;

public class ExitLobbyMessageReader implements  SocketMessageReader {
    @Override
    public UserEvent make(DataInputStream input) throws IOException, InvalidMessageException {
        return new ExitLobbyEvent( readString(input) );
    }
}
