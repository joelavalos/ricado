/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "registro_clinico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroClinico.findAll", query = "SELECT r FROM RegistroClinico r"),
    @NamedQuery(name = "RegistroClinico.findByRegistroclinicoid", query = "SELECT r FROM RegistroClinico r WHERE r.registroclinicoid = :registroclinicoid"),
    @NamedQuery(name = "RegistroClinico.findByRegistrofecha", query = "SELECT r FROM RegistroClinico r WHERE r.registrofecha = :registrofecha"),
    @NamedQuery(name = "RegistroClinico.findByPaciente", query = "SELECT r FROM RegistroClinico r WHERE r.idPersona = :idPersona")})
public class RegistroClinico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "registroclinicoid")
    private Integer registroclinicoid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registrofecha")
    @Temporal(TemporalType.DATE)
    private Date registrofecha;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Paciente idPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registroclinicoid")
    private Collection<Episodios> episodiosCollection;

    public RegistroClinico() {
    }

    public RegistroClinico(Integer registroclinicoid) {
        this.registroclinicoid = registroclinicoid;
    }

    public RegistroClinico(Integer registroclinicoid, Date registrofecha) {
        this.registroclinicoid = registroclinicoid;
        this.registrofecha = registrofecha;
    }

    public Integer getRegistroclinicoid() {
        return registroclinicoid;
    }

    public void setRegistroclinicoid(Integer registroclinicoid) {
        this.registroclinicoid = registroclinicoid;
    }

    public Date getRegistrofecha() {
        return registrofecha;
    }

    public void setRegistrofecha(Date registrofecha) {
        this.registrofecha = registrofecha;
    }

    public Paciente getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Paciente idPersona) {
        this.idPersona = idPersona;
    }

    @XmlTransient
    public Collection<Episodios> getEpisodiosCollection() {
        return episodiosCollection;
    }

    public void setEpisodiosCollection(Collection<Episodios> episodiosCollection) {
        this.episodiosCollection = episodiosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroclinicoid != null ? registroclinicoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroClinico)) {
            return false;
        }
        RegistroClinico other = (RegistroClinico) object;
        if ((this.registroclinicoid == null && other.registroclinicoid != null) || (this.registroclinicoid != null && !this.registroclinicoid.equals(other.registroclinicoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RegistroClinico[ registroclinicoid=" + registroclinicoid + " ]";
    }

}
