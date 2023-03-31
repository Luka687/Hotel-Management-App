/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.room;

import domain.GenericEntity;
import domain.Room;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class FilterRooms extends AbstractGenericOperation{
    private String filter_condition;
    private List<GenericEntity> r = new ArrayList();

    public String getFilter_condition() {
        return filter_condition;
    }

    public void setFilter_condition(String filter_condition) {
        this.filter_condition = filter_condition;
    }   

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Room)){
            throw new Exception ("Entity is not a reservation!");
        }    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        r = repository.filter((Room) param, filter_condition);
    }

    public List<GenericEntity> getR() {
        return r;
    }    
}
