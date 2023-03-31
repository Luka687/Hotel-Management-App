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
public class Reservation implements GenericEntity{
    private Guest guest;
    private Board board;
    private Date check_in_date;
    private Date check_out_date;
    private List <ReservationItem> reservation_items = new ArrayList();

    public Reservation() {
    }

    public Reservation(Guest guest, Board board, Date check_in_date, Date check_out_date, List<ReservationItem> reservation_items) {
        this.guest = guest;
        this.board = board;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.reservation_items = reservation_items;
    }   

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Date getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(Date check_in_date) {
        this.check_in_date = check_in_date;
    }

    public Date getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(Date check_out_date) {
        this.check_out_date = check_out_date;
    }

    public List<ReservationItem> getReservation_items() {
        return reservation_items;
    }

    public void setReservation_items(List<ReservationItem> reservation_items) {
        this.reservation_items = reservation_items;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.guest);
        hash = 71 * hash + Objects.hashCode(this.board);
        hash = 71 * hash + Objects.hashCode(this.check_in_date);
        hash = 71 * hash + Objects.hashCode(this.check_out_date);
        hash = 71 * hash + Objects.hashCode(this.reservation_items);
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
        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.guest, other.guest)) {
            return false;
        }
        if (!Objects.equals(this.board, other.board)) {
            return false;
        }
        if (!Objects.equals(this.check_in_date, other.check_in_date)) {
            return false;
        }
        if (!Objects.equals(this.check_out_date, other.check_out_date)) {
            return false;
        }
        return Objects.equals(this.reservation_items, other.reservation_items);
    }    
    
    @Override
    public String getTableName() {
        return "reservation";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "guest_id,board_id,check_in_date,check_out_date";
    }

    @Override
    public void prepareStatement(PreparedStatement statement, GenericEntity param) throws Exception {
        statement.setLong(1, ((Reservation) param).getGuest().getId());
        statement.setLong(2, ((Reservation) param).getBoard().getId());
        statement.setDate(3, new java.sql.Date(((Reservation) param).getCheck_in_date().getTime()));
        statement.setDate(4, new java.sql.Date(((Reservation) param).getCheck_out_date().getTime()));
    }

    @Override
    public String getTableId() {
        return "("+getGuest().getId()+","+getBoard().getId()+")";
    }

    @Override
    public String getOrderCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<GenericEntity> getList(ResultSet resultSet) throws Exception {
        List <GenericEntity> reservations = new ArrayList();
        while(resultSet.next()){
            Reservation r = new Reservation();
            Board b = new Board();
            Guest g = new Guest();
            
            g.setId(resultSet.getLong("guest_id"));
            b.setId(resultSet.getLong("board_id"));
            
            r.setBoard(b);
            r.setGuest(g);
            r.setCheck_in_date(new Date(resultSet.getDate("check_in_date").getTime()));
            r.setCheck_out_date(new Date(resultSet.getDate("check_out_date").getTime()));
            r.setReservation_items(new ArrayList());
           reservations.add(r);
        }
        return reservations;
    }

    @Override
    public GenericEntity get(ResultSet resultSet) throws Exception {
        if(resultSet.next()){
            Reservation r = new Reservation();
            Board b = new Board();
            Guest g = new Guest();
            
            g.setId(resultSet.getInt("guest_id"));
            b.setId(resultSet.getInt("board_id"));
            
            r.setBoard(b);
            r.setGuest(g);
            r.setCheck_in_date(new Date(resultSet.getDate("check_in_date").getTime()));
            r.setCheck_out_date(new Date(resultSet.getDate("check_out_date").getTime()));
            r.setReservation_items(new ArrayList());
            return r;
        }
        else{
            return null;
        }
    }  
    
    @Override
    public String getIdType() {
        return "(guest_id, board_id)";
    }
}
