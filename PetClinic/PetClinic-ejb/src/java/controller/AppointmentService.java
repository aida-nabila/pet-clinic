/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package controller;

import entity.Appointment;
import entity.Doctor;
import entity.Pet;
import java.util.Collections;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ai
 */
@Stateless
@PermitAll
public class AppointmentService implements AppointmentServiceLocal {

    @PersistenceContext(unitName = "PetClinicLibraryPU")
    private EntityManager em;

    @Override
    public boolean createAppointment(Appointment appointment) {
        try {
            em.persist(appointment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cancelAppointment(Long appointmentId) {
        try {
            Appointment appointment = em.find(Appointment.class, appointmentId);
            if (appointment != null) {
                appointment.setStatus("CANCELLED");
                em.merge(appointment);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Appointment> getUserAppointments(Long userId) {
        TypedQuery<Appointment> query = em.createQuery(
                "SELECT a FROM Appointment a WHERE a.userId.id = :userId ORDER BY a.appointmentDate, a.appointmentTime",
                Appointment.class
        );
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Pet> getUserPets(Long userId) {
        TypedQuery<Pet> query = em.createQuery(
                "SELECT p FROM Pet p WHERE p.userId.id = :userId",
                Pet.class
        );
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Doctor> getAllDoctors() {
        try {
            TypedQuery<Doctor> query = em.createQuery(
                    "SELECT d FROM Doctor d ORDER BY d.name",
                    Doctor.class
            );
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
