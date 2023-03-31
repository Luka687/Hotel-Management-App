/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.reservation_item;

import domain.GenericEntity;
import domain.ReservationItem;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class FilterReservationItems extends AbstractGenericOperation{
    private String filter_condition;
    private List<GenericEntity> r = new ArrayList();

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof ReservationItem)){
            throw new Exception ("Entity is not a reservation!");
        }    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        r = repository.filter((ReservationItem) param, filter_condition);
    }
    
}
