<%-- 
    Document   : staff-dashboard
    Created on : Sep 13, 2025, 8:20:04 PM
    Author     : ai
--%>

<%@ page import="java.util.*, entity.*, java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Redirect if staff is not logged in
    Staff staff = (Staff) session.getAttribute("staff");
    if (staff == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Staff Dashboard</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div class="page-container">

            <!-- Navigation Bar -->
            <nav class="navbar">
                <div class="logo">🐾 Pet Clinic</div>
                <div class="nav-links">
                    <a href="StaffAppointmentServlet" class="nav-link">Appointments</a>
                    <a href="LoginServlet?action=logout" class="logout-btn">Logout</a>
                </div>
            </nav>

            <!-- Main Content -->
            <div class="main-content">

                <!-- Dashboard Header -->
                <div class="dashboard-header">
                    <div class="user-info">
                        <h1>Staff Dashboard</h1>
                        <p class="user-details">Welcome, ${sessionScope.staff.name} (${sessionScope.staff.role})</p>
                    </div>
                    <div class="user-info">
                        <p style="color: #8B5FBF; font-weight: bold;">Staff Portal</p>
                    </div>
                </div>

                <!-- Success/Error messages -->
                <c:if test="${not empty error}">
                    <div class="alert alert-error" style="background-color: #f8d7da; color: #721c24; padding: 10px; margin-bottom: 10px; border-radius: 5px;">
                        ${fn:escapeXml(error)}
                    </div>
                </c:if>
                <c:if test="${not empty success}">
                    <div class="alert alert-success" style="background-color: #d4edda; color: #155724; padding: 10px; margin-bottom: 10px; border-radius: 5px;">
                        ${fn:escapeXml(success)}
                    </div>
                </c:if>

                <!-- Dashboard Stats -->
                <div class="dashboard-actions">
                    <div class="action-card">
                        <h3 class="action-title">Total Appointments</h3>
                        <p class="action-description">
                            <c:choose>
                                <c:when test="${not empty appointmentList}">
                                    ${fn:length(appointmentList)} appointments
                                </c:when>
                                <c:otherwise>0 appointments</c:otherwise>
                            </c:choose>
                        </p>
                    </div>

                    <div class="action-card">
                        <h3 class="action-title">Pending Appointments</h3>
                        <p class="action-description">
                            <c:set var="pendingCount" value="0" />
                            <c:forEach items="${appointmentList}" var="appointment">
                                <c:if test="${appointment.status eq 'Pending'}">
                                    <c:set var="pendingCount" value="${pendingCount + 1}" />
                                </c:if>
                            </c:forEach>
                            ${pendingCount} pending reviews
                        </p>
                    </div>

                    <div class="action-card">
                        <h3 class="action-title">Accepted Appointments</h3>
                        <p class="action-description">
                            <c:set var="acceptedCount" value="0" />
                            <c:forEach items="${appointmentList}" var="appointment">
                                <c:if test="${appointment.status eq 'Accepted'}">
                                    <c:set var="acceptedCount" value="${acceptedCount + 1}" />
                                </c:if>
                            </c:forEach>
                            ${acceptedCount} confirmed
                        </p>
                    </div>
                </div>

                <!-- Appointments Table -->
                <div class="table-container">
                    <div class="table-header">Appointment Management</div>
                    <c:choose>
                        <c:when test="${not empty appointmentList}">
                            <table class="appointments-table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Date</th>
                                        <th>Time</th>
                                        <th>Pet Name</th>
                                        <th>Pet Species</th>
                                        <th>Doctor</th>
                                        <th>User</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${appointmentList}" var="appointment">
                                        <tr>
                                            <td>#${appointment.appointmentId}</td>
                                            <td><fmt:formatDate value="${appointment.appointmentDate}" pattern="yyyy-MM-dd" /></td>
                                            <td><fmt:formatDate value="${appointment.appointmentTime}" pattern="HH:mm" /></td>
                                            <td style="font-weight: bold; color: #8B5FBF;">${appointment.petId.petName}</td>
                                            <td>${appointment.petId.petSpecies}</td>
                                            <td>${appointment.doctorId.name}</td>
                                            <td>${appointment.userId.name}</td>
                                            <td>
                                                <span class="status-badge status-${fn:toLowerCase(appointment.status)}">
                                                    ${appointment.status}
                                                </span>
                                            </td>
                                            <td>
                                                <div class="flex gap-sm">
                                                    <c:if test="${appointment.status eq 'Pending'}">
                                                        <a href="StaffAppointmentServlet?action=accept&id=${appointment.appointmentId}"
                                                           class="btn btn-success btn-sm"
                                                           onclick="return confirm('Accept this appointment for ${appointment.petId.petName}?')">
                                                            Accept
                                                        </a>
                                                        <a href="StaffAppointmentServlet?action=cancel&id=${appointment.appointmentId}"
                                                           class="btn btn-danger btn-sm"
                                                           onclick="return confirm('Cancel this appointment for ${appointment.petId.petName}?')">
                                                            Cancel
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${appointment.status eq 'Accepted'}">
                                                        <a href="StaffAppointmentServlet?action=complete&id=${appointment.appointmentId}"
                                                           class="btn btn-primary btn-sm"
                                                           onclick="return confirm('Mark appointment for ${appointment.petId.petName} as completed?')">
                                                            Complete
                                                        </a>
                                                        <a href="StaffAppointmentServlet?action=cancel&id=${appointment.appointmentId}"
                                                           class="btn btn-danger btn-sm"
                                                           onclick="return confirm('Cancel this appointment for ${appointment.petId.petName}?')">
                                                            Cancel
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${appointment.status eq 'Completed'}">
                                                        <span style="color: #2ECC71; font-weight: bold;">Done</span>
                                                    </c:if>
                                                    <c:if test="${appointment.status eq 'Cancelled'}">
                                                        <span style="color: #E74C3C; font-weight: bold;">Cancelled</span>
                                                    </c:if>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <div style="padding: 3rem; text-align: center;">
                                <h3 style="color: #8B5FBF; margin-bottom: 1rem;">No Appointments Found</h3>
                                <p style="color: #6c757d;">There are currently no appointments in the system.</p>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <!-- Instructions -->
                <div class="content-wrapper" style="margin-top: 2rem; background: #f8f9fa;">
                    <h3 style="color: #8B5FBF; margin-bottom: 1rem;">Instructions</h3>
                    <ul style="color: #6c757d; line-height: 1.8;">
                        <li><strong>Accept:</strong> Confirm the appointment and notify the user</li>
                        <li><strong>Cancel:</strong> Cancel the appointment</li>
                        <li><strong>Complete:</strong> Mark appointment as finished after the visit</li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Auto-refresh -->
        <script>
            setTimeout(function () {
                window.location.reload();
            }, 120000);

            document.addEventListener('DOMContentLoaded', function () {
                const now = new Date();
                const timestamp = document.createElement('div');
                timestamp.style.cssText = 'position: fixed; bottom: 10px; right: 10px; background: #8B5FBF; color: white; padding: 5px 10px; border-radius: 5px; font-size: 12px;';
                timestamp.textContent = 'Last updated: ' + now.toLocaleTimeString();
                document.body.appendChild(timestamp);
            });
        </script>
    </body>
</html>
