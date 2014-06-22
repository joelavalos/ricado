/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBeanVitalSigns;

import entities.Muesta;
import entities.Paciente;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sessionbeans.MuestaFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PersonaFacadeLocal;

/**
 *
 * @author Joel
 */
@ManagedBean
@RequestScoped
public class showSamples {
    @EJB
    private PacienteFacadeLocal pacienteFacade;
    @EJB
    private PersonaFacadeLocal personaFacade;
    @EJB
    private MuestaFacadeLocal muestaFacade;
    
    private List<Paciente> searchPaciente;
    private List<Muesta> searchSamples;
    private int samplesId;
    
    
    private Integer PersonId;
    private String PersonRut = "69727697";
    private Integer Rut = 6972769;

    private List<Integer> grupos = new ArrayList<Integer>(); 
    /**
     * Creates a new instance of showSamples
     */
    public showSamples() {
    }
    
    @PostConstruct
    public void init(){
        PersonId = personaFacade.findByRut(Rut);
        searchPaciente = pacienteFacade.searchByPerson(PersonId);
        //Date fecha = new Date(1990, 17, 9);
        searchSamples = muestaFacade.searchByPatient(searchPaciente.get(0));
        boolean existe = false;
        int maxGroup = 0;
        
        for(int i = 0; i<searchSamples.size(); i++){
            for(int j = 0; j<grupos.size(); j++){
                if(grupos.get(j) == searchSamples.get(i).getGrupo()){
                    existe = true;
                }
            }
            if(existe == false){
                grupos.add(searchSamples.get(i).getGrupo());
            }
            existe = false;
            maxGroup = searchSamples.get(i).getGrupo();
        }
        searchSamples = muestaFacade.searchByPatientGroup(searchPaciente.get(0), maxGroup);
    }

    public void showSamples(){
        PersonId = personaFacade.findByRut(Rut);
        searchPaciente = pacienteFacade.searchByPerson(PersonId);
        searchSamples = muestaFacade.searchByPatientGroup(searchPaciente.get(0), samplesId);
        System.out.println("Fecha seleccionada: " + samplesId);
        
    }
    public List<Muesta> getSearchSamples() {
        return searchSamples;
    }

    public void setSearchSamples(List<Muesta> searchSamples) {
        this.searchSamples = searchSamples;
    }

    public int getSamplesId() {
        return samplesId;
    }

    public void setSamplesId(int samplesId) {
        this.samplesId = samplesId;
    }

    public List<Paciente> getSearchPaciente() {
        return searchPaciente;
    }

    public void setSearchPaciente(List<Paciente> searchPaciente) {
        this.searchPaciente = searchPaciente;
    }

    public Integer getPersonId() {
        return PersonId;
    }

    public void setPersonId(Integer PersonId) {
        this.PersonId = PersonId;
    }

    public String getPersonRut() {
        return PersonRut;
    }

    public void setPersonRut(String PersonRut) {
        this.PersonRut = PersonRut;
    }

    public List<Integer> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Integer> grupos) {
        this.grupos = grupos;
    }
    
}
