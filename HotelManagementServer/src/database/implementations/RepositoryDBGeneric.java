/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.implementations;

import database.DBConnectionFactory;
import database.DBRepository;
import domain.GenericEntity;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Luka
 */
public class RepositoryDBGeneric implements DBRepository<GenericEntity>{

    @Override
    public List<GenericEntity> getAll(GenericEntity param) throws Exception {
        List <GenericEntity> list = new ArrayList();
        try {
            String query = "SELECT * FROM " + param.getTableName();// + " ORDER BY " + param.getOrderCondition();
            PreparedStatement statement = DBConnectionFactory.getInstance().getConnection().prepareStatement(query); 
            
            ResultSet resultSet = statement.executeQuery(query);
            list = param.getList(resultSet);
            
            resultSet.close();
            statement.close();
            return list;
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new Exception("Entities could not be loaded: \n" + exception.getMessage());
        }
    }

    @Override
    public void add(GenericEntity param) throws Exception {
        try {
            String query = "INSERT INTO " + param.getTableName() + " (" + param.getColumnNamesForInsert() + ") VALUES(" + param.getInsertValues() + ")";
            PreparedStatement statement = DBConnectionFactory.getInstance().getConnection().prepareStatement(query);
            param.prepareStatement(statement, param);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new Exception("Entity could not be saved: \n" + exception.getMessage());
        }
    }

    @Override
    public void edit(GenericEntity param) throws Exception {
        try{
            String query = "UPDATE " + param.getTableName() +" SET " + param.getUpdateValues() + " WHERE " + param.getIdType() +"=" + param.getTableId();
            System.out.println(query);
            PreparedStatement statement = DBConnectionFactory.getInstance().getConnection().prepareStatement(query);
            param.prepareStatement(statement, param);
            statement.executeUpdate();
            statement.close();
            System.out.println("EDITED GUEST");
        } catch (SQLException e){
            e.printStackTrace();
            throw new Exception("Entity could not be edited: \n" + e.getMessage());
        }
    }

    @Override
    public void delete(GenericEntity param) throws Exception {
        try{
            String query = "DELETE FROM " + param.getTableName() + " WHERE " + param.getIdType() +"=" + param.getTableId();
            System.out.println(query);
            PreparedStatement statement = DBConnectionFactory.getInstance().getConnection().prepareStatement(query);
            statement.executeUpdate();
            statement.close();
        } catch(SQLException e){
            e.printStackTrace();
            throw new Exception("Entity could not be deleted: \n" + e.getMessage());
        }
    }    

    @Override
    public GenericEntity getById(GenericEntity param) throws Exception {
        try {
            String query = "SELECT * FROM " + param.getTableName() + " WHERE " + param.getIdType() +"=" + param.getTableId(); //+ " ORDER BY " + param.getOrderCondition();
            PreparedStatement statement = DBConnectionFactory.getInstance().getConnection().prepareStatement(query); 
            
            ResultSet resultSet = statement.executeQuery(query);
            GenericEntity entity = param.get(resultSet);
            
            resultSet.close();
            statement.close();
            return entity;
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new Exception("Entity could not be found: \n" + exception.getMessage());
        }        
    }

    @Override
    public List<GenericEntity> filter(GenericEntity param, String condition) throws Exception {
        List<GenericEntity> list = null;
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + param.getTableName() + " WHERE " + condition;// + " ORDER BY " + param.getOrderCondition();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = param.getList(resultSet);
            resultSet.close();
            statement.close();
            return list;
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new Exception("Entities could not be found: \n" + exception.getMessage());
        }
    }

    @Override
    public GenericEntity logIn(GenericEntity param, String username, String password) throws Exception {
        GenericEntity g = null;
        try{
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + param.getTableName() + " WHERE username='" + username + "' AND password='" + password + "'";
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            g = param.get(resultSet);
            resultSet.close();
            statement.close();      
            System.out.println("QUERY EXECUTED!");
            return g;
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new Exception("User could not be logged in: \n" + e.getMessage());
        }
    }
}
