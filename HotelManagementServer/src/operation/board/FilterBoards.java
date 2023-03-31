/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.board;

import domain.Board;
import domain.GenericEntity;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class FilterBoards extends AbstractGenericOperation{
    private String filter_condition;
    private List <GenericEntity> boards = new ArrayList();

    public String getFilter_condition() {
        return filter_condition;
    }

    public void setFilter_condition(String filter_condition) {
        this.filter_condition = filter_condition;
    }

    public List<GenericEntity> getBoards() {
        return boards;
    }   
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Board)){
            throw new Exception ("Entity is not a board!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        boards = repository.filter((Board) param, filter_condition);
    }
    
}
