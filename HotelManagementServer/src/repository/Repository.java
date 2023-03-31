/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;

/**
 *
 * @author Luka
 * @param <T>
 */
public interface Repository <T> {
    List <T> getAll(T param) throws Exception;
    void add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param) throws Exception;
    T getById(T param) throws Exception;
    List <T> filter (T param, String condition) throws Exception;
    T logIn(T param, String username, String password) throws Exception;
}
