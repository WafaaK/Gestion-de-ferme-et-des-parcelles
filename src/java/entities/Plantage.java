/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "plantage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plantage.findAll", query = "SELECT p FROM Plantage p"),
    @NamedQuery(name = "Plantage.findByDate", query = "SELECT p FROM Plantage p WHERE p.plantagePK.date = :date"),
    @NamedQuery(name = "Plantage.findByNombre", query = "SELECT p FROM Plantage p WHERE p.plantagePK.nombre = :nombre"),
    @NamedQuery(name = "Plantage.findByParcelle", query = "SELECT p FROM Plantage p WHERE p.plantagePK.parcelle = :parcelle"),
    @NamedQuery(name = "Plantage.findByPlante", query = "SELECT p FROM Plantage p WHERE p.plantagePK.plante = :plante")})
public class Plantage implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlantagePK plantagePK;
    @JoinColumn(name = "parcelle_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Parcelle parcelleId;
    @JoinColumn(name = "plante_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Plante planteId;

    public Plantage() {
    }

    public Plantage(PlantagePK plantagePK) {
        this.plantagePK = plantagePK;
    }

    public Plantage(String date, int nombre, int parcelle, int plante) {
        this.plantagePK = new PlantagePK(date, nombre, parcelle, plante);
    }

    public PlantagePK getPlantagePK() {
        return plantagePK;
    }

    public void setPlantagePK(PlantagePK plantagePK) {
        this.plantagePK = plantagePK;
    }

    public Parcelle getParcelleId() {
        return parcelleId;
    }

    public void setParcelleId(Parcelle parcelleId) {
        this.parcelleId = parcelleId;
    }

    public Plante getPlanteId() {
        return planteId;
    }

    public void setPlanteId(Plante planteId) {
        this.planteId = planteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plantagePK != null ? plantagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantage)) {
            return false;
        }
        Plantage other = (Plantage) object;
        if ((this.plantagePK == null && other.plantagePK != null) || (this.plantagePK != null && !this.plantagePK.equals(other.plantagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Plantage[ plantagePK=" + plantagePK + " ]";
    }
    
}
