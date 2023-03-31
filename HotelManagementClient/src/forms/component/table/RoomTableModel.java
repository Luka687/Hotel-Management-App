/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.component.table;

import domain.Room;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class RoomTableModel extends AbstractTableModel{
    private final String [] column_names = {"ID", "Floor", "Occupied?"};
    private final String [] table_column_names = {"ID", "floor", "occupied"};
    private List <Room> rooms;
    
    @Override
    public int getRowCount() {
        if (rooms == null){
            return 0;
        }
        return rooms.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Room g = rooms.get(rowIndex);
        switch(columnIndex){
            case 0: return g.getId();
            case 1: return g.getFloor();
            case 2: return g.isOccupied();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        if (column > column_names.length){
            return "n/a";
        }
        return column_names[column];
    }
    
    public void addRoom(Room g){
        rooms.add(g);
        fireTableDataChanged();
    }
    
    public Room getRoomAt(int index){
        return this.rooms.get(index);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
        fireTableDataChanged();
    }   

    public String[] getColumn_names() {
        return column_names;
    }    

    public String[] getTable_column_names() {
        return table_column_names;
    }      
}
