/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Antecedentes;
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
public class AntecedentesFacade extends AbstractFacade<Antecedentes> implements AntecedentesFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_pingesoBL-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AntecedentesFacade() {
        super(Antecedentes.class);
    }

    @Override
    public List<Antecedentes> searchByName(String nombre) {
        Query query;
        query = em.createNamedQuery("Antecedentes.findByNombreAntecedente").
                setParameter("nombreAntecedente", nombre);
        
        return query.getResultList();
    }
    
    
}
