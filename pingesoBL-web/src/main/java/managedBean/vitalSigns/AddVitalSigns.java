/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean.vitalSigns;

import entities.Muesta;
import entities.Paciente;
import entities.RegistroClinico;
import entities.SignosVitales;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.MuestaFacadeLocal;
import sessionbeans.PacienteFacadeLocal;
import sessionbeans.PersonaFacadeLocal;
import sessionbeans.RegistroClinicoFacadeLocal;
import sessionbeans.SignosVitalesFacadeLocal;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@ViewScoped
public class AddVitalSigns {

    @EJB
    private MuestaFacadeLocal sampleFacade;
    @EJB
    private RegistroClinicoFacadeLocal clinicalRecordFacade;
    @EJB
    private PacienteFacadeLocal patientFacade;
    @EJB
    private PersonaFacadeLocal personFacade;
    @EJB
    private SignosVitalesFacadeLocal vitalSignsFacade;

    private List<SignosVitales> searchVitalSigns;
    private String vitalSignsName;
    private int vitalSignsMinRange;
    private int vitalSignsMaxRange;

    private List<SignosVitales> selectedVitalSign;
    private int vitalSignsId;
    private int vitalSignsValue;

    private List<Paciente> searchPatient;
    private List<RegistroClinico> searchClinicalRecord;

    private Integer PersonId;
    private String PersonRut = "69727697";
    private Integer Rut = 6972769;

    private List<Muesta> createSamples = new ArrayList<Muesta>();
    private List<Muesta> createSamplesAlways = new ArrayList<Muesta>();
    int max = 0;

    private int peso;
    private int altura;
    private int temperatura;
    private int saturacion;
    private int presionSistolica;
    private int presionDiastolica;

    @PostConstruct
    public void init() {
        String[] vitalSignsO = {"Peso","Altura","Temperatura","Saturación O2",
        "Presión Sistólica", "Presión Diastólica"};
        searchVitalSigns = vitalSignsFacade.findAll();
        for(int i=0; i<searchVitalSigns.size(); i++){
            for(int j = 0; j< vitalSignsO.length;j++){
                if(searchVitalSigns.get(i).getNombreSvital().equals(vitalSignsO[j])){
                    searchVitalSigns.remove(i);
                    i--;
                    break;
                }
            }
        }
    }

    public void addNewVitalSigns() {
        SignosVitales newVitalSigns = new SignosVitales(null, vitalSignsName);
        newVitalSigns.setRangoMin(vitalSignsMinRange);
        newVitalSigns.setRangoMax(vitalSignsMaxRange);

        vitalSignsFacade.create(newVitalSigns);
        searchVitalSigns = vitalSignsFacade.findAll();
    }

    public void createVitalSignsPatients() {
        PersonId = personFacade.findByRut(Rut);
        searchPatient = patientFacade.searchByPerson(PersonId);
        searchClinicalRecord = clinicalRecordFacade.searchByPaciente(searchPatient.get(0));

        List<Muesta> allSamples = sampleFacade.searchByPatient(searchPatient.get(0));
        max = 0;
        if (allSamples.isEmpty()) {
            max = 0;
        } else {
            for (Muesta allSample : allSamples) {
                if (allSample.getGrupo() > max) {
                    max = allSample.getGrupo();
                } else {
                }
            }
            max++;
        }
        
        Date fecha = new Date();

        selectedVitalSign = vitalSignsFacade.searchByName("Peso");
        Muesta newMuesta = new Muesta(null);
        newMuesta.setFecha(fecha);
        newMuesta.setValor(peso);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        
        newMuesta = new Muesta(null);
        selectedVitalSign = vitalSignsFacade.searchByName("Altura");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(altura);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = vitalSignsFacade.searchByName("Temperatura");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(temperatura);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = vitalSignsFacade.searchByName("Saturación O2");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(saturacion);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = vitalSignsFacade.searchByName("Presión Sistólica");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(presionSistolica);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = vitalSignsFacade.searchByName("Presión Diastólica");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(presionDiastolica);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);


        for (int i = 0; i < createSamplesAlways.size(); i++) {
            if (createSamplesAlways.get(i).getValor() != 0) {
                sampleFacade.create(createSamplesAlways.get(i));
            }
        }

        createSamplesAlways.clear();

        for (Muesta createSample : createSamples) {
            sampleFacade.create(createSample);
        }
        
        resetVitalSigns();
    }

    public void addVitalSignsPatients() {
        PersonId = personFacade.findByRut(Rut);
        searchPatient = patientFacade.searchByPerson(PersonId);
        selectedVitalSign = vitalSignsFacade.searchById(vitalSignsId);

        List<Muesta> allSamples = sampleFacade.searchByPatient(searchPatient.get(0));
        max = 0;
        if (allSamples.isEmpty()) {
            max = 0;
        } else {
            for (int i = 0; i < allSamples.size(); i++) {
                if (allSamples.get(i).getGrupo() > max) {
                    max = allSamples.get(i).getGrupo();
                } else {

                }
            }
            max++;
        }
        Date fecha = new Date();
        Muesta newMuesta = new Muesta(null, fecha, vitalSignsValue);
        newMuesta.setIdPersona(searchPatient.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamples.add(newMuesta);
    }

    public void resetVitalSigns(){
        createSamples.clear();
        max = 0;
        peso = 0;
        altura = 0;
        temperatura = 0;
        saturacion = 0;
        presionSistolica = 0;
        presionDiastolica = 0;
        max = 0;
        vitalSignsId = 0;
        vitalSignsValue = 0;
    }
    
    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public int getSaturacion() {
        return saturacion;
    }

    public void setSaturacion(int saturacion) {
        this.saturacion = saturacion;
    }

    public int getPresionSistolica() {
        return presionSistolica;
    }

    public void setPresionSistolica(int presionSistolica) {
        this.presionSistolica = presionSistolica;
    }

    public int getPresionDiastolica() {
        return presionDiastolica;
    }

    public void setPresionDiastolica(int presionDiastolica) {
        this.presionDiastolica = presionDiastolica;
    }

    public List<SignosVitales> getSearchVitalSigns() {
        return searchVitalSigns;
    }

    public void setSearchVitalSigns(List<SignosVitales> searchVitalSigns) {
        this.searchVitalSigns = searchVitalSigns;
    }

    public int getVitalSignsId() {
        return vitalSignsId;
    }

    public void setVitalSignsId(int vitalSignsId) {
        this.vitalSignsId = vitalSignsId;
    }

    public int getVitalSignsValue() {
        return vitalSignsValue;
    }

    public void setVitalSignsValue(int vitalSignsValue) {
        this.vitalSignsValue = vitalSignsValue;
    }

    public List<Muesta> getCreateSamples() {
        return createSamples;
    }

    public void setCreateSamples(List<Muesta> createSamples) {
        this.createSamples = createSamples;
    }

}
