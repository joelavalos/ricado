/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Patologia;
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
public class PatologiaFacade extends AbstractFacade<Patologia> implements PatologiaFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_pingesoBL-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatologiaFacade() {
        super(Patologia.class);
    }
    @Override
    public List<Patologia> searchByNombre(String nombre) {
        Query query;
        query = em.createNamedQuery("Patologia.findByPatologianombre").
                setParameter("patologianombre", nombre);
        
        return query.getResultList();
    }

    @Override
    public List<Patologia> searchById(String id) {
        Query query;
        query = em.createNamedQuery("Patologia.findByPatologiaid").
                setParameter("patologiaid", id);
        
        return query.getResultList();
    }
    
    
}
