<%-- 
    Document   : manage-pet
    Created on : Sep 13, 2025, 10:43:46 AM
    Author     : ai
--%>

<%@page import="java.util.List"%>
<%@page import="entity.Pet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Pets</title>
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
                <div class="content-wrapper">
                    <div class="page-header">
                        <h1 class="page-title">My Pets</h1>
                        <p class="page-subtitle">Manage your registered pets</p>
                    </div>

                    <!-- Success/Error Messages -->
                    <% if (session.getAttribute("successMessage") != null) {%>
                    <div class="alert alert-success">
                        <%= session.getAttribute("successMessage")%>
                    </div>
                    <% session.removeAttribute("successMessage"); %>
                    <% } %>

                    <% if (session.getAttribute("errorMessage") != null) {%>
                    <div class="alert alert-error">
                        <%= session.getAttribute("errorMessage")%>
                    </div>
                    <% session.removeAttribute("errorMessage"); %>
                    <% } %>

                    <!-- Pets Section -->
                    <div class="pets-section">
                        <div class="pets-header">
                            <h2>Your Registered Pets</h2>
                            <a href="PetServlet?action=add" class="btn btn-success btn-lg">Add New Pet</a>
                        </div>

                        <div class="pets-grid">
                            <%
                                List<Pet> petList = (List<Pet>) request.getAttribute("pets");

                                if (petList != null && !petList.isEmpty()) {
                                    for (Pet pet : petList) {
                            %>
                            <div class="pet-card">
                                <div class="pet-image">
                                    <% if (pet.getPetPicture() != null && pet.getPetPicture().length > 0) {
                                            String base64Image = java.util.Base64.getEncoder().encodeToString(pet.getPetPicture());
                                    %>
                                    <img src="data:image/jpeg;base64,<%= base64Image%>" 
                                         alt="<%= pet.getPetName()%>" class="pet-image">
                                    <% } else { %>
                                    <div class="no-image">No Image Available</div>
                                    <% }%>
                                </div>
                                <div class="pet-details">
                                    <h3 class="pet-name"><%= pet.getPetName()%></h3>
                                    <div class="pet-info">
                                        <p><strong>Species:</strong> <%= pet.getPetSpecies()%></p>
                                        <% if (pet.getPetBreed() != null && !pet.getPetBreed().isEmpty()) {%>
                                        <p><strong>Breed:</strong> <%= pet.getPetBreed()%></p>
                                        <% }%>
                                    </div>
                                    <div class="pet-actions">
                                        <a href="PetServlet?action=edit&petId=<%= pet.getPetId()%>" class="btn btn-primary btn-sm">Edit</a>
                                        <a href="PetServlet?action=delete&petId=<%= pet.getPetId()%>" 
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Are you sure you want to delete <%= pet.getPetName()%>?')">
                                            Delete
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <%
                                }
                            } else {
                            %>
                            <div class="text-center" style="grid-column: 1 / -1;">
                                <h3 style="color: #8B5FBF; margin-bottom: 1rem;">No Pets Found</h3>
                                <p style="color: #6c757d; margin-bottom: 2rem;">You haven't added any pets yet. Click the button below to get started!</p>
                                <a href="PetServlet?action=add" class="btn btn-success btn-lg">Add Your First Pet</a>
                            </div>
                            <% }%>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            // Auto-hide alerts after 5 seconds
            setTimeout(function () {
                var alerts = document.querySelectorAll('.alert');
                alerts.forEach(function (alert) {
                    alert.style.display = 'none';
                });
            }, 5000);
        </script>
    </body>
</html>
