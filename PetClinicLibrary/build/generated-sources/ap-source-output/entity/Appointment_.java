package entity;

import entity.Doctor;
import entity.Pet;
import entity.Staff;
import entity.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-09-14T09:58:32")
@StaticMetamodel(Appointment.class)
public class Appointment_ { 

    public static volatile SingularAttribute<Appointment, String> notification;
    public static volatile SingularAttribute<Appointment, Pet> petId;
    public static volatile SingularAttribute<Appointment, Date> appointmentTime;
    public static volatile SingularAttribute<Appointment, Doctor> doctorId;
    public static volatile SingularAttribute<Appointment, Long> appointmentId;
    public static volatile SingularAttribute<Appointment, Date> appointmentDate;
    public static volatile SingularAttribute<Appointment, Users> userId;
    public static volatile SingularAttribute<Appointment, Staff> staffId;
    public static volatile SingularAttribute<Appointment, String> status;

}