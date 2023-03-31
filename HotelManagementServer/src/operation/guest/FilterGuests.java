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
public class FilterGuests extends AbstractGenericOperation{
    private String filter_conditon;
    private List <GenericEntity> guests;

    public String getFilter_conditon() {
        return filter_conditon;
    }

    public void setFilter_conditon(String filter_conditon) {
        this.filter_conditon = filter_conditon;
    }

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Guest)){
            throw new Exception ("Entity is not a guest!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        guests = repository.filter((Guest) param, filter_conditon);
    }

    public List<GenericEntity> getGuests() {
        return guests;
    }
       
}
