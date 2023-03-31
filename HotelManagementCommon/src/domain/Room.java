/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luka
 */
public class Room implements GenericEntity{
    private long id;
    private int floor;
    private boolean occupied;

    public Room() {
    }

    public Room(long id, int floor, boolean occupied) {
        this.id = id;
        this.floor = floor;
        this.occupied = occupied;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + this.floor;
        hash = 29 * hash + (this.occupied ? 1 : 0);
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
        final Room other = (Room) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.floor != other.floor) {
            return false;
        }
        return this.occupied == other.occupied;
    }    

    @Override
    public String getTableName() {
        return "room";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "occupied";
    }

    @Override
    public void prepareStatement(PreparedStatement statement, GenericEntity param) throws Exception {
        statement.setBoolean(1, ((Room) param).isOccupied());
    }

    @Override
    public String getTableId() {
        return ""+getId();
    }

    @Override
    public String getOrderCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<GenericEntity> getList(ResultSet resultSet) throws Exception {
        List <GenericEntity> rooms = new ArrayList();
        while(resultSet.next()){
            Room r = new Room();
            r.setId(resultSet.getLong("id"));
            r.setFloor(resultSet.getInt("floor"));
            r.setOccupied(resultSet.getBoolean("occupied"));
            rooms.add(r);          
        }
        return rooms;
    }

    @Override
    public GenericEntity get(ResultSet resultSet) throws Exception {
        if(resultSet.next()){
            Room r = new Room();
            r.setId(resultSet.getLong("id"));
            r.setFloor(resultSet.getInt("floor"));
            r.setOccupied(resultSet.getBoolean("occupied"));      
            return r;
        }
        else{
            return null;
        }
    }   

    @Override
    public String toString() {
        return id + "";
    }
    
    @Override
    public String getIdType() {
        return "id";
    }
    
}
