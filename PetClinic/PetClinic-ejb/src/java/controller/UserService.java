/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package controller;

import entity.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Base64;

/**
 *
 * @author ai
 */
@Stateless
public class UserService implements UserServiceLocal {

    @PersistenceContext(unitName = "PetClinicLibraryPU")
    private EntityManager em;

    public void registerUser(String name, String email, String password) {
        Users user = new Users();
        user.setName(name);
        user.setEmail(email);
        // Hash password using SHA-256 before storing
        String hashedPassword = hashPassword(password);
        user.setPassword(hashedPassword);
        em.persist(user);
    }

    public boolean isEmailExist(String email) {
        try {
            // Use getSingleResult() if you expect 0 or 1 result
            // If it finds no result, it throws NoResultException
            em.createNamedQuery("Users.findByEmail", Users.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return true; // A user with this email was found
        } catch (NoResultException e) {
            return false; // No user found with this email
        } catch (Exception e) {
            // Log other exceptions for debugging, but treat as email not found for registration
            e.printStackTrace();
            return false;
        }
    }

    public Users authenticate(String email, String password) {
        try {
            Users user = em.createNamedQuery("Users.findByEmail", Users.class)
                    .setParameter("email", email)
                    .getSingleResult();

            // Verify password against hash
            if (user != null && validatePassword(password, user.getPassword())) {
                return user;
            }
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    // Password hasing using SHA-256
    private String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hasing password", e);
        }
    }

    private boolean validatePassword(String inputPassword, String storedHash) {
        String hashedInput = hashPassword(inputPassword);
        return hashedInput.equals(storedHash);
    }
}
