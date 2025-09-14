/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package controller;

import entity.Pet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ai
 */
@Local
public interface PetServiceLocal {

    Pet addPet(Pet pet);

    Pet updatePet(Pet pet);

    boolean deletePet(Long petId);

    Pet getPetById(Long petId);

    List<Pet> getPetsByUser(Long id);

    List<Pet> getAllPets();

    List<Pet> searchPetsByName(String name);

    List<Pet> getPetsBySpecies(String species);

    boolean uploadPetPicture(Long petId, byte[] pictureData, String fileName);

}
