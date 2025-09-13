<%-- 
    Document   : register
    Created on : Jun 7, 2025, 5:41:25 PM
    Author     : ai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Registration</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div class="center-container">
            <div class="form-container">
                <div class="form-header">
                    <h2 class="form-title">Join Pet Clinic</h2>
                    <p class="form-subtitle">Create your account to get started</p>
                </div>

                <form action="RegisterServlet" method="POST">
                    <div class="form-group">
                        <label class="form-label" for="name">Name:</label>
                        <input class="form-input" type="text" id="name" name="name" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="email">Email:</label>
                        <input class="form-input" type="email" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="password">Password:</label>
                        <input class="form-input" type="password" id="password" name="password" required>
                    </div>
                    <div class="form-group">
                        <input class="btn btn-primary btn-lg" style="width: 100%;" type="submit" value="Register">
                    </div>
                </form>

                <div class="form-footer">
                    <p>Already have an account? <a href="login.jsp">Login here</a></p>
                    <p><a href="index.jsp">Back to Home</a></p>
                </div>
            </div>


            <%-- Display messages --%>
            <%
                String message = (String) request.getAttribute("message");
                String messageType = (String) request.getAttribute("messageType");
                if (message != null) {
            %>
            <div class="message <%= messageType%>">
                <%= message%>
            </div>
            <%
                }
            %>
        </div>
    </body>
</html>