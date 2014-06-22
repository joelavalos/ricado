/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean.pathology;

import entities.Patologia;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.PatologiaFacadeLocal;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class ListPathology {
    @EJB
    private PatologiaFacadeLocal pathologyFacade;
    
    private List<Patologia> pathologies;
    private List<Patologia> filterPathologies;
    private Patologia selectedPathology;
    
    
   @PostConstruct
   public void init(){
       pathologies = pathologyFacade.findAll();
   }

    public List<Patologia> getPathologies() {
        return pathologies;
    }
    public void setPathologies(List<Patologia> pathologies) {
        this.pathologies = pathologies;
    }
    public List<Patologia> getFilterPathologies() {
        return filterPathologies;
    }
    public void setFilterPathologies(List<Patologia> filterPathologies) {
        this.filterPathologies = filterPathologies;
    }
    public Patologia getSelectedPathology() {
        return selectedPathology;
    }
    public void setSelectedPathology(Patologia selectedPathology) {
        this.selectedPathology = selectedPathology;
    }
    
    

   
   
    
}
