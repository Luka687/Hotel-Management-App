/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation;

import database.DBRepository;
import database.implementations.RepositoryDBGeneric;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luka
 */
public abstract class AbstractGenericOperation {
       protected final RepositoryDBGeneric repository;

    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }
    
    public final void execute(Object param) throws Exception{
           try {
               preconditions(param);
               startTransaction();
               executeOperation(param);
               commitTransaction();
           } catch (Exception ex) {
               ex.printStackTrace();
               rollbackTransaction();
           } finally{
               endTransaction();
           }
    }
    
    protected abstract void preconditions(Object param) throws Exception;
    
    protected abstract void executeOperation(Object param) throws Exception;
    
    private void startTransaction() throws Exception{
        ((DBRepository) repository).connect();
    }
    
    private void commitTransaction() throws Exception{
        ((DBRepository) repository).commit();
    }
    
    private void endTransaction() throws Exception{
        ((DBRepository) repository).disconnect();
    }
    
    private void rollbackTransaction() throws Exception{
        ((DBRepository) repository).rollback();
    }
       
       
}
