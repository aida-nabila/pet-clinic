/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ai
 */
public class RegisterServlet extends HttpServlet {

    @EJB
    private UserServiceLocal userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (name == null || name.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            request.setAttribute("message", "All fields are required.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        try {
            if (userService.isEmailExist(email)) {
                request.setAttribute("message", "Email already registered. Please use a different email.");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                userService.registerUser(name, email, password);
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            request.setAttribute("message", "An error occurred during registration: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles user registration requests";
    }

}
