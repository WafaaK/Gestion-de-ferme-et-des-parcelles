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
@Table(name = "ferme")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ferme.findAll", query = "SELECT f FROM Ferme f"),
    @NamedQuery(name = "Ferme.findById", query = "SELECT f FROM Ferme f WHERE f.id = :id"),
    @NamedQuery(name = "Ferme.findByPhoto", query = "SELECT f FROM Ferme f WHERE f.photo = :photo"),
    @NamedQuery(name = "Ferme.findByLibelle", query = "SELECT f FROM Ferme f WHERE f.libelle = :libelle")})
public class Ferme implements Serializable {
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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser userId;
    @OneToMany(mappedBy = "fermeId", fetch = FetchType.EAGER)
    private List<Parcelle> parcelleList;

    public Ferme() {
    }

    public Ferme(Long id) {
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

    public AppUser getUserId() {
        return userId;
    }

    public void setUserId(AppUser userId) {
        this.userId = userId;
    }

    @XmlTransient
    public List<Parcelle> getParcelleList() {
        return parcelleList;
    }

    public void setParcelleList(List<Parcelle> parcelleList) {
        this.parcelleList = parcelleList;
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
        if (!(object instanceof Ferme)) {
            return false;
        }
        Ferme other = (Ferme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ferme[ id=" + id + " ]";
    }
    
}
