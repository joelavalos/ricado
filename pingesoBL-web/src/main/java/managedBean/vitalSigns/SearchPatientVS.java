/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean.vitalSigns;

import entities.Persona;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.PersonaFacadeLocal;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class SearchPatientVS {
    @EJB
    private PersonaFacadeLocal personFacade;
    
    
    private List<Persona> person;
    private List<Persona> filterPerson;
    
    
    @PostConstruct
    public void init(){
        person = personFacade.findAll();
        for(int i = 0 ; i< person.size(); i++){
            if(person.get(i).getPersTipopersona()==1){
                person.remove(i);
                i--;
            }
        }
    }

    
    public List<Persona> getPerson() {
        return person;
    }

    public void setPerson(List<Persona> person) {
        this.person = person;
    }



    public List<Persona> getFilterPerson() {
        return filterPerson;
    }

    public void setFilterPerson(List<Persona> filterPerson) {
        this.filterPerson = filterPerson;
    }
    
}
