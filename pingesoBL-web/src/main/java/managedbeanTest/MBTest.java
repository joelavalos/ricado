/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeanTest;

import entities.Consulta;
import entities.Episodios;
import entities.Paciente;
import entities.RegistroClinico;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.ConsultaFacadeLocal;
import sessionbeans.EpisodiosFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PersonaFacadeLocal;
import sessionbeans.RegistroClinicoFacadeLocal;

/**
 *
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class MBTest {
    @EJB
    private ConsultaFacadeLocal consultaFacade;
    @EJB
    private EpisodiosFacadeLocal episodiosFacade;
    @EJB
    private RegistroClinicoFacadeLocal registroClinicoFacade;
    @EJB
    private PacienteFacadeLocal pacienteFacade;
    @EJB
    private PersonaFacadeLocal personaFacade;
    
    private List<Paciente> searchPaciente;
    private List<RegistroClinico> searchRegistroClinico;
    private List<Episodios> searchEpisode;
    private List<Consulta> searchConsultas;

    private Integer PersonId;
    private String PersonRut = "69727697";
    private Integer Rut = 6972769;
    /**
     * Creates a new instance of MBTest
     */
    public MBTest() {
    }
    
    public void searchPerson(){
        PersonId = personaFacade.findByRut(Rut);
        System.out.println("Id del paciente: " + PersonId);
        searchPaciente = pacienteFacade.searchByPerson(PersonId);
        System.out.println("Paciente estado: " + searchPaciente.get(0).getPaciFallecido());
        searchRegistroClinico = registroClinicoFacade.searchByPaciente(searchPaciente.get(0));
        System.out.println("Id registro clinico: " + searchRegistroClinico.get(0).getRegistroclinicoid());
        searchEpisode = episodiosFacade.searchByClinicalRegister(searchRegistroClinico.get(0));
        System.out.println("Id del episodio: " + searchEpisode.get(0).getEpisodioid());
        searchConsultas = consultaFacade.searchByEpisodio(searchEpisode.get(0));
        System.out.println("Hay un total de :" + searchConsultas.size() + " consultas");
        
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

    public List<Paciente> getSearchPaciente() {
        return searchPaciente;
    }

    public void setSearchPaciente(List<Paciente> searchPaciente) {
        this.searchPaciente = searchPaciente;
    }

    public List<RegistroClinico> getSearchRegistroClinico() {
        return searchRegistroClinico;
    }

    public void setSearchRegistroClinico(List<RegistroClinico> searchRegistroClinico) {
        this.searchRegistroClinico = searchRegistroClinico;
    }

    public List<Episodios> getSearchEpisode() {
        return searchEpisode;
    }

    public void setSearchEpisode(List<Episodios> searchEpisode) {
        this.searchEpisode = searchEpisode;
    }

    public List<Consulta> getSearchConsultas() {
        return searchConsultas;
    }

    public void setSearchConsultas(List<Consulta> searchConsultas) {
        this.searchConsultas = searchConsultas;
    }
    
}
