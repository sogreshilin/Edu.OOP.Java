package model;

import model.message.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Alexander on 03/06/2017.
 */
public class ClientMessageHandler implements MessageHandler {

    private Client client;
    private static final Logger log = LogManager.getLogger(Client.class);

    public ClientMessageHandler(Client client) {
        this.client = client;
    }

    @Override
    public void process(LoginSuccess message) {
        client.notifyObservers(message);
        client.setSessionID(message.getSessionID());
        client.loggedIn(true);
        log.info("logged in as {}", client.getName());
        log.info("session ID is {}", client.getSessionID());
        ListUsersRequest msg = new ListUsersRequest();
        msg.setSessionID(message.getSessionID());
        client.addOutgoingMessage(msg);
    }

    @Override
    public void process(LoginError message) {
        client.notifyObservers(message);
        log.info("got LoginError message : {}", message.getError());
    }

    @Override
    public void process(ListUsersSuccess message) {
        for (String user : message.getUsers()) {
            client.addUser(user);
            log.info("add user to userlist {}", user);
        }
        client.notifyObservers(message);
    }

    @Override
    public void process(ListUsersError message) {
        log.error("got ListUsersError message : {}", message.getError());
    }

    @Override
    public void process(TextSuccess message) {
        //TODO
        log.info("message delivered successfully");
    }

    @Override
    public void process(TextError message) {
        //TODO
        log.info("message was not delivered");
    }

    @Override
    public void process(LogoutSuccess message) {
        client.removeAllUsers();
        client.notifyObservers(message);
        client.finish();
        client.loggedIn(false);
        log.info("logout succeeded");
    }

    @Override
    public void process(LogoutError message) {
        client.notifyObservers(message);
        log.error("got LogoutError message : {}", message.getError());
    }

    @Override
    public void process(UserLoginMessage message) {
        if (client.loggedIn()) {
            client.addUser(message.getName());
            client.notifyObservers(message);
        }
    }

    @Override
    public void process(UserLogoutMessage message) {
        if (client.loggedIn()) {
            client.removeUser(message.getName());
            client.notifyObservers(message);
        }
    }

    @Override
    public void process(UserMessage message) {
        log.info("got message \"{}\" from {}", message.getMessage(), message.getName());
        client.notifyObservers(message);
    }
}