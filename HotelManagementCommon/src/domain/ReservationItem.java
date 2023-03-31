/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Luka
 */
public class ReservationItem implements GenericEntity{
    private Board board;
    private Guest guest;
    private long id;
    private Room room;

    public ReservationItem() {
    }

    public ReservationItem(Board board, Guest guest, long id, Room room) {
        this.board = board;
        this.guest = guest;
        this.id = id;
        this.room = room;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.board);
        hash = 23 * hash + Objects.hashCode(this.guest);
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 23 * hash + Objects.hashCode(this.room);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservationItem other = (ReservationItem) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.board, other.board)) {
            return false;
        }
        if (!Objects.equals(this.guest, other.guest)) {
            return false;
        }
        return Objects.equals(this.room, other.room);
    }
    
    

    @Override
    public String getTableName() {
        return "reservation_item";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "guest_id,board_id,room_id";
    }

    @Override
    public void prepareStatement(PreparedStatement statement, GenericEntity param) throws Exception {
        statement.setLong(1, ((ReservationItem) param).getGuest().getId());
        statement.setLong(2, ((ReservationItem) param).getBoard().getId());
        statement.setLong(3, ((ReservationItem) param).getRoom().getId());
    }

    @Override
    public String getTableId() {
        return "("+getGuest().getId()+","+getBoard().getId()+","+getId()+")";
    }

    @Override
    public String getOrderCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<GenericEntity> getList(ResultSet resultSet) throws Exception {
        List <GenericEntity> reservationItems = new ArrayList();
        while(resultSet.next()){
            ReservationItem r = new ReservationItem();
            Board b = new Board();
            Guest g = new Guest();
            Room room = new Room();
            
            g.setId(resultSet.getLong("guest_id"));
            b.setId(resultSet.getLong("board_id"));
            room.setId(resultSet.getLong("room_id"));
            
            r.setBoard(b);
            r.setGuest(g);
            r.setId(resultSet.getLong("id"));
            r.setRoom(room);
            reservationItems.add(r);
        }
        return reservationItems;
    }

    @Override
    public GenericEntity get(ResultSet resultSet) throws Exception {
        if(resultSet.next()){
            ReservationItem r = new ReservationItem();
            Board b = new Board();
            Guest g = new Guest();
            Room room = new Room();
            
            g.setId(resultSet.getLong("guest_id"));
            b.setId(resultSet.getLong("board_id"));
            room.setId(resultSet.getLong("room_id"));
            
            r.setBoard(b);
            r.setGuest(g);
            r.setId(resultSet.getLong("id"));
            r.setRoom(room);
            return r;
        }      
        else{
            return null;
        }
    }
    
    @Override
    public String getIdType() {
        return "(guest_id,board_id,id)";
    }
    
}
