/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domain.GenericEntity;
import domain.Guest;
import domain.User;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Luka
 */
public class Controller {
    private static Controller instance;
    Socket socket;
    Sender sender;
    Receiver receiver;

    private Controller() throws IOException {
        this.socket = new Socket("127.0.0.1", 9000);
        this.sender = new Sender(socket);
        this.receiver = new Receiver(socket);
    }

    public static Controller getInstance() throws IOException {
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }
    
    public Response login(User u) throws Exception{
        Request r = new Request();
        r.setArgument(u);
        r.setOperation(Operation.LOGIN);
        sender.send(r);
        Response response = (Response) receiver.receive();
        return response;
    }
    
    public Response GenericOperation(Object g, Operation operation) throws Exception{
        Request r = new Request();
        r.setArgument(g);
        r.setOperation(operation);
        sender.send(r);
        Response response = (Response) receiver.receive();
        return response;
    }    
}
