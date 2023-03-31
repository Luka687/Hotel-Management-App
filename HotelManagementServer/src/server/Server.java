/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.thread.ProcessClientRequests;

/**
 *
 * @author Luka
 */
public class Server extends Thread{
    private static Server instance;
    private ServerSocket serverSocket;
    private List <ProcessClientRequests> clients = new ArrayList();
    private boolean end = false;

    public ServerSocket getSocket() {
        return serverSocket;
    }

    private Server() {
        try {
            serverSocket = new ServerSocket(9000);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Server getInstance() {
        if (instance == null){
            instance = new Server();
        }
        return instance;
    }   

    @Override
    public void run() {
        while (!end){
            try {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                ProcessClientRequests client = new ProcessClientRequests(socket, this);
                client.start();
                clients.add(client);
                System.out.println("Accepted connection from: " + socket.getInetAddress().toString());
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Logger.getLogger(Server.class.getName()).log(Level.INFO, "Closing server...");
        //System.out.println("Closing server...");
    }

    public List<ProcessClientRequests> getClients() {
        return clients;
    }
    
    public void shutdown(){
        try {
            end = true;
            instance = null;
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
