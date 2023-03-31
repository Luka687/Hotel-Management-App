/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Luka
 */
public class Board implements GenericEntity{
    private long id;
    private String name;
    private double total_price;

    public Board() {
    }

    public Board(long id, String name, double total_price) {
        this.id = id;
        this.name = name;
        this.total_price = total_price;
    }   

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.total_price) ^ (Double.doubleToLongBits(this.total_price) >>> 32));
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
        final Board other = (Board) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.total_price) != Double.doubleToLongBits(other.total_price)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String getTableName() {
        return "board";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "name,total_price";
    }

    @Override
    public void prepareStatement(PreparedStatement statement, GenericEntity param) throws Exception {
        Board b = (Board) param;
        statement.setString(1, b.getName());
        statement.setDouble(2, b.getTotal_price());
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
        List <GenericEntity> list = new ArrayList();        
        while(resultSet.next()){
            Board b = new Board();
            b.setId(resultSet.getLong("id"));
            b.setName(resultSet.getString("name"));
            b.setTotal_price(resultSet.getDouble("total_price"));
            list.add(b);
        }
        return list;
    }

    @Override
    public GenericEntity get(ResultSet resultSet) throws Exception {          
        if(resultSet.next()){  
            Board b = new Board();
            b.setId(resultSet.getLong("id"));
            b.setName(resultSet.getString("name"));
            b.setTotal_price(resultSet.getDouble("total_price"));
            return b;
        }
        else{
            return null;
        }        
    } 

    @Override
    public String toString() {
        return name;
    }        

    @Override
    public String getIdType() {
        return "id";
    }
}
