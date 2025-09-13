/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package controller;

import entity.Appointment;
import entity.Doctor;
import entity.Pet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ai
 */
@Local
public interface AppointmentServiceLocal {

    boolean createAppointment(Appointment appointment);

    boolean cancelAppointment(Long appointmentId);

    List<Appointment> getUserAppointments(Long userId);

    List<Pet> getUserPets(Long userId);

    List<Doctor> getAllDoctors();

}
