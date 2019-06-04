package it.polimi.deib.newdem.adrenaline.view.inet;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class UserConnectionBase implements UserConnection {

    private List<UserConnectionReceiver> receiverList;

    private User user;


    public UserConnectionBase(User user) {
        this.receiverList = new ArrayList<>();

        this.user = user;
        user.bindConnection(this);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void addReceiver(UserConnectionReceiver receiver) {
        receiverList.add(receiver);
    }

    @Override
    public void removeReceiver(UserConnectionReceiver receiver) {
        receiverList.remove(receiver);
    }

    protected List<UserConnectionReceiver> getReceiverList() {
        return new ArrayList<>(receiverList);
    }

    @Override
    public void close() {
        for (UserConnectionReceiver receiver : getReceiverList()) {
            receiver.connectionDidClose(this);
        }

        user.bindConnection(null);
    }

    protected void notifyEvent(UserEvent event) {
        for (UserConnectionReceiver receiver : getReceiverList()) {
            event.notifyEvent(this, receiver);
        }
    }

}
