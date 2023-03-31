/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author Luka
 */
public enum Operation implements Serializable{
    LOGIN,
    GET_GUEST,ADD_GUEST,DELETE_GUEST,EDIT_GUEST,FILTER_GUESTS,GET_ALL_GUESTS,
    GET_BOARD,ADD_BOARD,DELETE_BOARD,EDIT_BOARD,FILTER_BOARDS,GET_ALL_BOARDS,
    GET_RESERVATION,ADD_RESERVATION,DELETE_RESERVATION,EDIT_RESERVATION,FILTER_RESERVATIONS,GET_ALL_RESERVATIONS,
    ADD_RESERVATION_ITEM, GET_ALL_RESERVATION_ITEMS, DELETE_RESERVATION_ITEM,
    GET_ALL_ROOMS, GET_ROOM, EDIT_ROOM, FILTER_ROOMS,
    USER_END, LOGIN_END,
}
