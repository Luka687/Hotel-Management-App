/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.component.table;

import domain.Reservation;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class ReservationTableModel extends AbstractTableModel{
    private final String [] column_names = {"Guest ID", "Board ID", "Check In Date", "Check Out Date"};
    private final String [] table_column_names = {"guest_id", "board_id", "check_in_date", "check_out_date"};
    private List <Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String[] getColumn_names() {
        return column_names;
    }

    public String[] getTable_column_names() {
        return table_column_names;
    }

    @Override
    public int getRowCount() {
        if (reservations == null){
            return 0;
        }
        return reservations.size();
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
        Reservation b = reservations.get(rowIndex);
        switch (columnIndex){
            case 0: return b.getGuest().getId();
            case 1: return b.getBoard().getId();
            case 2: return b.getCheck_in_date();
            case 3: return b.getCheck_out_date();
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
    
    public void addReservation(Reservation b){
        reservations.add(b);
        fireTableDataChanged();
    }
    
    public Reservation getReservationAt(int index){
        return reservations.get(index);
    }
}
