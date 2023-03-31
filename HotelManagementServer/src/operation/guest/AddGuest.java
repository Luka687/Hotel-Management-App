/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.guest;

import domain.Guest;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class AddGuest extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Guest)){
            throw new Exception ("Entity is not a guest!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Guest) param);
    }
    
}
