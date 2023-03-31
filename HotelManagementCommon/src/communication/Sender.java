/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Luka
 */
public class Sender {
    private Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }
    
    public void send(Object object) throws Exception{
        try{
            ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
            o.writeObject(object);
            o.flush();
        }
        catch(Exception e){
            e.printStackTrace();
            throw new Exception("Error sending object!\n"+e.getMessage());
        }
    }
}
