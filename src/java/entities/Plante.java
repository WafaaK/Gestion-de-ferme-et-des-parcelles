/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "plante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plante.findAll", query = "SELECT p FROM Plante p"),
    @NamedQuery(name = "Plante.findById", query = "SELECT p FROM Plante p WHERE p.id = :id"),
    @NamedQuery(name = "Plante.findByImage", query = "SELECT p FROM Plante p WHERE p.image = :image"),
    @NamedQuery(name = "Plante.findByLibelle", query = "SELECT p FROM Plante p WHERE p.libelle = :libelle"),
    @NamedQuery(name = "Plante.findByRacine", query = "SELECT p FROM Plante p WHERE p.racine = :racine")})
public class Plante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @Size(max = 255)
    @Column(name = "libelle")
    private String libelle;
    @Size(max = 255)
    @Column(name = "racine")
    private String racine;
    @JoinColumn(name = "type_plante_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private TypePlante typePlanteId;
    @OneToMany(mappedBy = "planteId", fetch = FetchType.EAGER)
    private List<Plantage> plantageList;

    public Plante() {
    }

    public Plante(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getRacine() {
        return racine;
    }

    public void setRacine(String racine) {
        this.racine = racine;
    }

    public TypePlante getTypePlanteId() {
        return typePlanteId;
    }

    public void setTypePlanteId(TypePlante typePlanteId) {
        this.typePlanteId = typePlanteId;
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
        if (!(object instanceof Plante)) {
            return false;
        }
        Plante other = (Plante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Plante[ id=" + id + " ]";
    }
    
}
