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
public class User implements GenericEntity{
    private long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    public User() {
    }

    public User(long id, String username, String password, String firstname, String lastname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + Objects.hashCode(this.password);
        hash = 59 * hash + Objects.hashCode(this.firstname);
        hash = 59 * hash + Objects.hashCode(this.lastname);
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        return Objects.equals(this.lastname, other.lastname);
    }    

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "username,password,firstname,lastname";
    }

    @Override
    public void prepareStatement(PreparedStatement statement, GenericEntity param) throws Exception {
        statement.setString(1, ((User) param).getUsername());
        statement.setString(2, ((User) param).getPassword());
        statement.setString(3, ((User) param).getFirstname());
        statement.setString(4, ((User) param).getLastname());
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
        List<GenericEntity> users = new ArrayList();
        while(resultSet.next()){
            User u = new User();
            u.setId(resultSet.getLong("id"));
            u.setUsername(resultSet.getString("username"));
            u.setPassword(resultSet.getString("password"));
            u.setFirstname(resultSet.getString("firstname"));
            u.setLastname(resultSet.getString("lastname"));
            users.add(u);
        }
        return users;
    }

    @Override
    public GenericEntity get(ResultSet resultSet) throws Exception {
        if(resultSet.next()){
            User u = new User();
            u.setId(resultSet.getLong("id"));
            u.setUsername(resultSet.getString("username"));
            u.setPassword(resultSet.getString("password"));
            u.setFirstname(resultSet.getString("firstname"));
            u.setLastname(resultSet.getString("lastname"));
            return u;
        }
        else{
            return null;
        }
    }
    
    @Override
    public String getIdType() {
        return "id";
    }
    
}

