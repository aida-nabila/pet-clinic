/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package controller;

import entity.Staff;
import java.util.Base64;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ai
 */
@Stateless
public class StaffService implements StaffServiceLocal {

    @PersistenceContext(unitName = "PetClinicLibraryPU")
    private EntityManager em;

    public boolean isEmailExist(String email) {
        try {
            // Use getSingleResult() if you expect 0 or 1 result
            // If it finds no result, it throws NoResultException
            em.createNamedQuery("Staff.findByEmail", Staff.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return true; // A staff with this email was found
        } catch (NoResultException e) {
            return false; // No staff found with this email
        } catch (Exception e) {
            // Log other exceptions for debugging, but treat as email not found for registration
            e.printStackTrace();
            return false;
        }
    }

    public Staff authenticate(String email, String password) {
        try {
            Staff staff = em.createNamedQuery("Staff.findByEmail", Staff.class)
                    .setParameter("email", email)
                    .getSingleResult();

            // Verify password
            if (staff != null && validatePassword(password, staff.getPassword())) {
                return staff;
            }
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }


    private boolean validatePassword(String inputPassword, String storedHash) {
        String hashedInput = inputPassword;
        return hashedInput.equals(storedHash);
    }
}
