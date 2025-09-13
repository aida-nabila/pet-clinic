<%-- 
    Document   : index
    Created on : Sep 13, 2025, 8:44:35 AM
    Author     : ai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Pet Clinic - Veterinary Services</title>
        <link href="css/style2.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&family=Playfair+Display:wght@400;700&display=swap" rel="stylesheet">
        <link rel="icon" type="image/png" href="https://via.placeholder.com/32x32.png?text=🐾">
    </head>
    <body>
        <nav class="navbar" role="navigation">
            <div class="nav-container">
                <div class="nav-brand">
                    <a href="index.jsp" class="logo"> <div class="logo-icon">🐾</div>
                        <span class="logo-text">Pet Clinic</span>
                    </a>
                </div>
                <div class="nav-menu">
                    <a href="index.jsp#services" class="nav-link">Services</a>
                    <a href="index.jsp#about" class="nav-link">About</a>
                    <a href="index.jsp#contact" class="nav-link">Contact</a>
                </div>
            </div>
        </nav>

        <section class="hero">
            <div class="hero-background">
                <div class="hero-gradient"></div>
            </div>
            <div class="hero-content">
                <div class="hero-text">
                    <h1 class="hero-title">
                        <span class="title-main">Welcome to</span>
                        <span class="title-brand">Pet Clinic</span>
                    </h1>
                    <p class="hero-subtitle">
                        Premium veterinary care for your beloved companions. 
                        Where compassion meets cutting-edge medical expertise.
                    </p>
                    <div class="hero-actions">
                        <a href="register.jsp" class="btn btn-primary">
                            <span class="btn-text">Register</span>
                            <span class="btn-icon">→</span>
                        </a>
                        <a href="login.jsp" class="btn btn-secondary">
                            <span class="btn-text">Login</span>
                        </a>
                    </div>
                </div>
                <div class="hero-image">
                    <div class="image-container">
                        <img src="https://images.unsplash.com/photo-1576201836106-db1758fd1c97?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80" 
                             alt="Professional veterinarian caring for pets" 
                             class="hero-img">
                        <div class="image-overlay"></div>
                    </div>
                </div>
            </div>
        </section>

        <section class="features" id="services"> <div class="container">
                <div class="features-grid">
                    <div class="feature-card">
                        <div class="feature-icon">🏥</div>
                        <h3 class="feature-title">State-of-the-Art Facility</h3>
                        <p class="feature-description">Modern equipment and sterile environments ensuring the highest standard of care</p>
                    </div>
                    <div class="feature-card">
                        <div class="feature-icon">👨‍⚕️</div>
                        <h3 class="feature-title">Expert Veterinarians</h3>
                        <p class="feature-description">Certified professionals with years of experience in animal healthcare</p>
                    </div>
                    <div class="feature-card">
                        <div class="feature-icon">❤️</div>
                        <h3 class="feature-title">Compassionate Care</h3>
                        <p class="feature-description">Treating every pet with the love and attention they deserve</p>
                    </div>
                    <div class="feature-card">
                        <div class="feature-icon">⏰</div>
                        <h3 class="feature-title">24/7 Emergency</h3>
                        <p class="feature-description">Round-the-clock emergency services for urgent medical needs</p>
                    </div>
                </div>
            </div>
        </section>

        <footer class="footer" id="contact"> <div class="container">
                <div class="footer-content">
                    <div class="footer-brand">
                        <a href="#" class="logo"> <div class="logo-icon">🐾</div>
                            <span class="logo-text">Pet Clinic</span>
                        </a>
                        <p class="footer-tagline">Your pet's health is our priority</p>
                    </div>
                    <div class="footer-links">
                        <div class="footer-section">
                            <h4>Services</h4>
                            <ul>
                                <li><a href="#">General Checkup</a></li>
                                <li><a href="#">Vaccinations</a></li>
                                <li><a href="#">Surgery</a></li>
                                <li><a href="#">Emergency Care</a></li>
                            </ul>
                        </div>
                        <div class="footer-section" id="about"> <h4>Contact</h4>
                            <ul>
                                <li>📍 123 Jalan Kucing, Bandar Petaling, 47000 Selangor, Malaysia</li>
                                <li>📞 +60 3-1234 5678</li>
                                <li>✉️ info@purrfectcare.my</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="footer-bottom">
                    <p>&copy; 2025 Pet Clinic. All rights reserved.</p>
                </div>
            </div>
        </footer>

    </body>
</html>