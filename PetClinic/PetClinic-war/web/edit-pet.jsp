<%-- 
    Document   : edit-pet
    Created on : Sep 13, 2025, 11:38:19 AM
    Author     : ai
--%>

<%@page import="entity.Pet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Pet pet = (Pet) request.getAttribute("pet");
    if (pet == null) {
        response.sendRedirect("PetServlet?action=list");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Pet - <%= pet.getPetName()%></title>
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
                <div class="form-container">
                    <div class="form-header">
                        <h2 class="form-title">Edit Pet: <%= pet.getPetName()%></h2>
                        <p class="form-subtitle">Update your pet's information</p>
                    </div>

                    <form action="PetServlet" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="petId" value="<%= pet.getPetId()%>">

                        <div class="form-group">
                            <label class="form-label">Pet Name *</label>
                            <input type="text" name="petName" class="form-input" 
                                   value="<%= pet.getPetName()%>" required>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Species *</label>
                            <select name="petSpecies" class="form-select" required>
                                <option value="">-- Select Species --</option>
                                <option value="Dog" <%= "Dog".equals(pet.getPetSpecies()) ? "selected" : ""%>>Dog</option>
                                <option value="Cat" <%= "Cat".equals(pet.getPetSpecies()) ? "selected" : ""%>>Cat</option>
                                <option value="Bird" <%= "Bird".equals(pet.getPetSpecies()) ? "selected" : ""%>>Bird</option>
                                <option value="Fish" <%= "Fish".equals(pet.getPetSpecies()) ? "selected" : ""%>>Fish</option>
                                <option value="Rabbit" <%= "Rabbit".equals(pet.getPetSpecies()) ? "selected" : ""%>>Rabbit</option>
                                <option value="Hamster" <%= "Hamster".equals(pet.getPetSpecies()) ? "selected" : ""%>>Hamster</option>
                                <option value="Other" <%= "Other".equals(pet.getPetSpecies()) ? "selected" : ""%>>Other</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Breed</label>
                            <input type="text" name="petBreed" class="form-input" 
                                   value="<%= pet.getPetBreed() != null ? pet.getPetBreed() : ""%>"
                                   placeholder="Enter breed (optional)">
                        </div>

                        <div class="form-group">
                            <label class="form-label">Current Image</label>
                            <div class="current-image-container">
                                <% if (pet.getPetPicture() != null && pet.getPetPicture().length > 0) {
                                    String base64Image = java.util.Base64.getEncoder().encodeToString(pet.getPetPicture());
                                %>
                                <img src="data:image/jpeg;base64,<%= base64Image%>" 
                                     style="max-width: 200px; max-height: 200px; border-radius: 8px;" 
                                     alt="Current pet image">
                                <p style="margin-top: 0.5rem; color: #6c757d; font-size: 0.9rem;">
                                    Current image: <%= pet.getPictureFileName() != null ? pet.getPictureFileName() : "Uploaded image"%>
                                </p>
                                <% } else { %>
                                <p style="color: #6c757d; font-style: italic;">No current image available</p>
                                <% }%>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Update Image</label>
                            <input type="file" name="petPicture" class="form-file" accept="image/*">
                            <p style="color: #6c757d; font-size: 0.9rem; margin-top: 0.5rem;">
                                Leave blank to keep current image. Max 5MB.
                            </p>
                        </div>

                        <div class="flex gap-md">
                            <button type="submit" class="btn btn-primary btn-lg" style="flex: 1;">
                                Update Pet
                            </button>
                            <a href="PetServlet" class="btn btn-secondary btn-lg" 
                               style="flex: 1; text-decoration: none; text-align: center;">
                                Cancel
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>