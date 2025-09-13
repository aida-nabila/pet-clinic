package controller;

import entity.Staff;
import entity.Users;
import java.io.IOException;
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
public class LoginServlet extends HttpServlet {

    @EJB
    private UserServiceLocal userService;

    @EJB
    private StaffServiceLocal staffService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            request.setAttribute("message", "Email and password are required.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {
            Users user = userService.authenticate(email, password);
            Staff staff = staffService.authenticate(email, password);

            // Create session and store user information
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(30 * 60); // 30 minutes

            if (user != null) {
                // User login
                session.setAttribute("user", user);
                response.sendRedirect("dashboard.jsp");
                return;
            } else if (staff != null) {
                // Staff login             
                session.setAttribute("staff", staff);
                session.setAttribute("userRole", staff.getRole());
                response.sendRedirect("StaffAppointmentServlet");
                return;
            } else {
                request.setAttribute("message", "Invalid email or password.");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

        } catch (Exception e) {
            request.setAttribute("message", "An error occurred during login: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle logout
        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("login.jsp");
            return;
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles user login requests";
    }
}
