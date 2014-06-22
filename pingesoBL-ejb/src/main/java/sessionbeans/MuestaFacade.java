/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Muesta;
import entities.Paciente;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Joel
 */
@Stateless
public class MuestaFacade extends AbstractFacade<Muesta> implements MuestaFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_pingesoBL-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MuestaFacade() {
        super(Muesta.class);
    }

    @Override
    public List<Muesta> searchByPatientAndDate(Paciente patient, Date fecha) {
        Query query;
        query = em.createNamedQuery("Muesta.findByPacienteFecha").
                setParameter("idPersona", patient).
                setParameter("fecha", fecha);
        
        return query.getResultList();
    }

    @Override
    public List<Muesta> searchByPatient(Paciente patient) {
        Query query;
        query = em.createNamedQuery("Muesta.findByPaciente").
                setParameter("idPersona", patient);
        
        return query.getResultList();
    }

    @Override
    public List<Muesta> searchByPatientGroup(Paciente patient, int group) {
        Query query;
        query = em.createNamedQuery("Muesta.findByPacienteGrupo").
                setParameter("idPersona", patient).
                setParameter("grupo", group);
        
        return query.getResultList();
    }
    
    
}
