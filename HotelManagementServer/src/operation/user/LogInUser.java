/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.user;

import domain.GenericEntity;
import domain.User;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class LogInUser extends AbstractGenericOperation{
    private GenericEntity g;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof User)){
            throw new Exception ("Entity is not a user!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        g = repository.logIn((User) param, ((User) param).getUsername(), ((User) param).getPassword());
    }

    public GenericEntity getG() {
        return g;
    }      
}
