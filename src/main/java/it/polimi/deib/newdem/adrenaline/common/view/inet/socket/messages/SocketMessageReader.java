package it.polimi.deib.newdem.adrenaline.common.view.inet.socket.messages;

import it.polimi.deib.newdem.adrenaline.common.view.inet.events.UserEvent;
import it.polimi.deib.newdem.adrenaline.common.view.inet.socket.InvalidMessageException;
import it.polimi.deib.newdem.adrenaline.common.view.inet.socket.SocketMessage;

import java.io.DataInputStream;
import java.io.IOException;

public interface SocketMessageReader {

    /**
     * Creates a new UserEvent object representing the message read in the given input stream.
     * @throws IOException if an IO error occurs while the message is read.
     * @throws InvalidMessageException if the data in the stream does not follow the protocol.
     */
    UserEvent make(DataInputStream input) throws IOException, InvalidMessageException;


    /**
     * Creates an object that  a user event from a socket's stream.
     * @throws InvalidMessageException if the given type is not supported by the used protocol.
     * @see SocketMessage for possible message types.
     */
    static SocketMessageReader fromMessageType(int type) throws InvalidMessageException {
        switch (type) {
            case SocketMessage.UPDATE_USER_NAME:
                return new UpdateUsernameMessageReader();
            case SocketMessage.ENTER_LOBBY:
                //

            default:
                throw new InvalidMessageException("type is not a valid message type.");
        }
    }

}
