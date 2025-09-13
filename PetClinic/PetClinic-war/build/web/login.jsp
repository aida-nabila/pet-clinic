<%-- 
    Document   : register-success
    Created on : Jun 7, 2025, 5:42:03 PM
    Author     : ai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="center-container">
            <div class="form-container">
                <div class="form-header">
                    <h2 class="form-title">Welcome Back</h2>
                    <p class="form-subtitle">Log in to your Pet Clinic account</p>
                </div>

                <%-- Display error message if any --%>
                <% if (request.getParameter("error") != null) { %>
                <div class="alert alert-error">
                    Invalid email or password. Please try again.
                </div>
                <% }%>

                <form action="LoginServlet" method="post">
                    <div class="form-group">
                        <label class="form-label">Email Address</label>
                        <input type="email" name="email" class="form-input" required>
                    </div>

                    <div class="form-group">
                        <label class="form-label">Password</label>
                        <input type="password" name="password" class="form-input" required>
                    </div>

                    <button type="submit" class="btn btn-primary btn-lg" style="width: 100%;">Login</button>
                </form>

                <div class="form-footer">
                    <p>Don't have an account? <a href="register.jsp">Register here</a></p>
                    <p><a href="index.jsp">Back to Home</a></p>
                </div>
            </div>
        </div>
    </body>
</html>
