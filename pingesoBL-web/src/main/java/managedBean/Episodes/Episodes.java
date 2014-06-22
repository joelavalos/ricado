/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean.Episodes;

import entities.Episodios;
import entities.Paciente;
import entities.RegistroClinico;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.EpisodiosFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PersonaFacadeLocal;
import sessionbeans.RegistroClinicoFacadeLocal;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class Episodes {
    @EJB
    private EpisodiosFacadeLocal episodesFacade;
    @EJB
    private RegistroClinicoFacadeLocal clinicalRecordFacade;
    @EJB
    private PacienteFacadeLocal patientFacade;
    @EJB
    private PersonaFacadeLocal personFacade;

    
    private Integer personId;
    private List<Paciente> searchPatient;
    private List<RegistroClinico> searchClinicalRecord;
    private List<Episodios> searchEpisode;
    
    private String rut;
    private Integer Rut = 6972769;
    private String name;    
    private int episode = 0;
    private Map<String,String> episodes = new HashMap<String,String>();
    
    @PostConstruct
    public void init(){
        rut = "69727697";
        personId = personFacade.findByRut(Rut);
        searchPatient = patientFacade.searchByPerson(personId);
        searchClinicalRecord = clinicalRecordFacade.searchByPaciente(searchPatient.get(0));
        searchEpisode = episodesFacade.searchByClinicalRegister(searchClinicalRecord.get(0));
        name = searchPatient.get(0).getPersona().getPersNombres() +" "+searchPatient.get(0).getPersona().getPersApepaterno() 
                +" "+searchPatient.get(0).getPersona().getPersApematerno();
        
        episodes = new HashMap<String,String>();
        episodes.put("Seleccione", "0");
        for(int i= 0; i<searchEpisode.size(); i++){
            String aux = searchEpisode.get(i).getEpisodioid().toString();
            episodes.put(aux, aux);
        }
    }
    
    public void resetEpisodes(){
        episode = 0;
    }

    public Map<String, String> getEpisodes() {
        return episodes;
    }
    public void setEpisodes(Map<String, String> episodes) {
        this.episodes = episodes;
    }
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getEpisode() {
        return episode;
    }
    public void setEpisode(int episode) {
        this.episode = episode;
    }
    
    
    
   
}