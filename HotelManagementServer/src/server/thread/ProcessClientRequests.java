/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.thread;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.Board;
import domain.GenericEntity;
import domain.Guest;
import domain.Reservation;
import domain.ReservationItem;
import domain.Room;
import domain.User;
import java.net.Socket;
import java.util.List;
import server.Server;

/**
 *
 * @author Luka
 */
public class ProcessClientRequests extends Thread{
    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private Server server;
    private boolean kraj = false;    

    public ProcessClientRequests(Socket socket, Server server) {
        this.socket = socket;
        this.sender = new Sender(socket);
        this.receiver = new Receiver(socket);
        this.server = server;
    }

    @Override
    public void run() {
        while(!kraj){
            try{
               Request request = (Request) receiver.receive();
               Response response = new Response();
               try{
                   switch (request.getOperation()){
                       case LOGIN:
                           User u = (User) request.getArgument();
                           GenericEntity entity = Controller.getInstance().login(u.getUsername(), u.getPassword());
                           if (entity == null){
                               Exception e = new Exception("User does not have access privileges!");
                               response.setException(e);
                               response.setMessage(e.getMessage());
                           }
                           else{
                               if (!Controller.getInstance().getActive_users().contains(entity)){
                                   Controller.getInstance().getActive_users().add(entity);
                                   response.setResult(u);
                                   response.setMessage("User "+ u.getUsername() + " successfully logged in!");
                               }
                               else{
                                   Exception e = new Exception("User is already logged in!");
                                   response.setException(e);
                                   response.setMessage(e.getMessage());
                               }
                           }
                           break;
                       case GET_ALL_GUESTS:
                           List <GenericEntity> guests = Controller.getInstance().getAllGuests();
                           response.setResult(guests);
                           response.setMessage("Successfully retrieved list of entities!");
                           break;
                       case FILTER_GUESTS:
                           List <GenericEntity> filtered_guests = Controller.getInstance().filterGuests(request.getArgument().toString());
                           response.setResult(filtered_guests);
                           response.setMessage("Successfully retrieved list of entities!");
                           break;
                       case DELETE_GUEST:
                           Controller.getInstance().deleteGuest((Guest) request.getArgument());
                           response.setMessage("The requested object was successfully deleted!");      
                           break;
                       case EDIT_GUEST:
                           Controller.getInstance().editGuest((Guest) request.getArgument());
                           response.setMessage("Successfully saved changes!");
                           break;
                       case ADD_GUEST:
                           Controller.getInstance().addGuest((Guest) request.getArgument());
                           response.setMessage("Successfully added object!");
                           break;
                       case ADD_BOARD:
                           Controller.getInstance().addBoard((Board) request.getArgument());
                           response.setMessage("Successfully added object!");
                           break;
                       case GET_ALL_ROOMS:
                           List <GenericEntity> rooms = Controller.getInstance().getAllRooms();
                           response.setResult(rooms);
                           response.setMessage("Successfully retrieved list of entities!");
                           break;
                       case GET_ALL_BOARDS:
                           List <GenericEntity> boards = Controller.getInstance().getAllBoards();
                           response.setResult(boards);
                           response.setMessage("Successfully retrieved list of entities!");
                           break; 
                       case EDIT_BOARD:
                           Controller.getInstance().editBoard((Board) request.getArgument());
                           response.setMessage("Successfully saved changes!");
                           break;
                       case ADD_RESERVATION:
                           Controller.getInstance().addReservation((Reservation) request.getArgument());
                           response.setMessage("Successfully added object!");
                           break;
                       case GET_ALL_RESERVATIONS:
                           List <GenericEntity> reservations = Controller.getInstance().getAllReservations();
                           response.setResult(reservations);
                           response.setMessage("Successfully retrieved list of entities!");
                           break;
                       case GET_ALL_RESERVATION_ITEMS:
                           List <GenericEntity> reservation_items = Controller.getInstance().getAllReservationItems();
                           response.setResult(reservation_items);
                           response.setMessage("Successfully retrieved list of entities!");
                           break;
                       case EDIT_RESERVATION:
                           Controller.getInstance().editReservation((Reservation) request.getArgument());
                           response.setMessage("Successfully saved changes!");
                           break;
                       case DELETE_RESERVATION:
                           Controller.getInstance().deleteReservation((Reservation) request.getArgument());
                           response.setMessage("The requested object was successfully deleted!");
                           break;
                       case DELETE_BOARD:
                           Controller.getInstance().deleteBoard((Board) request.getArgument());
                           response.setMessage("The requested object was successfully deleted!");
                           break;
                       case DELETE_RESERVATION_ITEM:
                           Controller.getInstance().deleteReservationItem((ReservationItem) request.getArgument());
                           response.setMessage("The requested object was successfully deleted!");
                           break;
                       case ADD_RESERVATION_ITEM:
                           Controller.getInstance().addReservationItems((List <ReservationItem>) request.getArgument());
                           response.setMessage("Successfully added object!");
                           break;
                       case EDIT_ROOM:
                           Controller.getInstance().editRoom((Room) request.getArgument());
                           response.setMessage("Successfully saved changes!");
                           break;
                       case FILTER_BOARDS:
                           List <GenericEntity> filtered_boards = Controller.getInstance().filterBoards(request.getArgument().toString());
                           response.setResult(filtered_boards);
                           response.setMessage("Successfully retrieved list of entities!");
                           break;
                       case FILTER_RESERVATIONS:
                           List <GenericEntity> filtered_res = Controller.getInstance().filterReservations(request.getArgument().toString());
                           response.setResult(filtered_res);
                           response.setMessage("Successfully retrieved list of entities!");
                           break;
                       case FILTER_ROOMS:
                           List <GenericEntity> filtered_rooms = Controller.getInstance().filterRooms(request.getArgument().toString());
                           response.setResult(filtered_rooms);
                           response.setMessage("Successfully retrieved list of entities!");
                           break;
                       case USER_END:
                           User k = (User) request.getArgument();
                           for(GenericEntity i: Controller.getInstance().getActive_users()){
                               if (((User) i).getUsername().equals(k.getUsername())){
                                   Controller.getInstance().getActive_users().remove(i);
                               }
                           }
//                           Controller.getInstance().getActive_users().remove(k);
                           kraj = true;
                   }                   
               }
               catch (Exception e){
                   e.printStackTrace();
                   response.setException(e);
               }
               sender.send(response);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    
}
