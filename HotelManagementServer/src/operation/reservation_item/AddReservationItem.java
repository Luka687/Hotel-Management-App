/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.reservation_item;

import domain.ReservationItem;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class AddReservationItem extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        List <ReservationItem> list = (List<ReservationItem>) param;
        for (ReservationItem r: list){
            if (r == null || !(r instanceof ReservationItem)){
                throw new Exception ("Entity is not a reservation!");
            } 
        }
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List <ReservationItem> list = (List<ReservationItem>) param;
        for (ReservationItem r: list){           
            repository.add((ReservationItem) r);
        }    
    }   
}
