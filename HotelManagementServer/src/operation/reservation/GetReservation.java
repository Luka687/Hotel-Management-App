/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.reservation;

import domain.GenericEntity;
import domain.Reservation;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class GetReservation extends AbstractGenericOperation{
    private GenericEntity r;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Reservation)){
            throw new Exception ("Entity is not a reservation!");
        }    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        r = repository.getById((Reservation) param);
    }

    public GenericEntity getR() {
        return r;
    }   
    
}
