/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "parcelle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parcelle.findAll", query = "SELECT p FROM Parcelle p"),
    @NamedQuery(name = "Parcelle.findById", query = "SELECT p FROM Parcelle p WHERE p.id = :id"),
    @NamedQuery(name = "Parcelle.findByPhoto", query = "SELECT p FROM Parcelle p WHERE p.photo = :photo"),
    @NamedQuery(name = "Parcelle.findByLibelle", query = "SELECT p FROM Parcelle p WHERE p.libelle = :libelle"),
    @NamedQuery(name = "Parcelle.findBySuperficie", query = "SELECT p FROM Parcelle p WHERE p.superficie = :superficie")})
public class Parcelle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "photo")
    private String photo;
    @Size(max = 255)
    @Column(name = "libelle")
    private String libelle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "superficie")
    private Float superficie;
    @JoinColumn(name = "ferme_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Ferme fermeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parcelleId", fetch = FetchType.EAGER)
    private List<Plantage> plantageList;

    public Parcelle() {
    }

    public Parcelle(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Float superficie) {
        this.superficie = superficie;
    }

    public Ferme getFermeId() {
        return fermeId;
    }

    public void setFermeId(Ferme fermeId) {
        this.fermeId = fermeId;
    }

    @XmlTransient
    public List<Plantage> getPlantageList() {
        return plantageList;
    }

    public void setPlantageList(List<Plantage> plantageList) {
        this.plantageList = plantageList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parcelle)) {
            return false;
        }
        Parcelle other = (Parcelle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Parcelle[ id=" + id + " ]";
    }
    
}
