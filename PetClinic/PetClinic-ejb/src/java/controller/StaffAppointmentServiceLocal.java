/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package controller;

import entity.Appointment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ai
 */
@Local
public interface StaffAppointmentServiceLocal {

    List<Appointment> getAllAppointments();

    boolean updateAppointmentStatus(Long appointmentId, String status);
}
