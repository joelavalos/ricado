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
@Table(name = "muesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muesta.findAll", query = "SELECT m FROM Muesta m"),
    @NamedQuery(name = "Muesta.findByIdMuestra", query = "SELECT m FROM Muesta m WHERE m.idMuestra = :idMuestra"),
    @NamedQuery(name = "Muesta.findByFecha", query = "SELECT m FROM Muesta m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Muesta.findByValor", query = "SELECT m FROM Muesta m WHERE m.valor = :valor"),
    @NamedQuery(name = "Muesta.findByPacienteFecha", query = "SELECT m FROM Muesta m WHERE m.idPersona = :idPersona and m.fecha = :fecha"),
    @NamedQuery(name = "Muesta.findByPaciente", query = "SELECT m FROM Muesta m WHERE m.idPersona = :idPersona"),
    @NamedQuery(name = "Muesta.findByPacienteGrupo", query = "SELECT m FROM Muesta m WHERE m.idPersona = :idPersona and m.grupo = :grupo")})
public class Muesta implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "grupo")
    private int grupo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_muestra")
    private Integer idMuestra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private int valor;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Paciente idPersona;
    @JoinColumn(name = "id_svitales", referencedColumnName = "id_svitales")
    @ManyToOne
    private SignosVitales idSvitales;

    public Muesta() {
    }

    public Muesta(Integer idMuestra) {
        this.idMuestra = idMuestra;
    }

    public Muesta(Integer idMuestra, Date fecha, int valor) {
        this.idMuestra = idMuestra;
        this.fecha = fecha;
        this.valor = valor;
    }

    public Integer getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(Integer idMuestra) {
        this.idMuestra = idMuestra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Paciente getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Paciente idPersona) {
        this.idPersona = idPersona;
    }

    public SignosVitales getIdSvitales() {
        return idSvitales;
    }

    public void setIdSvitales(SignosVitales idSvitales) {
        this.idSvitales = idSvitales;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMuestra != null ? idMuestra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Muesta)) {
            return false;
        }
        Muesta other = (Muesta) object;
        if ((this.idMuestra == null && other.idMuestra != null) || (this.idMuestra != null && !this.idMuestra.equals(other.idMuestra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Muesta[ idMuestra=" + idMuestra + " ]";
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

}
