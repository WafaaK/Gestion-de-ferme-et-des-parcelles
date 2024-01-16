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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "type_plante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypePlante.findAll", query = "SELECT t FROM TypePlante t"),
    @NamedQuery(name = "TypePlante.findById", query = "SELECT t FROM TypePlante t WHERE t.id = :id"),
    @NamedQuery(name = "TypePlante.findByHumiditeMax", query = "SELECT t FROM TypePlante t WHERE t.humiditeMax = :humiditeMax"),
    @NamedQuery(name = "TypePlante.findByHumiditeMin", query = "SELECT t FROM TypePlante t WHERE t.humiditeMin = :humiditeMin"),
    @NamedQuery(name = "TypePlante.findByLuminosite", query = "SELECT t FROM TypePlante t WHERE t.luminosite = :luminosite"),
    @NamedQuery(name = "TypePlante.findByName", query = "SELECT t FROM TypePlante t WHERE t.name = :name"),
    @NamedQuery(name = "TypePlante.findByTemperature", query = "SELECT t FROM TypePlante t WHERE t.temperature = :temperature")})
public class TypePlante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "humidite_max")
    private float humiditeMax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "humidite_min")
    private float humiditeMin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "luminosite")
    private float luminosite;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "temperature")
    private float temperature;
    @OneToMany(mappedBy = "typePlanteId", fetch = FetchType.EAGER)
    private List<Plante> planteList;

    public TypePlante() {
    }

    public TypePlante(Integer id) {
        this.id = id;
    }

    public TypePlante(Integer id, float humiditeMax, float humiditeMin, float luminosite, float temperature) {
        this.id = id;
        this.humiditeMax = humiditeMax;
        this.humiditeMin = humiditeMin;
        this.luminosite = luminosite;
        this.temperature = temperature;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getHumiditeMax() {
        return humiditeMax;
    }

    public void setHumiditeMax(float humiditeMax) {
        this.humiditeMax = humiditeMax;
    }

    public float getHumiditeMin() {
        return humiditeMin;
    }

    public void setHumiditeMin(float humiditeMin) {
        this.humiditeMin = humiditeMin;
    }

    public float getLuminosite() {
        return luminosite;
    }

    public void setLuminosite(float luminosite) {
        this.luminosite = luminosite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    @XmlTransient
    public List<Plante> getPlanteList() {
        return planteList;
    }

    public void setPlanteList(List<Plante> planteList) {
        this.planteList = planteList;
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
        if (!(object instanceof TypePlante)) {
            return false;
        }
        TypePlante other = (TypePlante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TypePlante[ id=" + id + " ]";
    }
    
}
