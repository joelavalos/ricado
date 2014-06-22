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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Joel
 */
@Entity
@Table(name = "consulta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consulta.findAll", query = "SELECT c FROM Consulta c"),
    @NamedQuery(name = "Consulta.findByConsultaid", query = "SELECT c FROM Consulta c WHERE c.consultaid = :consultaid"),
    @NamedQuery(name = "Consulta.findByHdiagnostica", query = "SELECT c FROM Consulta c WHERE c.hdiagnostica = :hdiagnostica"),
    @NamedQuery(name = "Consulta.findByConsultafecha", query = "SELECT c FROM Consulta c WHERE c.consultafecha = :consultafecha"),
    @NamedQuery(name = "Consulta.findByCancelada", query = "SELECT c FROM Consulta c WHERE c.cancelada = :cancelada"),
    @NamedQuery(name = "Consulta.findByMotivocancel", query = "SELECT c FROM Consulta c WHERE c.motivocancel = :motivocancel"),
    @NamedQuery(name = "Consulta.findByPausada", query = "SELECT c FROM Consulta c WHERE c.pausada = :pausada"),
    @NamedQuery(name = "Consulta.findByMotivoConsulta", query = "SELECT c FROM Consulta c WHERE c.motivoConsulta = :motivoConsulta"),
    @NamedQuery(name = "Consulta.findByNotas", query = "SELECT c FROM Consulta c WHERE c.notas = :notas"),
    @NamedQuery(name = "Consulta.findByExploracionFisica", query = "SELECT c FROM Consulta c WHERE c.exploracionFisica = :exploracionFisica"),
    @NamedQuery(name = "Consulta.findByEpisodio", query = "SELECT c FROM Consulta c WHERE c.episodioid = :episodioid")})
public class Consulta implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consultaid")
    private Collection<Diagnostico> diagnosticoCollection;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pertinencia")
    private boolean pertinencia;
    @OneToMany(mappedBy = "consultaid")
    private Collection<ConsentimientoGes> consentimientoGesCollection;
    @OneToMany(mappedBy = "consultaid")
    private Collection<IpdGes> ipdGesCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consultaid")
    private Integer consultaid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "hdiagnostica")
    private String hdiagnostica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consultafecha")
    @Temporal(TemporalType.DATE)
    private Date consultafecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cancelada")
    private boolean cancelada;
    @Size(max = 2147483647)
    @Column(name = "motivocancel")
    private String motivocancel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pausada")
    private boolean pausada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "motivo_consulta")
    private String motivoConsulta;
    @Size(max = 2147483647)
    @Column(name = "notas")
    private String notas;
    @Size(max = 2147483647)
    @Column(name = "exploracion_fisica")
    private String exploracionFisica;
    @JoinColumn(name = "episodioid", referencedColumnName = "episodioid")
    @ManyToOne(optional = false)
    private Episodios episodioid;

    public Consulta() {
    }

    public Consulta(Integer consultaid) {
        this.consultaid = consultaid;
    }

    public Consulta(Integer consultaid, String hdiagnostica, Date consultafecha, boolean cancelada, boolean pausada, String motivoConsulta) {
        this.consultaid = consultaid;
        this.hdiagnostica = hdiagnostica;
        this.consultafecha = consultafecha;
        this.cancelada = cancelada;
        this.pausada = pausada;
        this.motivoConsulta = motivoConsulta;
    }

    public Integer getConsultaid() {
        return consultaid;
    }

    public void setConsultaid(Integer consultaid) {
        this.consultaid = consultaid;
    }

    public String getHdiagnostica() {
        return hdiagnostica;
    }

    public void setHdiagnostica(String hdiagnostica) {
        this.hdiagnostica = hdiagnostica;
    }

    public Date getConsultafecha() {
        return consultafecha;
    }

    public void setConsultafecha(Date consultafecha) {
        this.consultafecha = consultafecha;
    }

    public boolean getCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public String getMotivocancel() {
        return motivocancel;
    }

    public void setMotivocancel(String motivocancel) {
        this.motivocancel = motivocancel;
    }

    public boolean getPausada() {
        return pausada;
    }

    public void setPausada(boolean pausada) {
        this.pausada = pausada;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getExploracionFisica() {
        return exploracionFisica;
    }

    public void setExploracionFisica(String exploracionFisica) {
        this.exploracionFisica = exploracionFisica;
    }

    public Episodios getEpisodioid() {
        return episodioid;
    }

    public void setEpisodioid(Episodios episodioid) {
        this.episodioid = episodioid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consultaid != null ? consultaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consulta)) {
            return false;
        }
        Consulta other = (Consulta) object;
        if ((this.consultaid == null && other.consultaid != null) || (this.consultaid != null && !this.consultaid.equals(other.consultaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Consulta[ consultaid=" + consultaid + " ]";
    }

    @XmlTransient
    public Collection<IpdGes> getIpdGesCollection() {
        return ipdGesCollection;
    }

    public void setIpdGesCollection(Collection<IpdGes> ipdGesCollection) {
        this.ipdGesCollection = ipdGesCollection;
    }

    @XmlTransient
    public Collection<ConsentimientoGes> getConsentimientoGesCollection() {
        return consentimientoGesCollection;
    }

    public void setConsentimientoGesCollection(Collection<ConsentimientoGes> consentimientoGesCollection) {
        this.consentimientoGesCollection = consentimientoGesCollection;
    }

    public boolean getPertinencia() {
        return pertinencia;
    }

    public void setPertinencia(boolean pertinencia) {
        this.pertinencia = pertinencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Diagnostico> getDiagnosticoCollection() {
        return diagnosticoCollection;
    }

    public void setDiagnosticoCollection(Collection<Diagnostico> diagnosticoCollection) {
        this.diagnosticoCollection = diagnosticoCollection;
    }
    
}
