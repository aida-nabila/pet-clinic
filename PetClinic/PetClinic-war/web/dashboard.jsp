<%-- 
    Document   : dashboard
    Created on : Sep 13, 2025, 9:47:53 AM
    Author     : ai
--%>

<%@page import="entity.Users" %>
<%
    // Check if user is logged in
    Users user = (Users) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Owner Dashboard</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="page-container">
            <!-- Navigation Bar -->
            <nav class="navbar">
                <div class="logo">
                    🐾 Pet Clinic
                </div>
                <div class="nav-links">
                    <a href="dashboard.jsp" class="nav-link">Dashboard</a>
                    <a href="PetServlet" class="nav-link">My Pets</a>
                    <a href="AppointmentServlet" class="nav-link">Appointments</a>
                    <a href="LoginServlet?action=logout" class="logout-btn">Logout</a>
                </div>
            </nav>

            <!-- Main Content -->
            <div class="main-content">
                <!-- Dashboard Header -->
                <div class="dashboard-header">
                    <div class="user-info">
                        <h1>Welcome, <%= user.getName() %>!</h1>
                        <p>Email: <%= user.getEmail() %></p>
                    </div>
                </div>

                <!-- Dashboard Actions -->
                <div class="dashboard-actions">
                    <div class="action-card">
                        <div class="action-icon">🐕</div>
                        <h3 class="action-title">Manage Pets</h3>
                        <p class="action-description">Add, edit, or view your registered pets</p>
                        <a href="PetServlet" class="btn btn-primary">View Pets</a>
                    </div>

                    <div class="action-card">
                        <div class="action-icon">📅</div>
                        <h3 class="action-title">Appointments</h3>
                        <p class="action-description">Schedule and manage your appointments</p>
                        <a href="AppointmentServlet" class="btn btn-primary">View Appointments</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
