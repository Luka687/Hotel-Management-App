/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.reservation;

import domain.GenericEntity;
import domain.Guest;
import domain.Reservation;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class GetAllReservations extends AbstractGenericOperation{
    private List <GenericEntity> r = new ArrayList();

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Reservation)){
            throw new Exception ("Entity is not a reservation!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        r = repository.getAll((Reservation) param);
    }

    public List<GenericEntity> getR() {
        return r;
    }    
}
