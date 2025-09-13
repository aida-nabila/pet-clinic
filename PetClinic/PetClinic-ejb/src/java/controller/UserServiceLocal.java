/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package controller;

import entity.Users;
import javax.ejb.Local;

/**
 *
 * @author ai
 */
@Local
public interface UserServiceLocal {

    void registerUser(String name, String email, String password);

    boolean isEmailExist(String email);
    
    Users authenticate(String email, String password);
}
