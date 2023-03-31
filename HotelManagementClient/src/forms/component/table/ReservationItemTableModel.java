/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.component.table;

import domain.ReservationItem;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class ReservationItemTableModel extends AbstractTableModel{
    private final String [] column_names = {"ID", "Guest ID", "Board ID", "Room Number"};
    private final String [] table_column_names = {"guest_id", "board_id", "id", "room_id"};
    private List <ReservationItem> reservationItems = new ArrayList();

    public List<ReservationItem> getReservationItems() {
        return reservationItems;
    }

    public void setReservationItems(List<ReservationItem> reservationItems) {
        this.reservationItems = reservationItems;
    }

    public String[] getColumn_names() {
        return column_names;
    }

    public String[] getTable_column_names() {
        return table_column_names;
    }

    @Override
    public int getRowCount() {
        if (reservationItems == null){
            return 0;
        }
        return reservationItems.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    public int getTableColumnCount(){
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ReservationItem b = reservationItems.get(rowIndex);
        switch (columnIndex){
            case 1: return b.getGuest().getId();
            case 2: return b.getBoard().getId();
            case 0: return b.getId();
            case 3: return b.getRoom().getId();
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
    
    public void addReservationItem(ReservationItem b){
        reservationItems.add(b);
        fireTableDataChanged();
    }
    
    public ReservationItem getReservationItemAt(int index){
        return reservationItems.get(index);
    }
}
