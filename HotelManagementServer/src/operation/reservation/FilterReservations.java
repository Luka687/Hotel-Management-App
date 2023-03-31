/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.reservation;

import domain.GenericEntity;
import domain.Reservation;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class FilterReservations extends AbstractGenericOperation{
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
        if (param == null || !(param instanceof Reservation)){
            throw new Exception ("Entity is not a reservation!");
        }    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        r = repository.filter((Reservation) param, filter_condition);
    }

    public List<GenericEntity> getR() {
        return r;
    }   
}
