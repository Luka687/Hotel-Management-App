/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.implementations.RepositoryDBGeneric;
import domain.Board;
import domain.GenericEntity;
import domain.Guest;
import domain.Reservation;
import domain.ReservationItem;
import domain.Room;
import domain.User;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;
import operation.board.AddBoard;
import operation.board.DeleteBoard;
import operation.board.EditBoard;
import operation.board.FilterBoards;
import operation.board.GetAllBoards;
import operation.board.GetBoard;
import operation.guest.AddGuest;
import operation.guest.DeleteGuest;
import operation.guest.EditGuest;
import operation.guest.FilterGuests;
import operation.guest.GetAllGuests;
import operation.guest.GetGuest;
import operation.reservation.AddReservation;
import operation.reservation.DeleteReservation;
import operation.reservation.EditReservation;
import operation.reservation.FilterReservations;
import operation.reservation.GetAllReservations;
import operation.reservation.GetReservation;
import operation.reservation_item.AddReservationItem;
import operation.reservation_item.DeleteReservationItem;
import operation.reservation_item.EditReservationItem;
import operation.reservation_item.GetAllReservationItems;
import operation.reservation_item.GetReservationItem;
import operation.room.EditRoom;
import operation.room.FilterRooms;
import operation.room.GetAllRooms;
import operation.room.GetRoom;
import operation.user.LogInUser;
import repository.Repository;
import server.Server;

/**
 *
 * @author Luka
 */
public class Controller {
    
    private static Controller instance;
    private final RepositoryDBGeneric repository;
    private List <GenericEntity> active_users;
    private Server server;

    private Controller() {
        this.repository = new RepositoryDBGeneric();
        active_users = new ArrayList();
    }
    
    public static Controller getInstance(){
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }

    public List<GenericEntity> getActive_users() {
        return active_users;
    }

    public void setActive_users(List<GenericEntity> active_users) {
        this.active_users = active_users;
    }
    
    public void startServer(){
        if (server == null || !server.isAlive()){
            server = Server.getInstance();
            server.start();
        }
    }
    
    public void stopServer(){
        server.shutdown();
    }
    
    public GenericEntity login(String username, String password) throws Exception{
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        AbstractGenericOperation operation = new LogInUser();
        operation.execute(user);
        return ((LogInUser) operation).getG();       
    } 
    
    // "ADD" FUNCTIONS
    
    public void addGuest(Guest g) throws Exception{
        AbstractGenericOperation operation = new AddGuest();
        operation.execute(g);
    }
    
    public void addReservationItems(List <ReservationItem> list) throws Exception{
        AbstractGenericOperation o = new AddReservationItem();
        o.execute(list);
    }
    
    public void addReservation(Reservation g) throws Exception{
        AbstractGenericOperation operation = new AddReservation();
        operation.execute(g);
        addReservationItems(g.getReservation_items());
    }
    
    public void addBoard(Board g) throws Exception{
        AbstractGenericOperation operation = new AddBoard();
        operation.execute(g);      
    }
    
    // "GET" FUNCTIONS
    
    public GenericEntity getGuest(long id) throws Exception{
        Guest g = new Guest();
        g.setId(id);
        AbstractGenericOperation operation = new GetGuest();
        operation.execute(g);
        return ((GetGuest) operation).getGenericEntity();
    }
    
    public GenericEntity getReservation(Guest g, Board b) throws Exception{
        Reservation r = new Reservation();
        r.setGuest(g);
        r.setBoard(b);
        AbstractGenericOperation operation = new GetReservation();
        operation.execute(r);
        return ((GetReservation) operation).getR();
    }
    
    public GenericEntity getReservationItem(Guest g, Board b, long id) throws Exception{
        ReservationItem r = new ReservationItem();
        r.setGuest(g);
        r.setBoard(b);
        r.setId(id);
        AbstractGenericOperation operation = new GetReservationItem();
        operation.execute(r);
        return ((GetReservation) operation).getR();
    }
    
    public GenericEntity getBoard(long id) throws Exception{
        Board g = new Board();
        g.setId(id);
        AbstractGenericOperation operation = new GetBoard();
        operation.execute(g);
        return ((GetBoard) operation).getBoard();
    }
    
