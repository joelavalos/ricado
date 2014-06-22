/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Consulta;
import entities.Episodios;
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
public class ConsultaFacade extends AbstractFacade<Consulta> implements ConsultaFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_pingesoBL-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaFacade() {
        super(Consulta.class);
    }

    @Override
    public List<Consulta> searchByEpisodio(Episodios episodio) {
        Query query;
        query = em.createNamedQuery("Consulta.findByEpisodio").
                setParameter("episodioid", episodio);
        
        return query.getResultList();
    }
    
    
}
