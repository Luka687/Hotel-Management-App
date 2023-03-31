/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.board;

import domain.Board;
import domain.Guest;
import operation.AbstractGenericOperation;

/**
 *
 * @author Luka
 */
public class EditBoard extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Board)){
            throw new Exception ("Entity is not a board!");
        }    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((Board) param);
    }
    
}
