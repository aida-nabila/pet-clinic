/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ai
 */
@Entity
@Table(name = "PET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pet.findAll", query = "SELECT p FROM Pet p"),
    @NamedQuery(name = "Pet.findByPetId", query = "SELECT p FROM Pet p WHERE p.petId = :petId"),
    @NamedQuery(name = "Pet.findByPetName", query = "SELECT p FROM Pet p WHERE p.petName = :petName"),
    @NamedQuery(name = "Pet.findByPetSpecies", query = "SELECT p FROM Pet p WHERE p.petSpecies = :petSpecies"),
    @NamedQuery(name = "Pet.findByPetBreed", query = "SELECT p FROM Pet p WHERE p.petBreed = :petBreed"),
    @NamedQuery(name = "Pet.findByPictureFileName", query = "SELECT p FROM Pet p WHERE p.pictureFileName = :pictureFileName")})
public class Pet implements Serializable {

    @Lob
    @Column(name = "PET_PICTURE")
    private byte[] petPicture;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "petId")
    private Collection<Appointment> appointmentCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PET_ID")
    private Long petId;
    @Basic(optional = false)
    @Column(name = "PET_NAME")
    private String petName;
    @Basic(optional = false)
    @Column(name = "PET_SPECIES")
    private String petSpecies;
    @Column(name = "PET_BREED")
    private String petBreed;
    @Column(name = "PICTURE_FILE_NAME")
    private String pictureFileName;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Users userId;

    public Pet() {
    }

    public Pet(Long petId) {
        this.petId = petId;
    }

    public Pet(Long petId, String petName, String petSpecies) {
        this.petId = petId;
        this.petName = petName;
        this.petSpecies = petSpecies;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetSpecies() {
        return petSpecies;
    }

    public void setPetSpecies(String petSpecies) {
        this.petSpecies = petSpecies;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public byte[] getPetPicture() {
        return (byte[]) petPicture;
    }

    public void setPetPicture(byte[] petPicture) {
        this.petPicture = petPicture;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (petId != null ? petId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pet)) {
            return false;
        }
        Pet other = (Pet) object;
        if ((this.petId == null && other.petId != null) || (this.petId != null && !this.petId.equals(other.petId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pet[ petId=" + petId + " ]";
    }

    @XmlTransient
    public Collection<Appointment> getAppointmentCollection() {
        return appointmentCollection;
    }

    public void setAppointmentCollection(Collection<Appointment> appointmentCollection) {
        this.appointmentCollection = appointmentCollection;
    }

}