    public GenericEntity getRoom(long id) throws Exception{
        Room g = new Room();
        g.setId(id);
        AbstractGenericOperation operation = new GetRoom();
        operation.execute(g);
        return ((GetRoom) operation).getR();
    }
    
    // "GET ALL" FUNCTIONS
    
    public List <GenericEntity> getAllRooms() throws Exception{
        Room r = new Room();
        AbstractGenericOperation o = new GetAllRooms();
        o.execute(r);
        return ((GetAllRooms) o).getList();
    }

    public List <GenericEntity> getAllBoards() throws Exception{
        Board r = new Board();
        AbstractGenericOperation o = new GetAllBoards();
        o.execute(r);
        return ((GetAllBoards) o).getBoards();
    }     
    
    public List <GenericEntity> getAllReservations() throws Exception{
        Reservation r = new Reservation();
        AbstractGenericOperation o = new GetAllReservations();
        o.execute(r);
        return ((GetAllReservations) o).getR();
    }

    public List <GenericEntity> getAllGuests() throws Exception{
        Guest r = new Guest();
        AbstractGenericOperation o = new GetAllGuests();
        o.execute(r);
        return ((GetAllGuests) o).getGuests();
    } 
    
    public List <GenericEntity> getAllReservationItems() throws Exception{
        ReservationItem r = new ReservationItem();
        AbstractGenericOperation o = new GetAllReservationItems();
        o.execute(r);
        return ((GetAllReservationItems) o).getR();
    }
    
    // "EDIT" FUNCTIONS
    
    public void editGuest(Guest g) throws Exception{
        AbstractGenericOperation o = new EditGuest();
        o.execute(g);
    }
    
    public void editBoard(Board g) throws Exception{
        AbstractGenericOperation o = new EditBoard();
        o.execute(g);
    }   
    
    public void editReservation(Reservation g) throws Exception{
        AbstractGenericOperation o = new EditReservation();
        o.execute(g);
    }
    
    public void editReservationItem(ReservationItem g) throws Exception{
        AbstractGenericOperation o = new EditReservationItem();
        o.execute(g);
    }
    
    public void editRoom(Room room) throws Exception {
        AbstractGenericOperation o = new EditRoom();
        o.execute(room);
    }
    
    
    // "DELETE" FUNCTIONS
    
    public void deleteGuest(Guest g) throws Exception{
        AbstractGenericOperation o = new DeleteGuest();
        o.execute(g);
    }
    
    public void deleteBoard(Board g) throws Exception{
        AbstractGenericOperation o = new DeleteBoard();
        o.execute(g);
    }
    
    public void deleteReservation(Reservation g) throws Exception{
        AbstractGenericOperation o = new DeleteReservation();
        o.execute(g);
    }
    
    public void deleteReservationItem(ReservationItem g) throws Exception {
        AbstractGenericOperation o = new DeleteReservationItem();
        o.execute(g);
    }
    
    // "FILTER" FUNCTIONS
    
    public List <GenericEntity> filterGuests(String f) throws Exception{
        Guest r = new Guest();
        AbstractGenericOperation o = new FilterGuests();
        ((FilterGuests) o).setFilter_conditon(f);
        o.execute(r);
        return ((FilterGuests) o).getGuests();
    } 
    
    public List <GenericEntity> filterBoards(String f) throws Exception{
        Board r = new Board();
        AbstractGenericOperation o = new FilterBoards();
        ((FilterBoards) o).setFilter_condition(f);
        o.execute(r);
        return ((FilterBoards) o).getBoards();
    }
    
    public List <GenericEntity> filterReservations(String f) throws Exception{
        Reservation r = new Reservation();
        AbstractGenericOperation o = new FilterReservations();
        ((FilterReservations) o).setFilter_condition(f);
        o.execute(r);
        return ((FilterReservations) o).getR();
    }   

    public List<GenericEntity> filterRooms(String f) throws Exception {
        Room r = new Room();
        AbstractGenericOperation o = new FilterRooms();
        ((FilterReservations) o).setFilter_condition(f);
        o.execute(r);
        return ((FilterReservations) o).getR();
    }
}
