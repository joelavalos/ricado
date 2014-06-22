/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "antecedentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Antecedentes.findAll", query = "SELECT a FROM Antecedentes a"),
    @NamedQuery(name = "Antecedentes.findByIdAntecedente", query = "SELECT a FROM Antecedentes a WHERE a.idAntecedente = :idAntecedente"),
    @NamedQuery(name = "Antecedentes.findByNombreAntecedente", query = "SELECT a FROM Antecedentes a WHERE a.nombreAntecedente = :nombreAntecedente")})
public class Antecedentes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_antecedente")
    private Integer idAntecedente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_antecedente")
    private String nombreAntecedente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAntecedente")
    private Collection<Antmedidos> antmedidosCollection;

    public Antecedentes() {
    }

    public Antecedentes(Integer idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    public Antecedentes(Integer idAntecedente, String nombreAntecedente) {
        this.idAntecedente = idAntecedente;
        this.nombreAntecedente = nombreAntecedente;
    }

    public Integer getIdAntecedente() {
        return idAntecedente;
    }

    public void setIdAntecedente(Integer idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    public String getNombreAntecedente() {
        return nombreAntecedente;
    }

    public void setNombreAntecedente(String nombreAntecedente) {
        this.nombreAntecedente = nombreAntecedente;
    }

    @XmlTransient
    public Collection<Antmedidos> getAntmedidosCollection() {
        return antmedidosCollection;
    }

    public void setAntmedidosCollection(Collection<Antmedidos> antmedidosCollection) {
        this.antmedidosCollection = antmedidosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAntecedente != null ? idAntecedente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Antecedentes)) {
            return false;
        }
        Antecedentes other = (Antecedentes) object;
        if ((this.idAntecedente == null && other.idAntecedente != null) || (this.idAntecedente != null && !this.idAntecedente.equals(other.idAntecedente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Antecedentes[ idAntecedente=" + idAntecedente + " ]";
    }
    
}
