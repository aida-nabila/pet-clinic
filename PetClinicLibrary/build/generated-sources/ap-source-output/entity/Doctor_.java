package entity;

import entity.Appointment;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-09-13T21:44:27")
@StaticMetamodel(Doctor.class)
public class Doctor_ { 

    public static volatile SingularAttribute<Doctor, String> password;
    public static volatile SingularAttribute<Doctor, String> specialty;
    public static volatile SingularAttribute<Doctor, Long> doctorId;
    public static volatile SingularAttribute<Doctor, String> name;
    public static volatile SingularAttribute<Doctor, String> email;
    public static volatile CollectionAttribute<Doctor, Appointment> appointmentCollection;

}