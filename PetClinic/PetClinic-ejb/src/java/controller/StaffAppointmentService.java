package controller;

import entity.Appointment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class StaffAppointmentService implements StaffAppointmentServiceLocal {

    @PersistenceContext(unitName = "PetClinicLibraryPU")
    private EntityManager em;

    // Fetch all appointments with related entities
    public List<Appointment> getAllAppointments() {
        TypedQuery<Appointment> query = em.createQuery(
                "SELECT a FROM Appointment a "
                + "JOIN FETCH a.petId "
                + "JOIN FETCH a.doctorId "
                + "JOIN FETCH a.userId "
                + "ORDER BY a.appointmentDate DESC, a.appointmentTime DESC",
                Appointment.class
        );

        return query.getResultList();
    }

    // Update appointment status
    public boolean updateAppointmentStatus(Long appointmentId, String status) {
        Appointment appointment = em.find(Appointment.class, appointmentId);
        if (appointment != null) {
            appointment.setStatus(status);
            em.merge(appointment);
            return true;
        }
        return false;
    }

}
