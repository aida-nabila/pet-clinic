package entity;

import entity.Appointment;
import entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-09-14T09:58:32")
@StaticMetamodel(Pet.class)
public class Pet_ { 

    public static volatile SingularAttribute<Pet, String> petName;
    public static volatile SingularAttribute<Pet, Long> petId;
    public static volatile SingularAttribute<Pet, String> petInfo;
    public static volatile SingularAttribute<Pet, String> petSpecies;
    public static volatile SingularAttribute<Pet, String> petBreed;
    public static volatile SingularAttribute<Pet, byte[]> petPicture;
    public static volatile SingularAttribute<Pet, Users> userId;
    public static volatile CollectionAttribute<Pet, Appointment> appointmentCollection;
    public static volatile SingularAttribute<Pet, String> pictureFileName;

}