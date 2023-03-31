/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.board;

import domain.Board;
import domain.GenericEntity;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class GetBoard extends AbstractGenericOperation{
    private GenericEntity board;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Board)){
            throw new Exception ("Entity is not a board!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        board = repository.getById((Board) param);
    }

    public GenericEntity getBoard() {
        return board;
    }
        
}
