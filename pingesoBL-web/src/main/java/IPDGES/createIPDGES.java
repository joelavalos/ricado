/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IPDGES;

import entities.Consulta;
import entities.Episodios;
import entities.IpdGes;
import entities.Paciente;
import entities.Patologia;
import entities.RegistroClinico;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import sessionbeans.ConsultaFacadeLocal;
import sessionbeans.EpisodiosFacadeLocal;
import sessionbeans.IpdGesFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PersonaFacadeLocal;
import sessionbeans.RegistroClinicoFacadeLocal;

/**
 *
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class createIPDGES {

    @EJB
    private IpdGesFacadeLocal ipdGesFacade;
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

    private String augeProblem;
    private String augeSubProblem;
    private String diagnosis;
    private String fundamentDiagnosis;
    private String treatment;
    private boolean confirmsGES;
    private Date deadline;

    /**
     * Creates a new instance of createIPDGES
     */
    public createIPDGES() {
    }

    public void createIPDGES() {
        if (hayProblema() && haySubProblema() && hayDiagnostico() && hayFundamento() && hayTratamiento() && hayFechaLimite()) {
            Date today = new Date();
            if(deadline.after(today)){
                PersonId = personaFacade.findByRut(Rut);
                searchPaciente = pacienteFacade.searchByPerson(PersonId);
                searchRegistroClinico = registroClinicoFacade.searchByPaciente(searchPaciente.get(0));
                searchEpisode = episodiosFacade.searchByClinicalRegister(searchRegistroClinico.get(0));

                searchConsultas = consultaFacade.searchByEpisodio(searchEpisode.get(0));

                IpdGes newIPDGES = new IpdGes(null);
                newIPDGES.setConsultaid(searchConsultas.get(0));
                newIPDGES.setProblemaauge(augeProblem);
                newIPDGES.setSubproblemaauge(augeSubProblem);
                newIPDGES.setDiagnostico(diagnosis);
                newIPDGES.setFundamentodiagnostico(fundamentDiagnosis);
                newIPDGES.setTratamientoind(treatment);
                newIPDGES.setConfirmages(confirmsGES);
                newIPDGES.setFechalimite(deadline);

                ipdGesFacade.create(newIPDGES);

                resetIPD();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Formulario IPD agregado", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
                RequestContext.getCurrentInstance().execute("newIPDDialog.hide()");
            }else{
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar una fecha posterior a hoy", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            }
        } else {
            if (!hayProblema()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar un problema", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            }
            if (!haySubProblema()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar un subproblema", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            }
            if (!hayDiagnostico()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar un diagnostico", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            }
            if (!hayFundamento()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar un fundamento diagnostico", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            }
            if (!hayTratamiento()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar un tratamiento", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            }
            if (!hayFechaLimite()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar una fecha limite", "");
                FacesContext.getCurrentInstance().addMessage("", fm);
            }
        }
    }
    
    public void resetIPD(){
        augeProblem = "";
        augeSubProblem = "";
        diagnosis = "";
        fundamentDiagnosis = "";
        treatment = "";
        confirmsGES = false;
        deadline = null;
    }

    private boolean hayProblema() {
        return !augeProblem.equals("");
    }

    private boolean haySubProblema() {
        return !augeSubProblem.equals("");
    }

    private boolean hayDiagnostico() {
        return !diagnosis.equals("");
    }

    private boolean hayFundamento() {
        return !fundamentDiagnosis.equals("");
    }

    private boolean hayTratamiento() {
        return !treatment.equals("");
    }

    private boolean hayFechaLimite() {
        return deadline != null;
    }

    public String getAugeProblem() {
        return augeProblem;
    }

    public void setAugeProblem(String augeProblem) {
        this.augeProblem = augeProblem;
    }

    public String getAugeSubProblem() {
        return augeSubProblem;
    }

    public void setAugeSubProblem(String augeSubProblem) {
        this.augeSubProblem = augeSubProblem;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getFundamentDiagnosis() {
        return fundamentDiagnosis;
    }

    public void setFundamentDiagnosis(String fundamentDiagnosis) {
        this.fundamentDiagnosis = fundamentDiagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public boolean isConfirmsGES() {
        return confirmsGES;
    }

    public void setConfirmsGES(boolean confirmsGES) {
        this.confirmsGES = confirmsGES;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

}
