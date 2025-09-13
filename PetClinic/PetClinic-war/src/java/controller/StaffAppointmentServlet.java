package controller;

import entity.Appointment;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class StaffAppointmentServlet extends HttpServlet {

    @EJB
    private StaffAppointmentServiceLocal staffAppointmentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userRole") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userRole = (String) session.getAttribute("userRole");
        if (!"Staff".equalsIgnoreCase(userRole) && !"Nurse".equalsIgnoreCase(userRole)) {
            response.sendRedirect("unauthorized.jsp");
            return;
        }

        String action = request.getParameter("action");

        if ("cancel".equalsIgnoreCase(action)) {
            updateStatus(request, response, "Cancelled");
        } else if ("accept".equalsIgnoreCase(action)) {
            updateStatus(request, response, "Accepted");
        } else if ("complete".equalsIgnoreCase(action)) {
            updateStatus(request, response, "Completed");
        } else {
            showAppointments(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Simply show appointments on POST
        showAppointments(request, response);
    }

    private void showAppointments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Appointment> appointmentList = staffAppointmentService.getAllAppointments();
        request.setAttribute("appointmentList", appointmentList);
        request.getRequestDispatcher("staff-dashboard.jsp").forward(request, response);
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response, String status)
            throws ServletException, IOException {

        try {
            Long appointmentId = Long.parseLong(request.getParameter("id"));
            boolean success = staffAppointmentService.updateAppointmentStatus(appointmentId, status);

            if (success) {
                request.setAttribute("success", "Appointment " + status.toLowerCase() + " successfully");
            } else {
                request.setAttribute("error", "Failed to update appointment");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid appointment ID");
        }

        showAppointments(request, response);
    }
}
