/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Appointment;
import entity.Doctor;
import entity.Pet;
import entity.Staff;
import entity.Users;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ai
 */
public class AppointmentServlet extends HttpServlet {

    @EJB
    private AppointmentServiceLocal appointmentService;

    private static final int STAFF_ID = 1;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        if ("cancel".equals(action)) {
            String appointmentId = request.getParameter("id");
            if (appointmentId != null) {
                boolean cancelled = appointmentService.cancelAppointment(Long.parseLong(appointmentId));
                if (cancelled) {
                    request.setAttribute("successMessage", "Appointment cancelled successfully.");
                } else {
                    request.setAttribute("errorMessage", "Failed to cancel appointment.");
                }
            }
        }

        // Load user's appointments
        List<Appointment> appointments = appointmentService.getUserAppointments(user.getId());
        request.setAttribute("appointments", appointments);

        // Load pets for the dropdown
        List<Pet> pets = appointmentService.getUserPets(user.getId());
        request.setAttribute("pets", pets);

        // Load doctors
        List<Doctor> doctors = appointmentService.getAllDoctors();
        request.setAttribute("doctors", doctors);

        request.getRequestDispatcher("/manage-appointment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            Long petId = Long.parseLong(request.getParameter("petId"));
            Long doctorId = Long.parseLong(request.getParameter("doctorId"));
            String dateStr = request.getParameter("appointmentDate");
            String timeStr = request.getParameter("appointmentTime");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            Date appointmentDate = dateFormat.parse(dateStr);
            Date appointmentTime = timeFormat.parse(timeStr);

            // Create new appointment
            Appointment appointment = new Appointment();
            appointment.setAppointmentDate(appointmentDate);
            appointment.setAppointmentTime(appointmentTime);
            appointment.setStatus("Pending");

            // Set relationships
            Pet pet = new Pet();
            pet.setPetId(petId);
            appointment.setPetId(pet);

            Doctor doctor = new Doctor();
            doctor.setDoctorId(doctorId);
            appointment.setDoctorId(doctor);
            appointment.setUserId(user);
            
            Staff staff = new Staff();
            staff.setStaffId(STAFF_ID);
            appointment.setStaffId(staff);

            // Save appointment
            boolean success = appointmentService.createAppointment(appointment);

            if (success) {
                request.setAttribute("successMessage", "Appointment scheduled successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to schedule appointment. Please try again.");
            }

        } catch (ParseException e) {
            request.setAttribute("errorMessage", "Invalid date or time format.");
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid selection.");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
        }

        // Reload the page with updated data
        doGet(request, response);
    }
}
