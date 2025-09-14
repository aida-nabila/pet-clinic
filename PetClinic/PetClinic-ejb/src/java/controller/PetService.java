/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package controller;

import entity.Pet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ai
 */
@Stateless
public class PetService implements PetServiceLocal {

    @PersistenceContext(unitName = "PetClinicLibraryPU")
    private EntityManager em;

    public Pet addPet(Pet pet) {
        try {
            em.persist(pet);
            em.flush(); // Ensure ID is generated
            return pet;
        } catch (Exception e) {
            throw new RuntimeException("Error adding pet: " + e.getMessage(), e);
        }
    }

    public Pet updatePet(Pet pet) {
        try {
            return em.merge(pet);
        } catch (Exception e) {
            throw new RuntimeException("Error updating pet: " + e.getMessage(), e);
        }
    }

    public boolean deletePet(Long petId) {
        try {
            Pet pet = em.find(Pet.class, petId);
            if (pet != null) {
                em.remove(pet);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting pet: " + e.getMessage(), e);
        }
    }

    public Pet getPetById(Long petId) {
        try {
            return em.find(Pet.class, petId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving pet: " + e.getMessage(), e);
        }
    }

    public List<Pet> getPetsByUser(Long id) {
        try {
            TypedQuery<Pet> query = em.createQuery(
                    "SELECT p FROM Pet p WHERE p.userId.id = :id ORDER BY p.petName",
                    Pet.class
            );
            query.setParameter("id", id);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving pets by user: " + e.getMessage(), e);
        }
    }

    public List<Pet> getAllPets() {
        try {
            TypedQuery<Pet> query = em.createQuery(
                    "SELECT p FROM Pet p ORDER BY p.petName",
                    Pet.class
            );
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all pets: " + e.getMessage(), e);
        }
    }

    public List<Pet> searchPetsByName(String name) {
        try {
            TypedQuery<Pet> query = em.createQuery(
                    "SELECT p FROM Pet p WHERE LOWER(p.petName) LIKE LOWER(:name) ORDER BY p.petName",
                    Pet.class
            );
            query.setParameter("name", "%" + name + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error searching pets by name: " + e.getMessage(), e);
        }
    }

    public List<Pet> getPetsBySpecies(String species) {
        try {
            TypedQuery<Pet> query = em.createQuery(
                    "SELECT p FROM Pet p WHERE p.petSpecies = :species ORDER BY p.petName",
                    Pet.class
            );
            query.setParameter("species", species);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving pets by species: " + e.getMessage(), e);
        }
    }

    public boolean uploadPetPicture(Long petId, byte[] pictureData, String fileName) {
        try {
            Pet pet = em.find(Pet.class, petId);
            if (pet != null) {
                pet.setPetPicture(pictureData);
                pet.setPictureFileName(fileName);
                em.merge(pet);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error uploading pet picture: " + e.getMessage(), e);
        }
    }
}
