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
public class Guest implements GenericEntity{
    private long id;
    private String firstname;
    private String lastname;
    private String phone_number;
    private String email;
    private int age;

    public Guest() {
    }

    public Guest(long id, String firstname, String lastname, String phone_number, String email, int age) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone_number = phone_number;
        this.email = email;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.firstname);
        hash = 97 * hash + Objects.hashCode(this.lastname);
        hash = 97 * hash + Objects.hashCode(this.phone_number);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + this.age;
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
        final Guest other = (Guest) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.phone_number, other.phone_number)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }
    
    @Override
    public String getTableName() {
        return "guest";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "firstname,lastname,phone_number,email,age";
    }

    @Override
    public void prepareStatement(PreparedStatement statement, GenericEntity param) throws Exception {
        Guest g = (Guest) param;
        statement.setString(1, g.getFirstname());
        statement.setString(2, g.getLastname());
        statement.setString(3, g.getPhone_number());
        statement.setString(4, g.getEmail());
        statement.setInt(5, g.getAge());
    }

    @Override
    public String getTableId() {
        return "" + getId();
    }

    @Override
    public String getOrderCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<GenericEntity> getList(ResultSet resultSet) throws Exception{
        List <GenericEntity> list = new ArrayList();
        while (resultSet.next()){
            Guest g = new Guest();
            g.setFirstname(resultSet.getString("firstname"));
            g.setLastname(resultSet.getString("lastname"));
            g.setPhone_number(resultSet.getString("phone_number"));
            g.setEmail(resultSet.getString("email"));
            g.setAge(resultSet.getInt("age"));
            g.setId(resultSet.getLong("id"));
            
            list.add(g);
        }
        return list;
    }

    @Override
    public GenericEntity get(ResultSet resultSet) throws Exception {
        if(resultSet.next()){
            Guest g = new Guest();
            g.setFirstname(resultSet.getString("firstname"));
            g.setLastname(resultSet.getString("lastname"));
            g.setPhone_number(resultSet.getString("phone_number"));
            g.setEmail(resultSet.getString("email"));
            g.setAge(resultSet.getInt("age"));
            g.setId(resultSet.getLong("id"));
            return (GenericEntity) g;
        }
        else{
            return null;
        }
    }

    @Override
    public String toString() {
        return id + ": "+ firstname + " " + lastname;
    }
    
    @Override
    public String getIdType() {
        return "id";
    }
    
}
