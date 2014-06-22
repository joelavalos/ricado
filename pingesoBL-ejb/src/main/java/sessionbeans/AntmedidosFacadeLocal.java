/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Antmedidos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joel
 */
@Local
public interface AntmedidosFacadeLocal {

    void create(Antmedidos antmedidos);

    void edit(Antmedidos antmedidos);

    void remove(Antmedidos antmedidos);

    Antmedidos find(Object id);

    List<Antmedidos> findAll();

    List<Antmedidos> findRange(int[] range);

    int count();
    
}
