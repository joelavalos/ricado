/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "consentimiento_ges")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsentimientoGes.findAll", query = "SELECT c FROM ConsentimientoGes c"),
    @NamedQuery(name = "ConsentimientoGes.findByIdCges", query = "SELECT c FROM ConsentimientoGes c WHERE c.idCges = :idCges"),
    @NamedQuery(name = "ConsentimientoGes.findByConsentimientofecha", query = "SELECT c FROM ConsentimientoGes c WHERE c.consentimientofecha = :consentimientofecha"),
    @NamedQuery(name = "ConsentimientoGes.findByConfirmacionges", query = "SELECT c FROM ConsentimientoGes c WHERE c.confirmacionges = :confirmacionges"),
    @NamedQuery(name = "ConsentimientoGes.findByConfirmaciontratamiento", query = "SELECT c FROM ConsentimientoGes c WHERE c.confirmaciontratamiento = :confirmaciontratamiento")})
public class ConsentimientoGes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cges")
    private Integer idCges;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consentimientofecha")
    @Temporal(TemporalType.DATE)
    private Date consentimientofecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "confirmacionges")
    private boolean confirmacionges;
    @Basic(optional = false)
    @NotNull
    @Column(name = "confirmaciontratamiento")
    private boolean confirmaciontratamiento;
    @JoinColumn(name = "consultaid", referencedColumnName = "consultaid")
    @ManyToOne
    private Consulta consultaid;

    public ConsentimientoGes() {
    }

    public ConsentimientoGes(Integer idCges) {
        this.idCges = idCges;
    }

    public ConsentimientoGes(Integer idCges, Date consentimientofecha, boolean confirmacionges, boolean confirmaciontratamiento) {
        this.idCges = idCges;
        this.consentimientofecha = consentimientofecha;
        this.confirmacionges = confirmacionges;
        this.confirmaciontratamiento = confirmaciontratamiento;
    }

    public Integer getIdCges() {
        return idCges;
    }

    public void setIdCges(Integer idCges) {
        this.idCges = idCges;
    }

    public Date getConsentimientofecha() {
        return consentimientofecha;
    }

    public void setConsentimientofecha(Date consentimientofecha) {
        this.consentimientofecha = consentimientofecha;
    }

    public boolean getConfirmacionges() {
        return confirmacionges;
    }

    public void setConfirmacionges(boolean confirmacionges) {
        this.confirmacionges = confirmacionges;
    }

    public boolean getConfirmaciontratamiento() {
        return confirmaciontratamiento;
    }

    public void setConfirmaciontratamiento(boolean confirmaciontratamiento) {
        this.confirmaciontratamiento = confirmaciontratamiento;
    }

    public Consulta getConsultaid() {
        return consultaid;
    }

    public void setConsultaid(Consulta consultaid) {
        this.consultaid = consultaid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCges != null ? idCges.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsentimientoGes)) {
            return false;
        }
        ConsentimientoGes other = (ConsentimientoGes) object;
        if ((this.idCges == null && other.idCges != null) || (this.idCges != null && !this.idCges.equals(other.idCges))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ConsentimientoGes[ idCges=" + idCges + " ]";
    }
    
}
