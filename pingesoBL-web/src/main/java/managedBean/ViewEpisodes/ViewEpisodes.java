/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean.ViewEpisodes;

import entities.Consulta;
import entities.Episodios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import sessionbeans.ConsultaFacadeLocal;
import sessionbeans.EpisodiosFacadeLocal;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class ViewEpisodes {
    @EJB
    private ConsultaFacadeLocal consultationFacade;
    @EJB
    private EpisodiosFacadeLocal episodesFacade;
    
    private int idEpisode;
    private List<Consulta> consultations;
    private List<Consulta> filterConsultations;
    private Consulta selectedConsultation;
    
   @PostConstruct
    public void init(){
        //consultations = consultationFacade.s;
        /*Date a= new Date();
        Consulta aux1 = new Consulta(1, "paciente grave",a , false, false, "le dolia un pelo");
        Consulta aux2 = new Consulta(1, "paciente grave2",a , false, false, "le dolia un pelo2");
        consultations = new ArrayList<Consulta>();
        consultations.add(aux1);
        consultations.add(aux2);*/
    }

    public void resetView(){
        idEpisode = 0;
        selectedConsultation = null;
        consultations.clear();
    }
    
    public void loadConsultations(int id){
        idEpisode = id;
        Episodios episodeSelected = episodesFacade.find(idEpisode);
        consultations = consultationFacade.searchByEpisodio(episodeSelected);
        System.out.println(consultations);
        RequestContext.getCurrentInstance().execute("viewEpisodesDialog.show()");
    }
    
    public void loadConsultations(){
        Episodios episodeSelected = episodesFacade.find(idEpisode);
        consultations = consultationFacade.searchByEpisodio(episodeSelected);
        System.out.println(consultations);
        RequestContext.getCurrentInstance().execute("viewEpisodesDialog.show()");
    }
    
    public int getIdEpisode() {
        return idEpisode;
    }

    public void setIdEpisode(int idEpisode) {
        this.idEpisode = idEpisode;
    }
    
    public List<Consulta> getFilterConsultations() {
        return filterConsultations;
    }

    public void setFilterConsultations(List<Consulta> filterConsultations) {
        this.filterConsultations = filterConsultations;
    }

    
    public List<Consulta> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consulta> consultations) {
        this.consultations = consultations;
    }

    public Consulta getSelectedConsultation() {
        return selectedConsultation;
    }

    public void setSelectedConsultation(Consulta selectedConsultation) {
        this.selectedConsultation = selectedConsultation;
    }

 
}
