/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean.perinatalHistory;

import entities.Antecedentes;
import entities.Antmedidos;
import entities.Episodios;
import entities.Paciente;
import entities.RegistroClinico;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.AntecedentesFacadeLocal;
import sessionbeans.AntmedidosFacadeLocal;
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
public class PerinatalHistory {

    @EJB
    private AntmedidosFacadeLocal antmedidosFacade;
    @EJB
    private EpisodiosFacadeLocal episodeFacade;
    @EJB
    private RegistroClinicoFacadeLocal clinicalRecordFacade;
    @EJB
    private PacienteFacadeLocal patientFacade;
    @EJB
    private PersonaFacadeLocal personFacade;
    @EJB
    private AntecedentesFacadeLocal antecedentesFacade;

    private int personId;
    private Integer Rut = 6972769;
    private String name;
    private List<Paciente> searchPatient;
    private List<RegistroClinico> searchClinicalRecord;
    private List<Episodios> searchEpisode;
    private Paciente patient;

    private List<Antmedidos> listAntMedidos = new ArrayList<Antmedidos>();
    private List<Antecedentes> searchAntecedente;
    private Antmedidos newAntmedido;

    String[] familyHistory;
    String[] personalHistory;
    String reasonAbortion = "";
    String[] bornCheck;
    int deeds = -1;
    int abortions = -1;
    int births = -1;
    int born = -1;
    int stillbirths = -1;
    int living = -1;
    int deadFirstWeek = -1;
    int deadSecondWeek = -1;
    DateFormat dfDateInstance = DateFormat.getDateInstance();
    Date lastPregnancy = null;
    int RNHeavier = -1;

    //embarazo actual
    int currentWeight = -1;
    int usualWeight = -1;
    int size = -1;
    Date FUR = null;
    Date FURO = null;
    String[] estimated;
    String doubts = "";
    
    Date FPBirth = null;
    int gestationalAge = -1;
    int numberDays = -1;
    String doubtsHEA = "";
    String reason = "";
    String blood = "";
    String bloodType = "";
    String sensitized = "";

    String examinationCN = "";
    String examinationMN = "";
    String examinationON = "";
    String normalPelvis = "";
    String normalPapanic = "";
    String normalCervix = "";
    String VIH = "";

    Date VDRL = null;
    String VDRLOption = "";

    String[] HCTOCheck;
    double HTCTOFloat = -0.1;
    Date HCTODate = null;

    String smoker = "";
    int cantCigars = -1;

