<%-- 
    Document   : manage-appointment
    Created on : Sep 13, 2025, 11:30:18 AM
    Author     : ai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Appointments</title>
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
                <!-- Success/Error Messages -->
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">${successMessage}</div>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-error">${errorMessage}</div>
                </c:if>

                <!-- Schedule New Appointment -->
                <div class="content-wrapper">
                    <div class="page-header">
                        <h2 class="page-title">Schedule New Appointment</h2>
                        <p class="page-subtitle">Book an appointment for your pet</p>
                    </div>

                    <form action="AppointmentServlet" method="post" style="max-width: 600px; margin: 0 auto;">
                        <div class="form-group">
                            <label class="form-label">Select Pet</label>
                            <div class="checkbox-group">
                                <c:forEach items="${pets}" var="pet">
                                    <label class="checkbox-item">
                                        <input type="checkbox" name="petId" value="${pet.petId}">${pet.petName} (${pet.petSpecies} - ${pet.petBreed})
                                    </label>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Select Doctor</label>
                            <select class="form-select" name="doctorId" required>
                                <option value="">-- Select Doctor --</option>
                                <c:forEach items="${doctors}" var="doctor">
                                    <option value="${doctor.doctorId}">${doctor.name} - ${doctor.specialty}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Appointment Date</label>
                            <input class="form-input" type="date" name="appointmentDate" required>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Appointment Time</label>
                            <input class="form-input" type="time" name="appointmentTime" required>
                        </div>

                        <button type="submit" class="btn btn-success btn-lg" style="width: 100%;">Schedule Appointment</button>
                    </form>
                </div>

                <!-- Your Appointments -->
                <div class="table-container">
                    <div class="table-header">
                        <h2>Your Appointments</h2>
                    </div>

                    <c:choose>
                        <c:when test="${empty appointments}">
                            <div class="empty-state">
                                <div class="empty-state-icon">📅</div>
                                <h3 class="empty-state-title">No appointments scheduled</h3>
                                <p class="empty-state-message">You don't have any appointments scheduled yet. Book your first appointment using the form above.</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <table class="appointments-table">
                                <thead>
                                    <tr>
                                        <th>Pet Name</th>
                                        <th>Doctor</th>
                                        <th>Date</th>
                                        <th>Time</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${appointments}" var="appointment">
                                        <tr>
                                            <td>${appointment.petId.petName}</td>
                                            <td>${appointment.doctorId.name}</td>
                                            <td><fmt:formatDate value="${appointment.appointmentDate}" pattern="yyyy-MM-dd" /></td>
                                            <td><fmt:formatDate value="${appointment.appointmentTime}" pattern="HH:mm" /></td>
                                            <td>
                                                <span class="status-badge status-${appointment.status.toLowerCase()}">
                                                    ${appointment.status}
                                                </span>
                                            </td>
                                            <td>
                                                <c:if test="${appointment.status == 'Pending'}">
                                                    <a href="AppointmentServlet?action=cancel&id=${appointment.appointmentId}" 
                                                       class="btn btn-danger"
                                                       onclick="return confirm('Are you sure you want to cancel this appointment?')">
                                                        Cancel
                                                    </a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <script>
            // Set minimum date to tomorrow
            document.addEventListener('DOMContentLoaded', function () {
                const today = new Date();
                const tomorrow = new Date(today);
                tomorrow.setDate(tomorrow.getDate() + 1);

                // Format date as YYYY-MM-DD
                const yyyy = tomorrow.getFullYear();
                const mm = String(tomorrow.getMonth() + 1).padStart(2, '0');
                const dd = String(tomorrow.getDate()).padStart(2, '0');
                const minDate = yyyy + '-' + mm + '-' + dd;

                const dateInput = document.querySelector('input[name="appointmentDate"]');
                if (dateInput) {
                    dateInput.min = minDate;
                }

                // Set minimum time to current time if today is selected
                const timeInput = document.querySelector('input[name="appointmentTime"]');
                if (dateInput && timeInput) {
                    dateInput.addEventListener('change', function () {
                        const selectedDate = new Date(this.value);
                        const today = new Date();

                        // Reset to remove any previous min time restrictions
                        timeInput.removeAttribute('min');

                        // Check if dates are the same day
                        if (selectedDate.toDateString() === today.toDateString()) {
                            // If selected date is today, set min time to current time + 1 hour
                            const hours = String(today.getHours()).padStart(2, '0');
                            const minutes = String(today.getMinutes()).padStart(2, '0');
                            timeInput.min = hours + ':' + minutes;
                        }
                    });
                }
            });
        </script>
    </body>
</html>