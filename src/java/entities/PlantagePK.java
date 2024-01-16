/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author hp
 */
@Embeddable
public class PlantagePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "date")
    private String date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nombre")
    private int nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parcelle")
    private int parcelle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plante")
    private int plante;

    public PlantagePK() {
    }

    public PlantagePK(String date, int nombre, int parcelle, int plante) {
        this.date = date;
        this.nombre = nombre;
        this.parcelle = parcelle;
        this.plante = plante;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getParcelle() {
        return parcelle;
    }

    public void setParcelle(int parcelle) {
        this.parcelle = parcelle;
    }

    public int getPlante() {
        return plante;
    }

    public void setPlante(int plante) {
        this.plante = plante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (date != null ? date.hashCode() : 0);
        hash += (int) nombre;
        hash += (int) parcelle;
        hash += (int) plante;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlantagePK)) {
            return false;
        }
        PlantagePK other = (PlantagePK) object;
        if ((this.date == null && other.date != null) || (this.date != null && !this.date.equals(other.date))) {
            return false;
        }
        if (this.nombre != other.nombre) {
            return false;
        }
        if (this.parcelle != other.parcelle) {
            return false;
        }
        if (this.plante != other.plante) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PlantagePK[ date=" + date + ", nombre=" + nombre + ", parcelle=" + parcelle + ", plante=" + plante + " ]";
    }
    
}
