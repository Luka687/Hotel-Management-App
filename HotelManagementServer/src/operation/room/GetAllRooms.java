/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.room;

import domain.GenericEntity;
import domain.Room;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class GetAllRooms extends AbstractGenericOperation{
    private List <GenericEntity> list;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Room)){
            throw new Exception ("Entity is not a room!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll((Room) param);
    }

    public List<GenericEntity> getList() {
        return list;
    }     
}
