/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.room;


import domain.Room;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class EditRoom extends AbstractGenericOperation{
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Room)){
            throw new Exception ("Entity is not a reservation!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((Room) param);
    }    
}
