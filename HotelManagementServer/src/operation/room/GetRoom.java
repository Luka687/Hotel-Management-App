/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.room;

import domain.GenericEntity;
import domain.Room;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class GetRoom extends AbstractGenericOperation{
    private GenericEntity r;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Room)){
            throw new Exception ("Entity is not a room!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        r = repository.getById(((Room) param));
    }

    public GenericEntity getR() {
        return r;
    }   
    
}
