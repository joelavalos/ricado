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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByIdPersona", query = "SELECT p FROM Paciente p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "Paciente.findByPaciFfallecimiento", query = "SELECT p FROM Paciente p WHERE p.paciFfallecimiento = :paciFfallecimiento"),
    @NamedQuery(name = "Paciente.findByPaciFallecido", query = "SELECT p FROM Paciente p WHERE p.paciFallecido = :paciFallecido"),
    @NamedQuery(name = "Paciente.findByPaciOtraprevision", query = "SELECT p FROM Paciente p WHERE p.paciOtraprevision = :paciOtraprevision")})
public class Paciente implements Serializable {
    @Column(name = "paci_fallecido")
    private Boolean paciFallecido;
    @OneToMany(mappedBy = "idPersona")
    private Collection<Muesta> muestaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_persona")
    private Integer idPersona;
    @Column(name = "paci_ffallecimiento")
    @Temporal(TemporalType.DATE)
    private Date paciFfallecimiento;
    @Size(max = 50)
    @Column(name = "paci_otraprevision")
    private String paciOtraprevision;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private Collection<RegistroClinico> registroClinicoCollection;

    public Paciente() {
    }

    public Paciente(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Paciente(Integer idPersona, boolean paciFallecido) {
        this.idPersona = idPersona;
        this.paciFallecido = paciFallecido;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Date getPaciFfallecimiento() {
        return paciFfallecimiento;
    }

    public void setPaciFfallecimiento(Date paciFfallecimiento) {
        this.paciFfallecimiento = paciFfallecimiento;
    }

    public boolean getPaciFallecido() {
        return paciFallecido;
    }

    public void setPaciFallecido(boolean paciFallecido) {
        this.paciFallecido = paciFallecido;
    }

    public String getPaciOtraprevision() {
        return paciOtraprevision;
    }

    public void setPaciOtraprevision(String paciOtraprevision) {
        this.paciOtraprevision = paciOtraprevision;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @XmlTransient
    public Collection<RegistroClinico> getRegistroClinicoCollection() {
        return registroClinicoCollection;
    }

    public void setRegistroClinicoCollection(Collection<RegistroClinico> registroClinicoCollection) {
        this.registroClinicoCollection = registroClinicoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Paciente[ idPersona=" + idPersona + " ]";
    }

    @XmlTransient
    public Collection<Muesta> getMuestaCollection() {
        return muestaCollection;
    }

    public void setMuestaCollection(Collection<Muesta> muestaCollection) {
        this.muestaCollection = muestaCollection;
    }
}
