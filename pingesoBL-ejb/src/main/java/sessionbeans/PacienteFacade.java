/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Paciente;
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
public class PacienteFacade extends AbstractFacade<Paciente> implements PacienteFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_pingesoBL-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacienteFacade() {
        super(Paciente.class);
    }

    @Override
    public List<Paciente> searchByPerson(int idPersona) {
        Query query;
        query = em.createNamedQuery("Paciente.findByIdPersona").
                setParameter("idPersona", idPersona);
        
        return query.getResultList();
    }
    
    
}
