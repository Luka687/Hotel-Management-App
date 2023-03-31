/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Luka
 */
public interface GenericEntity extends Serializable{
    
    String getTableName();

    String getColumnNamesForInsert();

    void prepareStatement(PreparedStatement statement, GenericEntity param) throws Exception;
    
    String getTableId();

    default String getInsertValues(){
        StringBuilder sb = new StringBuilder();
        String [] columns = getColumnNamesForInsert().split(",");
        for (int i = 0; i < columns.length; i++){
            sb.append("?");
            if (i != columns.length - 1){
                sb.append(",");
            }
        }
        return sb.toString();
    };
    
    default String getUpdateValues(){
        StringBuilder sb = new StringBuilder();
        String [] columns = getColumnNamesForInsert().split(","); 
        for (int i = 0; i < columns.length; i++){
            sb.append(columns[i]);
            sb.append("=?");
            if (i != columns.length - 1){
                sb.append(",");
            }
        }
        return sb.toString();
    };

    public String getOrderCondition();

    public List<GenericEntity> getList(ResultSet resultSet) throws Exception;
    
    public GenericEntity get(ResultSet resultSet) throws Exception;

    public String getIdType();
    
}
