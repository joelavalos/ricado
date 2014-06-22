/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.ConsentimientoGes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joel
 */
@Local
public interface ConsentimientoGesFacadeLocal {

    void create(ConsentimientoGes consentimientoGes);

    void edit(ConsentimientoGes consentimientoGes);

    void remove(ConsentimientoGes consentimientoGes);

    ConsentimientoGes find(Object id);

    List<ConsentimientoGes> findAll();

    List<ConsentimientoGes> findRange(int[] range);

    int count();
    
}
