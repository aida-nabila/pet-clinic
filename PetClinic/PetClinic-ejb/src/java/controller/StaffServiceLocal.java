/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package controller;

import entity.Staff;
import javax.ejb.Local;

/**
 *
 * @author ai
 */
@Local
public interface StaffServiceLocal {

    boolean isEmailExist(String email);

    Staff authenticate(String email, String password);
}
