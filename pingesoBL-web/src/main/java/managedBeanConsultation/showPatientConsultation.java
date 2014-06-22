/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeanConsultation;

import entities.Consulta;
import entities.Diagnostico;
import entities.Episodios;
import entities.Paciente;
import entities.Patologia;
import entities.RegistroClinico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.ConsultaFacadeLocal;
import sessionbeans.DiagnosticoFacadeLocal;
import sessionbeans.EpisodiosFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PatologiaFacadeLocal;
import sessionbeans.PersonaFacadeLocal;
import sessionbeans.RegistroClinicoFacadeLocal;

/**
 *
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class showPatientConsultation {

    @EJB
    private DiagnosticoFacadeLocal diagnosticoFacade;
    @EJB
    private PatologiaFacadeLocal patologiaFacade;
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
    private List<Patologia> searchPathology;

    private Integer PersonId;
    private String PersonRut = "69727697";
    private Integer Rut = 6972769;
    
    private String Hdiagnostica;
    private Date date;
    private boolean cancel;
    private String cancelReason;
    private boolean pause;
    private String consultationReason;
    private String notes;
    private String physicalExamination;

    private String pathologyId;
    private List<Patologia> selectedPathology;
    private boolean gesDiagnoses;
    private Date dateDiagnoses;
    private String stateDiagnoses;

    //PARTE NUEVA PARA LOS DIAGNOSTICOS DE TODAS LAS CONSULTAS DEL EPISODIO
    private List<Diagnostico> allDiagnosesConsultation = new ArrayList<Diagnostico>();
    //PARTE NUEVA PARA LOS DIAGNOSTICOS DE TODAS LAS CONSULTAS DEL EPISODIO

    private List<Diagnostico> allDiagnoses;

    /**
     * Creates a new instance of showPatientConsultation
     */
    @PostConstruct
    public void init() {
        PersonId = personaFacade.findByRut(Rut);
        searchPaciente = pacienteFacade.searchByPerson(PersonId);
        searchRegistroClinico = registroClinicoFacade.searchByPaciente(searchPaciente.get(0));
        searchEpisode = episodiosFacade.searchByClinicalRegister(searchRegistroClinico.get(0));

        searchConsultas = consultaFacade.searchByEpisodio(searchEpisode.get(0));
        
        //PARTE NUEVA PARA LOS DIAGNOSTICOS DE TODAS LAS CONSULTAS DEL EPISODIO
        for (int j = 0; j < searchConsultas.size(); j++) {
            allDiagnoses = diagnosticoFacade.searchByConsultation(searchConsultas.get(j));
            for (int k = 0; k < allDiagnoses.size(); k++) {
                allDiagnosesConsultation.add(allDiagnoses.get(k));
            }
        }
        //PARTE NUEVA PARA LOS DIAGNOSTICOS DE TODAS LAS CONSULTAS DEL EPISODIO
        searchPathology = patologiaFacade.findAll();
       

    }

    public showPatientConsultation() {
    }

    public void showConsultation() {
        PersonId = personaFacade.findByRut(Rut);
        //System.out.println("Id del paciente: " + PersonId);
        searchPaciente = pacienteFacade.searchByPerson(PersonId);
        //System.out.println("Paciente estado: " + searchPaciente.get(0).getPaciFallecido());
        searchRegistroClinico = registroClinicoFacade.searchByPaciente(searchPaciente.get(0));
        //System.out.println("Id registro clinico: " + searchRegistroClinico.get(0).getRegistroclinicoid());
        searchEpisode = episodiosFacade.searchByClinicalRegister(searchRegistroClinico.get(0));
        //System.out.println("Id del episodio: " + searchEpisode.get(0).getEpisodioid());
        searchConsultas = consultaFacade.searchByEpisodio(searchEpisode.get(0));
        //System.out.println("Hay un total de :" + searchConsultas.size() + " consultas");      
    }

    public void createConsultation() {
        PersonId = personaFacade.findByRut(Rut);
        //System.out.println("Id del paciente: " + PersonId);
        searchPaciente = pacienteFacade.searchByPerson(PersonId);
        //System.out.println("Paciente estado: " + searchPaciente.get(0).getPaciFallecido());
        searchRegistroClinico = registroClinicoFacade.searchByPaciente(searchPaciente.get(0));
        //System.out.println("Id registro clinico: " + searchRegistroClinico.get(0).getRegistroclinicoid());
        searchEpisode = episodiosFacade.searchByClinicalRegister(searchRegistroClinico.get(0));
        //System.out.println("Id del episodio: " + searchEpisode.get(0).getEpisodioid());
        searchConsultas = consultaFacade.searchByEpisodio(searchEpisode.get(0));
        System.out.println("Hay un total de :" + searchConsultas.size() + " consultas");

        date = new Date();
        cancel = false;
        pause = false;

        Consulta newConsultation = new Consulta(null);
        newConsultation.setEpisodioid(searchEpisode.get(0));
        newConsultation.setHdiagnostica(Hdiagnostica);
        newConsultation.setConsultafecha(date);
        newConsultation.setCancelada(cancel);
        newConsultation.setMotivocancel(cancelReason);
        newConsultation.setPausada(pause);
        newConsultation.setMotivoConsulta(consultationReason);
        newConsultation.setNotas(notes);
        newConsultation.setExploracionFisica(physicalExamination);

        consultaFacade.create(newConsultation);
        searchConsultas = consultaFacade.searchByEpisodio(searchEpisode.get(0));
        System.out.println("Se ha creado la consulta");

        //Variables para crear el diagnostico, se requiere que se cree la consulta antes
        selectedPathology = patologiaFacade.searchById(pathologyId);
        dateDiagnoses = new Date();
        gesDiagnoses = false;
        stateDiagnoses = "confirmado";

        Diagnostico newDiagnostico = new Diagnostico(null);
        newDiagnostico.setPatologiaid(selectedPathology.get(0));
        newDiagnostico.setConsultaid(newConsultation);
        newDiagnostico.setDiagnosticofecha(dateDiagnoses);
        newDiagnostico.setDiagnosticoges(gesDiagnoses);
        newDiagnostico.setDiagnosticoestado(stateDiagnoses);

        diagnosticoFacade.create(newDiagnostico);

        
        //PARTE NUEVA PARA LOS DIAGNOSTICOS DE TODAS LAS CONSULTAS DEL EPISODIO
        allDiagnosesConsultation.clear();
        for (int j = 0; j < searchConsultas.size(); j++) {
            allDiagnoses = diagnosticoFacade.searchByConsultation(searchConsultas.get(j));
            for (int k = 0; k < allDiagnoses.size(); k++) {
                allDiagnosesConsultation.add(allDiagnoses.get(k));
            }
        }
        //PARTE NUEVA PARA LOS DIAGNOSTICOS DE TODAS LAS CONSULTAS DEL EPISODIO

    }

    public RegistroClinicoFacadeLocal getRegistroClinicoFacade() {
        return registroClinicoFacade;
    }

    public void setRegistroClinicoFacade(RegistroClinicoFacadeLocal registroClinicoFacade) {
        this.registroClinicoFacade = registroClinicoFacade;
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

    public String getHdiagnostica() {
        return Hdiagnostica;
    }

    public void setHdiagnostica(String Hdiagnostica) {
        this.Hdiagnostica = Hdiagnostica;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public String getConsultationReason() {
        return consultationReason;
    }

    public void setConsultationReason(String consultationReason) {
        this.consultationReason = consultationReason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhysicalExamination() {
        return physicalExamination;
    }

    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination;
    }

    public List<Patologia> getSearchPathology() {
        return searchPathology;
    }

    public void setSearchPathology(List<Patologia> searchPathology) {
        this.searchPathology = searchPathology;
    }

    public String getPathologyId() {
        return pathologyId;
    }

    public void setPathologyId(String pathologyId) {
        this.pathologyId = pathologyId;
    }

    public List<Patologia> getSelectedPathology() {
        return selectedPathology;
    }

    public void setSelectedPathology(List<Patologia> selectedPathology) {
        this.selectedPathology = selectedPathology;
    }

    public boolean isGesDiagnoses() {
        return gesDiagnoses;
    }

    public void setGesDiagnoses(boolean gesDiagnoses) {
        this.gesDiagnoses = gesDiagnoses;
    }

    public Date getDateDiagnoses() {
        return dateDiagnoses;
    }

    public void setDateDiagnoses(Date dateDiagnoses) {
        this.dateDiagnoses = dateDiagnoses;
    }

    public String getStateDiagnoses() {
        return stateDiagnoses;
    }

    public void setStateDiagnoses(String stateDiagnoses) {
        this.stateDiagnoses = stateDiagnoses;
    }

    public List<Diagnostico> getAllDiagnoses() {
        return allDiagnoses;
    }

    public void setAllDiagnoses(List<Diagnostico> allDiagnoses) {
        this.allDiagnoses = allDiagnoses;
    }

    public List<Diagnostico> getAllDiagnosesConsultation() {
        return allDiagnosesConsultation;
    }

    public void setAllDiagnosesConsultation(List<Diagnostico> allDiagnosesConsultation) {
        this.allDiagnosesConsultation = allDiagnosesConsultation;
    }

}
