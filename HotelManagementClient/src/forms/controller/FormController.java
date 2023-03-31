/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.controller;

import communication.Controller;
import communication.Operation;
import communication.Response;
import domain.Board;
import domain.GenericEntity;
import domain.Guest;
import domain.Reservation;
import domain.ReservationItem;
import domain.Room;
import domain.User;
import forms.FrmBoard;
import forms.FrmGuest;
import forms.FrmReservation;
import forms.FrmRoom;
import forms.FrmTable;
import forms.component.table.BoardTableModel;
import forms.component.table.GuestTableModel;
import forms.component.table.ReservationTableModel;
import forms.component.table.RoomTableModel;
import forms.modes.FormMode;
import java.awt.Component;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class FormController {
    private static FormController instance;
    
    public static FormController getInstance() throws IOException {
        if (instance == null){
            instance = new FormController();
        }
        return instance;
    }   
    
    public void setGuestTableModel(JTable jtbl, Operation o, Object entity) throws IOException, Exception{
        GuestTableModel gtm = new GuestTableModel();
        Response r = Controller.getInstance().GenericOperation(entity, o);
        List <GenericEntity> list = (List <GenericEntity>) r.getResult();
        jtbl.setModel(gtm);
        List <Guest> guests = new ArrayList();
        if(list!=null){
            for (GenericEntity g: list){
                guests.add((Guest) g);
            }
        }       
        gtm.setGuests(guests);
    }
    
    public void setGuest(Guest g, JTextField txt_lastname, JTextField txt_firstname, JTextField txt_age,
                        JTextField txt_mail, JTextField txt_phone, Operation o, Component rootPane) throws IOException, Exception{
            if(!txt_firstname.getText().equals("") 
                    && !txt_lastname.getText().equals("") && !txt_age.getText().equals("")
                    && !txt_mail.getText().equals("") && txt_mail.getText().contains("@")
                    && !txt_phone.getText().equals("")){
                String message = "Are you sure you want to add guest?";
                if(o == Operation.EDIT_GUEST){
                    message  = "Are you sure you want to save changes?";
                }
                int c = JOptionPane.showConfirmDialog(rootPane, message);
                
                if (c == JOptionPane.YES_OPTION){
                    g.setFirstname(txt_firstname.getText());
                    g.setLastname(txt_lastname.getText());
                    g.setEmail(txt_mail.getText());
                    try{
                        g.setAge(Integer.parseInt(txt_age.getText()));    
                    }
                    catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(rootPane, "Age must be a numeric value!");
                        throw new Exception("Age must be a numeric value!");
                    }
                    g.setPhone_number(txt_phone.getText());
                    Response r = Controller.getInstance().GenericOperation(g, o); 
                    
                    if (r.getException() == null){
                        JOptionPane.showMessageDialog(rootPane, r.getMessage());
                    }
                    else{
                        String message2 = "Could not add guest!";
                        if(o == Operation.EDIT_GUEST){
                            message2  ="Could not save changes!";
                        }
                        JOptionPane.showMessageDialog(rootPane, message2);
                        throw new Exception(r.getException());
                    }
                }
            }
        else{
                JOptionPane.showMessageDialog(rootPane, "Invalid data!");
            }
        }
    
    public void selectGuest(JTable tbl_guests, FrmTable ft, Component rootPane, User u){
        GuestTableModel gtm = (GuestTableModel) tbl_guests.getModel();
        if (tbl_guests.getSelectedRow() >= 0){            
           Guest g = gtm.getGuestAt(tbl_guests.getSelectedRow());
           System.out.println(g.getAge());
           ft.setVisible(false);
           ft.dispose();
           FrmGuest f = new FrmGuest(FormMode.VIEW_MODE, g, u);
           f.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Please select a guest!");
        }
    }
    
    public void filterGuests(JComboBox combo_box_filter, JTextField txt_filter, JTable tbl_guests, Component rootPane, JComboBox f){
        try {
            if (combo_box_filter.getSelectedItem() != null && !txt_filter.getText().equals("") && txt_filter.getText()!= null){
               GuestTableModel gtm = (GuestTableModel) tbl_guests.getModel();               
               String filter = gtm.getTable_column_names()[combo_box_filter.getSelectedIndex()] + f.getSelectedItem().toString() + "'" + txt_filter.getText()+"'";
               FormController.getInstance().setGuestTableModel(tbl_guests, Operation.FILTER_GUESTS, filter); 
            }            
            else{
                JOptionPane.showMessageDialog(rootPane, "You must select and write a filter condition!");
            }
        } catch (IOException ex) {
            Logger.getLogger(FrmTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setBoard(JTextField txt_name, JTextField txt_id, JTextField txt_price, Board b, Operation o, Component rootPane) 
            throws IOException, Exception{
        
        if (!txt_name.getText().equals("") && !txt_price.getText().equals("")){   
            String message = "Are you sure you want to add board?";
                if(o == Operation.EDIT_BOARD){
                    message  = "Are you sure you want to save changes?";
                }
                int c = JOptionPane.showConfirmDialog(rootPane, message);
                
            if(c == JOptionPane.YES_OPTION){
                b.setName(txt_name.getText());
                try{
                    b.setTotal_price(Double.parseDouble(txt_price.getText()));
                }
                catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(rootPane, "Price must be a numeric value!");
                    throw new Exception("Price must be a numeric value!");
                }
                Response r = Controller.getInstance().GenericOperation(b, o);               
                if (r.getException() == null){
                    JOptionPane.showMessageDialog(rootPane, r.getMessage());
                }
                else{
                    String message2 = "Could not add board!";
                    if(o == Operation.EDIT_BOARD){
                        message2  ="Could not save changes!";
                    }
                    JOptionPane.showMessageDialog(rootPane, message2);
                    throw new Exception(r.getException());
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "All fields are mandatory!");
        }                            
    }

    public void selectBoard(JTable tbl, FrmTable aThis, JRootPane rootPane, User u) {
        BoardTableModel gtm = (BoardTableModel) tbl.getModel();
        if (tbl.getSelectedRow() >= 0){            
           Board g = gtm.getBoardAt(tbl.getSelectedRow());
           aThis.setVisible(false);
           aThis.dispose();
           FrmBoard f = new FrmBoard(FormMode.VIEW_MODE, g, u);
           f.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Please select a board!");
        }
    }

    public void setBoardTableModel(JTable tbl, Operation o, Object entity) throws IOException, Exception {
        BoardTableModel gtm = new BoardTableModel();
        Response r = Controller.getInstance().GenericOperation(entity, o);
        List <GenericEntity> list = (List <GenericEntity>) r.getResult();
        tbl.setModel(gtm);
        List <Board> boards = new ArrayList();
        if (list!= null){
            System.out.println("NOT NULL");
            for (GenericEntity g: list){
                boards.add((Board) g);
            }
        }
        
        gtm.setBoards(boards);
    }

    public void filterBoards(JComboBox<String> combo_box_filter, JTextField txt_filter, JTable tbl, JRootPane rootPane, JComboBox f) {
        try {
            if (combo_box_filter.getSelectedItem() != null && !txt_filter.getText().equals("") && txt_filter.getText()!= null){
               BoardTableModel gtm = (BoardTableModel) tbl.getModel();               
               String filter = gtm.getTable_column_names()[combo_box_filter.getSelectedIndex()] + f.getSelectedItem().toString() + "'" + txt_filter.getText()+"'";
               FormController.getInstance().setBoardTableModel(tbl, Operation.FILTER_BOARDS, filter); 
            }            
            else{
                JOptionPane.showMessageDialog(rootPane, "You must select and write a filter condition!");
            }
        } catch (IOException ex) {
            Logger.getLogger(FrmTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setReservationTableModel(JTable tbl, Operation o, Object entity) throws IOException, Exception {
        ReservationTableModel gtm = new ReservationTableModel();
        Response r = Controller.getInstance().GenericOperation(entity, o);
        Response i = Controller.getInstance().GenericOperation(entity, Operation.GET_ALL_RESERVATION_ITEMS); 
        List <GenericEntity> list = (List <GenericEntity>) r.getResult();
        List <GenericEntity> item_list = (List <GenericEntity>) i.getResult();
        tbl.setModel(gtm);
        List <Reservation> reservations = new ArrayList();
        if (list!= null){
            for (GenericEntity g: list){
                if (item_list != null){
                    for(GenericEntity e: item_list){
                        if (((ReservationItem) e).getBoard().getId() == ((Reservation) g).getBoard().getId() &&
                            ((ReservationItem) e).getGuest().getId() == ((Reservation) g).getGuest().getId()){
                            ((Reservation) g).getReservation_items().add((ReservationItem) e);
                        }
                    }
                }               
                reservations.add((Reservation) g);
            }
        }
        
        gtm.setReservations(reservations);
    }
    
    public void selectReservation(JTable tbl, FrmTable aThis, JRootPane rootPane, User u) {
        ReservationTableModel gtm = (ReservationTableModel) tbl.getModel();
        if (tbl.getSelectedRow() >= 0){            
           Reservation g = gtm.getReservationAt(tbl.getSelectedRow());
           aThis.setVisible(false);
           aThis.dispose();
           FrmReservation f = new FrmReservation(FormMode.VIEW_MODE, g, u);
           f.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Please select a reservation!");
        }
    }
    
    public void filterReservations(JComboBox<String> combo_box_filter, JTextField txt_filter, JTable tbl, JRootPane rootPane, JComboBox f) {
        try {
            if (combo_box_filter.getSelectedItem() != null && !txt_filter.getText().equals("") && txt_filter.getText()!= null){
               ReservationTableModel gtm = (ReservationTableModel) tbl.getModel();               
               String filter = gtm.getTable_column_names()[combo_box_filter.getSelectedIndex()] + f.getSelectedItem().toString() + "'" + txt_filter.getText()+"'";
               FormController.getInstance().setReservationTableModel(tbl, Operation.FILTER_RESERVATIONS, filter); 
            }            
            else{
                JOptionPane.showMessageDialog(rootPane, "You must select and write a filter condition!");
            }
        } catch (IOException ex) {
            Logger.getLogger(FrmTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setRoomTableModel(JTable tbl, Operation o, Object entity) throws IOException, Exception {
        RoomTableModel gtm = new RoomTableModel();
        Response r = Controller.getInstance().GenericOperation(entity, o);
        List <GenericEntity> list = (List <GenericEntity>) r.getResult();
        tbl.setModel(gtm);
        List <Room> rooms = new ArrayList();
        if (list!= null){
            for (GenericEntity g: list){
                rooms.add((Room) g);
            }
        }
        
        gtm.setRooms(rooms);
    }

    public void selectRoom(JTable tbl, FrmTable aThis, JRootPane rootPane, User u) {
        RoomTableModel gtm = (RoomTableModel) tbl.getModel();
        if (tbl.getSelectedRow() >= 0){            
           Room g = gtm.getRoomAt(tbl.getSelectedRow());
           aThis.setVisible(false);
           aThis.dispose();
           FrmRoom f = new FrmRoom(FormMode.VIEW_MODE, g, u);
           f.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Please select a room!");
        }        
    }

    public void filterRooms(JComboBox<String> combo_box_filter, JTextField txt_filter, JTable tbl, JRootPane rootPane, JComboBox f) {
        try {
            if (combo_box_filter.getSelectedItem() != null && !txt_filter.getText().equals("") && txt_filter.getText()!= null){
               RoomTableModel gtm = (RoomTableModel) tbl.getModel();               
               String filter = gtm.getTable_column_names()[combo_box_filter.getSelectedIndex()] + f.getSelectedItem().toString() + "'" + txt_filter.getText()+"'";
               FormController.getInstance().setRoomTableModel(tbl, Operation.FILTER_ROOMS, filter); 
            }            
            else{
                JOptionPane.showMessageDialog(rootPane, "You must select and write a filter condition!");
            }
        } catch (IOException ex) {
            Logger.getLogger(FrmTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmTable.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }   
}
