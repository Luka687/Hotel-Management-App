/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.component.table;

import domain.Guest;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class GuestTableModel extends AbstractTableModel{
    private final String [] column_names = {"ID", "First Name", "Last Name", "Phone Number", "E-Mail", "Age"};
    private final String [] table_column_names = {"ID", "firstname", "lastname", "phone_number", "email", "Age"};
    private List <Guest> guests;
    
    @Override
    public int getRowCount() {
        if (guests == null){
            return 0;
        }
        return guests.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Guest g = guests.get(rowIndex);
        switch(columnIndex){
            case 0: return g.getId();
            case 1: return g.getFirstname();
            case 2: return g.getLastname();
            case 3: return g.getPhone_number();
            case 4: return g.getEmail();
            case 5: return g.getAge();
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
    
    public void addGuest(Guest g){
        guests.add(g);
        fireTableDataChanged();
    }
    
    public Guest getGuestAt(int index){
        return this.guests.get(index);
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
        fireTableDataChanged();
    }   

    public String[] getColumn_names() {
        return column_names;
    }    

    public String[] getTable_column_names() {
        return table_column_names;
    }   
}
