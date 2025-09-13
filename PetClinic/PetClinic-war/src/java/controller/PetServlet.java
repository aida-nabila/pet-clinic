/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Pet;
import entity.Users;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author ai
 */
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5, // 5 MB max file size
        maxRequestSize = 1024 * 1024 * 10 // 10 MB max request size
)
public class PetServlet extends HttpServlet {

    @EJB
    private PetServiceLocal petService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Default action
        }

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "create":
                addPet(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updatePet(request, response);
                break;
            case "delete":
                deletePet(request, response);
                break;
            case "list":
            default:
                listPets(request, response);
                break;
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/add-pet.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Users currentUser = (Users) session.getAttribute("user");

            if (currentUser == null) {
                response.sendRedirect("LoginServlet");
                return;
            }

            String petIdStr = request.getParameter("petId");
            if (petIdStr != null && !petIdStr.isEmpty()) {
                Long petId = Long.parseLong(petIdStr);
                Pet pet = petService.getPetById(petId);

                // Check if pet belongs to current user
                if (pet != null && pet.getUserId().getId().equals(currentUser.getId())) {
                    request.setAttribute("pet", pet);
                    request.getRequestDispatcher("/edit-pet.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("errorMessage", "Pet not found or access denied!");
                    response.sendRedirect("PetServlet?action=list");
                }
            } else {
                request.getSession().setAttribute("errorMessage", "Pet ID is required!");
                response.sendRedirect("PetServlet?action=list");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Error loading pet: " + e.getMessage());
            response.sendRedirect("PetServlet?action=list");
        }
    }

    private void addPet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            Users currentUser = (Users) session.getAttribute("user");

            if (currentUser == null) {
                response.sendRedirect("LoginServlet");
                return;
            }

            // Get form parameters
            String petName = request.getParameter("petName");
            String petSpecies = request.getParameter("petSpecies");
            String petBreed = request.getParameter("petBreed");

            // Create new pet
            Pet pet = new Pet();
            pet.setPetName(petName);
            pet.setPetSpecies(petSpecies);
            pet.setPetBreed(petBreed);
            pet.setUserId(currentUser);

            // Handle file upload
            Part filePart = request.getPart("petPicture");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = getFileName(filePart);
                InputStream fileContent = filePart.getInputStream();

                // Java 8 compatible way to read bytes
                byte[] pictureData = readAllBytesJava8(fileContent);

                pet.setPetPicture(pictureData);
                pet.setPictureFileName(fileName);
            }

            // Save pet
            petService.addPet(pet);

            request.getSession().setAttribute("successMessage", "Pet added successfully!");
            response.sendRedirect("PetServlet?action=list");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Error adding pet: " + e.getMessage());
            response.sendRedirect("PetServlet?action=add");
        }
    }

    private void updatePet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Users currentUser = (Users) session.getAttribute("user");

            if (currentUser == null) {
                response.sendRedirect("LoginServlet");
                return;
            }

            // Get form parameters
            String petIdStr = request.getParameter("petId");
            String petName = request.getParameter("petName");
            String petSpecies = request.getParameter("petSpecies");
            String petBreed = request.getParameter("petBreed");

            if (petIdStr == null || petIdStr.isEmpty()) {
                request.getSession().setAttribute("errorMessage", "Pet ID is required!");
                response.sendRedirect("PetServlet?action=list");
                return;
            }

            Long petId = Long.parseLong(petIdStr);
            Pet pet = petService.getPetById(petId);

            // Check if pet belongs to current user
            if (pet == null || !pet.getUserId().getId().equals(currentUser.getId())) {
                request.getSession().setAttribute("errorMessage", "Pet not found or access denied!");
                response.sendRedirect("PetServlet?action=list");
                return;
            }

            // Update pet details
            pet.setPetName(petName);
            pet.setPetSpecies(petSpecies);
            pet.setPetBreed(petBreed);

            // Handle file upload - only update if new file is provided
            Part filePart = request.getPart("petPicture");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = getFileName(filePart);
                InputStream fileContent = filePart.getInputStream();
                byte[] pictureData = readAllBytesJava8(fileContent);
                pet.setPetPicture(pictureData);
                pet.setPictureFileName(fileName);
            }

            // Update pet
            petService.updatePet(pet);

            request.getSession().setAttribute("successMessage", "Pet updated successfully!");
            response.sendRedirect("PetServlet?action=list");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Error updating pet: " + e.getMessage());
            response.sendRedirect("PetServlet?action=list");
        }
    }

    // Helper method for Java 8 compatibility
    private byte[] readAllBytesJava8(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384]; // 16KB buffer

        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        return buffer.toByteArray();
    }

    private void deletePet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String petIdStr = request.getParameter("petId");
            if (petIdStr != null && !petIdStr.isEmpty()) {
                Long petId = Long.parseLong(petIdStr);
                boolean deleted = petService.deletePet(petId);

                if (deleted) {
                    request.getSession().setAttribute("successMessage", "Pet deleted successfully!");
                } else {
                    request.getSession().setAttribute("errorMessage", "Pet not found!");
                }
            }
            response.sendRedirect("PetServlet?action=list");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Error deleting pet: " + e.getMessage());
            response.sendRedirect("PetServlet?action=list");
        }
    }

    private void listPets(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            Users currentUser = (Users) session.getAttribute("user");

            if (currentUser == null) {
                response.sendRedirect("LoginServlet");
                return;
            }

            // Get pets for current user
            List<Pet> pets = petService.getPetsByOwner(currentUser.getId());
            request.setAttribute("pets", pets);

            request.getRequestDispatcher("/manage-pet.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Error loading pets: " + e.getMessage());
            response.sendRedirect("dashboard.jsp");
        }
    }

    // Helper method to get filename from Part
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "unknown";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
