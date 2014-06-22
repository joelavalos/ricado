/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Consulta;
import entities.Diagnostico;
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
public class DiagnosticoFacade extends AbstractFacade<Diagnostico> implements DiagnosticoFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_pingesoBL-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagnosticoFacade() {
        super(Diagnostico.class);
    }

    @Override
    public List<Diagnostico> searchByConsultation(Consulta consulta) {
        Query query;
        query = em.createNamedQuery("Diagnostico.findByConsulta").
                setParameter("consultaid", consulta);
        
        return query.getResultList();
    }
    
    
}
