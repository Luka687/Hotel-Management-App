/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.component.table;

import domain.Board;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class BoardTableModel extends AbstractTableModel{
    private final String [] column_names = {"ID", "Name", "Total Price"};
    private final String [] table_column_names = {"id", "name", "total_price"};
    private List <Board> boards;

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public String[] getColumn_names() {
        return column_names;
    }

    public String[] getTable_column_names() {
        return table_column_names;
    }

    @Override
    public int getRowCount() {
        if (boards == null){
            return 0;
        }
        return boards.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Board b = boards.get(rowIndex);
        switch (columnIndex){
            case 0: return b.getId();
            case 1: return b.getName();
            case 2: return b.getTotal_price();
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
    
    public void addBoard(Board b){
        boards.add(b);
        fireTableDataChanged();
    }
    
    public Board getBoardAt(int index){
        return boards.get(index);
    }
    
}
