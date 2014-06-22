/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Joel
 */
@Entity
@Table(name = "signos_vitales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SignosVitales.findAll", query = "SELECT s FROM SignosVitales s"),
    @NamedQuery(name = "SignosVitales.findByIdSvitales", query = "SELECT s FROM SignosVitales s WHERE s.idSvitales = :idSvitales"),
    @NamedQuery(name = "SignosVitales.findByNombreSvital", query = "SELECT s FROM SignosVitales s WHERE s.nombreSvital = :nombreSvital"),
    @NamedQuery(name = "SignosVitales.findByRangoMin", query = "SELECT s FROM SignosVitales s WHERE s.rangoMin = :rangoMin"),
    @NamedQuery(name = "SignosVitales.findByRangoMax", query = "SELECT s FROM SignosVitales s WHERE s.rangoMax = :rangoMax")})
public class SignosVitales implements Serializable {
    @Size(max = 10)
    @Column(name = "unidad")
    private String unidad;
    @OneToMany(mappedBy = "idSvitales")
    private Collection<Muesta> muestaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_svitales")
    private Integer idSvitales;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_svital")
    private String nombreSvital;
    @Column(name = "rango_min")
    private Integer rangoMin;
    @Column(name = "rango_max")
    private Integer rangoMax;

    public SignosVitales() {
    }

    public SignosVitales(Integer idSvitales) {
        this.idSvitales = idSvitales;
    }

    public SignosVitales(Integer idSvitales, String nombreSvital) {
        this.idSvitales = idSvitales;
        this.nombreSvital = nombreSvital;
    }

    public Integer getIdSvitales() {
        return idSvitales;
    }

    public void setIdSvitales(Integer idSvitales) {
        this.idSvitales = idSvitales;
    }

    public String getNombreSvital() {
        return nombreSvital;
    }

    public void setNombreSvital(String nombreSvital) {
        this.nombreSvital = nombreSvital;
    }

    public Integer getRangoMin() {
        return rangoMin;
    }

    public void setRangoMin(Integer rangoMin) {
        this.rangoMin = rangoMin;
    }

    public Integer getRangoMax() {
        return rangoMax;
    }

    public void setRangoMax(Integer rangoMax) {
        this.rangoMax = rangoMax;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSvitales != null ? idSvitales.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SignosVitales)) {
            return false;
        }
        SignosVitales other = (SignosVitales) object;
        if ((this.idSvitales == null && other.idSvitales != null) || (this.idSvitales != null && !this.idSvitales.equals(other.idSvitales))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SignosVitales[ idSvitales=" + idSvitales + " ]";
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    @XmlTransient
    public Collection<Muesta> getMuestaCollection() {
        return muestaCollection;
    }

    public void setMuestaCollection(Collection<Muesta> muestaCollection) {
        this.muestaCollection = muestaCollection;
    }
    
}
