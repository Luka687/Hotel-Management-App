/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.guest;

import domain.GenericEntity;
import domain.Guest;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class GetAllGuests extends AbstractGenericOperation{
    private List <GenericEntity> guests;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Guest)){
            throw new Exception ("Entity is not a guest!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        guests = repository.getAll((Guest) param);
    }

    public List<GenericEntity> getGuests() {
        return guests;
    }           
}
