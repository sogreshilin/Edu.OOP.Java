package model.message;

import model.MessageHandler;
import model.Server;

/**
 * Created by Alexander on 03/06/2017.
 */
public interface ClientMessage extends Message {
    void process(Server server, Server.ClientHandler handler);
}
