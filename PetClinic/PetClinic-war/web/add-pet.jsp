<%-- 
    Document   : add-pet
    Created on : Sep 13, 2025, 10:09:13 AM
    Author     : ai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Pet</title>
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
                        <h2 class="form-title">Add New Pet</h2>
                        <p class="form-subtitle">Register your furry friend with Pet Clinic</p>
                    </div>

                    <form action="PetServlet?action=create" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="form-label">Pet Name</label>
                            <input type="text" name="petName" class="form-input" required>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Species</label>
                            <select name="petSpecies" class="form-select" required>
                                <option value="">-- Select Species --</option>
                                <option value="Dog">Dog</option>
                                <option value="Cat">Cat</option>
                                <option value="Bird">Bird</option>
                                <option value="Fish">Fish</option>
                                <option value="Rabbit">Rabbit</option>
                                <option value="Hamster">Hamster</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Breed (Optional)</label>
                            <input type="text" name="petBreed" class="form-input">
                        </div>

                        <!-- Medical Information -->
                        <div class="form-group">
                            <label class="form-label">Medical Information</label>
                            <textarea name="petInfo" class="form-input" rows="4" placeholder="Enter medical information..."></textarea>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Pet Picture</label>
                            <input type="file" name="petPicture" class="form-file" accept="image/*">
                        </div>

                        <button type="submit" class="btn btn-success btn-lg" style="width: 100%;">Add Pet</button>
                    </form>

                    <div class="form-footer">
                        <a href="PetServlet">← Back to Pet List</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