    public void save() {
        Date fecha = new Date();
        listAntMedidos.clear();

        personId = personFacade.findByRut(Rut);
        searchPatient = patientFacade.searchByPerson(personId);
        patient = searchPatient.get(0);
        searchClinicalRecord = clinicalRecordFacade.searchByPaciente(searchPatient.get(0));
        searchEpisode = episodeFacade.searchByClinicalRegister(searchClinicalRecord.get(0));

        for (int i = 0; i < familyHistory.length; i++) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName(familyHistory[i]);

            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor("Seleccionado");
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);

        }

        for (int i = 0; i < personalHistory.length; i++) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName(personalHistory[i]);

            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor("Seleccionado");
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (deeds > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Gestas");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(deeds));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (abortions > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Abortos");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(abortions));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (births > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Partos");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(births));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (reasonAbortion.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Abortos razón");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(reasonAbortion);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        for (int i = 0; i < bornCheck.length; i++) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName(bornCheck[i]);

            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor("Seleccionado");
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (born > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Nacidos vivos");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(born));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (stillbirths > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Nacidos Muertos");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(stillbirths));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (living > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Vivos");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(living));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (deadFirstWeek > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Muertos 1° semana");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(deadFirstWeek));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (deadSecondWeek > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Muertos 2° a 4° semana");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(deadSecondWeek));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (lastPregnancy != null) {
            String dateFOrmat = dfDateInstance.format(lastPregnancy);
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Término último embarazo");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(dateFOrmat);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        if (RNHeavier > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("RN con mayor peso");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(RNHeavier));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (currentWeight > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Peso Actual");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(currentWeight));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (usualWeight > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Peso habitual");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(usualWeight));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (size > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Talla");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(size));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (FUR != null) {
            String dateFOrmat = dfDateInstance.format(FUR);
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("F.U.R");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(dateFOrmat);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (FURO != null) {
            String dateFOrmat = dfDateInstance.format(FURO);
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("F.U.R Operacional");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(dateFOrmat);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        for (int i = 0; i < estimated.length; i++) {
            newAntmedido = new Antmedidos();

            System.out.println(estimated[i]);

            searchAntecedente = antecedentesFacade.searchByName(estimated[i]);

            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor("Seleccionado");
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (doubts.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Dudas");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(doubts);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (FPBirth != null) {
            String dateFOrmat = dfDateInstance.format(FPBirth);
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("F.P. Parto");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(dateFOrmat);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (gestationalAge > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Edad Gestación ingreso");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(gestationalAge));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (numberDays > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("N° de Dias");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(numberDays));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        /*if (doubtsHEA.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Motivo");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(doubtsHEA);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }*/
        
        if (reason.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Motivo");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(reason);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (blood.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Grupo de Sangre");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(blood);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (bloodType.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("RH");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(bloodType);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (sensitized.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Sensibilizada");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(sensitized);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (examinationCN.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Ex. clínico normal");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(examinationCN);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (examinationMN.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Ex. mamas normal");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(examinationMN);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (examinationON.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Ex. odont. normal");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(examinationON);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (normalPelvis.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Pelvis normal");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(normalPelvis);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (normalPapanic.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Papanic. normal");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(normalPapanic);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (normalCervix.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Cervix normal");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(normalCervix);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (VIH.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("VIH");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(VIH);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (VDRLOption.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("VLDR valor");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(VDRLOption);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (VDRL != null) {
            String dateFOrmat = dfDateInstance.format(VDRL);
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("VLDR fecha");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(dateFOrmat);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }

        for (int i = 0; i < HCTOCheck.length; i++) {
            newAntmedido = new Antmedidos();

            System.out.println(HCTOCheck[i]);

            searchAntecedente = antecedentesFacade.searchByName(HCTOCheck[i]);

            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor("Seleccionado");
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (HTCTOFloat > -0.1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("HCTO valor");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(String.valueOf(HTCTOFloat));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (HCTODate != null) {
            String dateFOrmat = dfDateInstance.format(HCTODate);
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("HCTO fecha");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(dateFOrmat);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (smoker.equals("")) {

        } else {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Fuma");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(smoker);
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        if (cantCigars > -1) {
            newAntmedido = new Antmedidos();

            searchAntecedente = antecedentesFacade.searchByName("Cigarros por día");
            newAntmedido.setIdAntmedidos(null);
            newAntmedido.setEpisodioid(searchEpisode.get(0));
            newAntmedido.setIdAntecedente(searchAntecedente.get(0));
            newAntmedido.setValor(Integer.toString(cantCigars));
            newAntmedido.setFecha(fecha);

            listAntMedidos.add(newAntmedido);
        }
        
        

        System.out.println("Personales: " + listAntMedidos.size());

        for (int j = 0; j < listAntMedidos.size(); j++) {
            antmedidosFacade.create(listAntMedidos.get(j));
        }
    }

    public String[] getHCTOCheck() {
        return HCTOCheck;
    }

    public void setHCTOCheck(String[] HCTOCheck) {
        this.HCTOCheck = HCTOCheck;
    }

    public double getHTCTOFloat() {
        return HTCTOFloat;
    }

    public void setHTCTOFloat(double HTCTOFloat) {
        this.HTCTOFloat = HTCTOFloat;
    }

    public Date getHCTODate() {
        return HCTODate;
    }

    public void setHCTODate(Date HCTODate) {
        this.HCTODate = HCTODate;
    }

    public String getSmoker() {
        return smoker;
    }

    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }

    public int getCantCigars() {
        return cantCigars;
    }

    public void setCantCigars(int cantCigars) {
        this.cantCigars = cantCigars;
    }

    public String getExaminationCN() {
        return examinationCN;
    }

    public void setExaminationCN(String examinationCN) {
        this.examinationCN = examinationCN;
    }

    public String getExaminationMN() {
        return examinationMN;
    }

    public void setExaminationMN(String examinationMN) {
        this.examinationMN = examinationMN;
    }

    public String getExaminationON() {
        return examinationON;
    }

    public void setExaminationON(String examinationON) {
        this.examinationON = examinationON;
    }

    public String getNormalPelvis() {
        return normalPelvis;
    }

    public void setNormalPelvis(String normalPelvis) {
        this.normalPelvis = normalPelvis;
    }

    public String getNormalPapanic() {
        return normalPapanic;
    }

    public void setNormalPapanic(String normalPapanic) {
        this.normalPapanic = normalPapanic;
    }

    public String getNormalCervix() {
        return normalCervix;
    }

    public void setNormalCervix(String normalCervix) {
        this.normalCervix = normalCervix;
    }

    public String getVIH() {
        return VIH;
    }

    public void setVIH(String VIH) {
        this.VIH = VIH;
    }

    public Date getVDRL() {
        return VDRL;
    }

    public void setVDRL(Date VDRL) {
        this.VDRL = VDRL;
    }

    public String getVDRLOption() {
        return VDRLOption;
    }

    public void setVDRLOption(String VDRLOption) {
        this.VDRLOption = VDRLOption;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getSensitized() {
        return sensitized;
    }

    public void setSensitized(String sensitized) {
        this.sensitized = sensitized;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getUsualWeight() {
        return usualWeight;
    }

    public void setUsualWeight(int usualWeight) {
        this.usualWeight = usualWeight;
    }

    public Date getFUR() {
        return FUR;
    }

    public void setFUR(Date FUR) {
        this.FUR = FUR;
    }

    public Date getFURO() {
        return FURO;
    }

    public void setFURO(Date FURO) {
        this.FURO = FURO;
    }

    public String[] getEstimated() {
        return estimated;
    }

    public void setEstimated(String[] estimated) {
        this.estimated = estimated;
    }

    public String getDoubts() {
        return doubts;
    }

    public void setDoubts(String doubts) {
        this.doubts = doubts;
    }

    public Date getFPBirth() {
        return FPBirth;
    }

    public void setFPBirth(Date FPBirth) {
        this.FPBirth = FPBirth;
    }

    public int getGestationalAge() {
        return gestationalAge;
    }

    public void setGestationalAge(int gestationalAge) {
        this.gestationalAge = gestationalAge;
    }

    public int getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }

    public String getDoubtsHEA() {
        return doubtsHEA;
    }

    public void setDoubtsHEA(String doubtsHEA) {
        this.doubtsHEA = doubtsHEA;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getDeeds() {
        return deeds;
    }

    public void setDeeds(int deeds) {
        this.deeds = deeds;
    }

    public int getAbortions() {
        return abortions;
    }

    public void setAbortions(int abortions) {
        this.abortions = abortions;
    }

    public int getBirths() {
        return births;
    }

    public void setBirths(int births) {
        this.births = births;
    }

    public String[] getBornCheck() {
        return bornCheck;
    }

    public void setBornCheck(String[] bornCheck) {
        this.bornCheck = bornCheck;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    public int getStillbirths() {
        return stillbirths;
    }

    public void setStillbirths(int stillbirths) {
        this.stillbirths = stillbirths;
    }

    public int getLiving() {
        return living;
    }

    public void setLiving(int living) {
        this.living = living;
    }

    public int getDeadFirstWeek() {
        return deadFirstWeek;
    }

    public void setDeadFirstWeek(int deadFirstWeek) {
        this.deadFirstWeek = deadFirstWeek;
    }

    public int getDeadSecondWeek() {
        return deadSecondWeek;
    }

    public void setDeadSecondWeek(int deadSecondWeek) {
        this.deadSecondWeek = deadSecondWeek;
    }

    public Date getLastPregnancy() {
        return lastPregnancy;
    }

    public void setLastPregnancy(Date lastPregnancy) {
        this.lastPregnancy = lastPregnancy;
    }

    public int getRNHeavier() {
        return RNHeavier;
    }

    public void setRNHeavier(int RNHeavier) {
        this.RNHeavier = RNHeavier;
    }

    public String getReasonAbortion() {
        return reasonAbortion;
    }

    public void setReasonAbortion(String reasonAbortion) {
        this.reasonAbortion = reasonAbortion;
    }

    public String[] getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String[] familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String[] getPersonalHistory() {
        return personalHistory;
    }

    public void setPersonalHistory(String[] personalHistory) {
        this.personalHistory = personalHistory;
    }

}
