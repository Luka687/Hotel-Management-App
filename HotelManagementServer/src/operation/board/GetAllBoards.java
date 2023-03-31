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
public class GetAllBoards extends AbstractGenericOperation{
    private List<GenericEntity> boards = new ArrayList();

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Board)){
            throw new Exception ("Entity is not a board!");
        }    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        boards = repository.getAll((Board) param);
    }

    public List<GenericEntity> getBoards() {
        return boards;
    }    
}
