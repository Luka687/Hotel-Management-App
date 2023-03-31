/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.reservation_item;

import domain.ReservationItem;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class EditReservationItem extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof ReservationItem)){
            throw new Exception ("Entity is not a reservation!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((ReservationItem) param);
    }
    
}
