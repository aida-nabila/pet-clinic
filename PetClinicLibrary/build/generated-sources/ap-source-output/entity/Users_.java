package entity;

import entity.Appointment;
import entity.Pet;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-09-14T09:58:32")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile SingularAttribute<Users, Long> id;
    public static volatile CollectionAttribute<Users, Pet> petCollection;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile CollectionAttribute<Users, Appointment> appointmentCollection;

}