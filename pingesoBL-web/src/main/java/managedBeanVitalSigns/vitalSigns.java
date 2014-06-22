/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeanVitalSigns;

import entities.Consulta;
import entities.Episodios;
import entities.Muesta;
import entities.Paciente;
import entities.Patologia;
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
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class vitalSigns {

    @EJB
    private MuestaFacadeLocal muestaFacade;
    @EJB
    private RegistroClinicoFacadeLocal registroClinicoFacade;
    @EJB
    private PacienteFacadeLocal pacienteFacade;
    @EJB
    private PersonaFacadeLocal personaFacade;
    @EJB
    private SignosVitalesFacadeLocal signosVitalesFacade;

    private List<SignosVitales> searchVitalSigns;
    private String vitalSignsName;
    private int vitalSignsMinRange;
    private int vitalSignsMaxRange;

    private List<SignosVitales> selectedVitalSign;
    private int vitalSignsId;
    private int vitalSignsValor;

    private List<Paciente> searchPaciente;
    private List<RegistroClinico> searchRegistroClinico;

    private Integer PersonId;
    private String PersonRut = "69727697";
    private Integer Rut = 6972769;
    
    private List<Muesta> createSamples = new ArrayList<Muesta>();
    private List<Muesta> createSamplesAlways = new ArrayList<Muesta>();
    int max = 0;

    private int peso;
    private int altura;
    private int temperatura;
    private int saturación;
    private int presiónSistólica;
    private int presiónDiastólica;

    /**
     * Creates a new instance of vitalSigns
     */
    @PostConstruct
    public void init() {
        searchVitalSigns = signosVitalesFacade.findAll();
    }

    public vitalSigns() {
    }

    public void addVitalSigns() {
        SignosVitales newVitalSigns = new SignosVitales(null, vitalSignsName);
        newVitalSigns.setRangoMin(vitalSignsMinRange);
        newVitalSigns.setRangoMax(vitalSignsMaxRange);

        signosVitalesFacade.create(newVitalSigns);
        searchVitalSigns = signosVitalesFacade.findAll();
        System.out.println("Se ha creado el nuevo signo vital");
    }

    public void createVitalSignsPatients() {
        PersonId = personaFacade.findByRut(Rut);
        searchPaciente = pacienteFacade.searchByPerson(PersonId);
        searchRegistroClinico = registroClinicoFacade.searchByPaciente(searchPaciente.get(0));

        List<Muesta> allSamples = muestaFacade.searchByPatient(searchPaciente.get(0));
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

        System.out.println("Valor del grupo: " + max);

        Date fecha = new Date();

        selectedVitalSign = signosVitalesFacade.searchByName("Peso");
        Muesta newMuesta = new Muesta(null);
        newMuesta.setFecha(fecha);
        newMuesta.setValor(peso);
        newMuesta.setIdPersona(searchPaciente.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = signosVitalesFacade.searchByName("Altura");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(altura);
        newMuesta.setIdPersona(searchPaciente.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = signosVitalesFacade.searchByName("Temperatura");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(temperatura);
        newMuesta.setIdPersona(searchPaciente.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = signosVitalesFacade.searchByName("Saturacion");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(saturación);
        newMuesta.setIdPersona(searchPaciente.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = signosVitalesFacade.searchByName("Presion sistolica");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(presiónSistólica);
        newMuesta.setIdPersona(searchPaciente.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        newMuesta = new Muesta(null);
        selectedVitalSign = signosVitalesFacade.searchByName("Presion diastolica");
        newMuesta.setFecha(fecha);
        newMuesta.setValor(presiónDiastólica);
        newMuesta.setIdPersona(searchPaciente.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);
        createSamplesAlways.add(newMuesta);

        for (int i = 0; i < createSamplesAlways.size(); i++) {
            if (createSamplesAlways.get(i).getValor() != 0) {
                muestaFacade.create(createSamplesAlways.get(i));
            }
        }

        createSamplesAlways.clear();

        for (int i = 0; i < createSamples.size(); i++) {
            muestaFacade.create(createSamples.get(i));
        }

        createSamples.clear();

        max = 0;
        peso = 0;
        altura = 0;
        temperatura = 0;
        saturación = 0;
        presiónSistólica = 0;
        presiónDiastólica = 0;

        System.out.println("El id del signo vital es: " + selectedVitalSign.get(0).getNombreSvital());
        System.out.println("El id del registro clinico es: " + searchRegistroClinico.get(0).getRegistroclinicoid());

        System.out.println("Se ha creado la muestra");
        max = 0;

    }

    public void addVitalSignsPatients() {
        PersonId = personaFacade.findByRut(Rut);
        searchPaciente = pacienteFacade.searchByPerson(PersonId);
        selectedVitalSign = signosVitalesFacade.searchById(vitalSignsId);

        List<Muesta> allSamples = muestaFacade.searchByPatient(searchPaciente.get(0));
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
        Muesta newMuesta = new Muesta(null, fecha, vitalSignsValor);
        newMuesta.setIdPersona(searchPaciente.get(0));
        newMuesta.setIdSvitales(selectedVitalSign.get(0));
        newMuesta.setGrupo(max);

        createSamples.add(newMuesta);

        System.out.println("El valor maximo del grupo es: " + max);
    }

    public List<SignosVitales> getSearchVitalSigns() {
        return searchVitalSigns;
    }

    public void setSearchVitalSigns(List<SignosVitales> searchVitalSigns) {
        this.searchVitalSigns = searchVitalSigns;
    }

    public String getVitalSignsName() {
        return vitalSignsName;
    }

    public void setVitalSignsName(String vitalSignsName) {
        this.vitalSignsName = vitalSignsName;
    }

    public int getVitalSignsMinRange() {
        return vitalSignsMinRange;
    }

    public void setVitalSignsMinRange(int vitalSignsMinRange) {
        this.vitalSignsMinRange = vitalSignsMinRange;
    }

    public int getVitalSignsMaxRange() {
        return vitalSignsMaxRange;
    }

    public void setVitalSignsMaxRange(int vitalSignsMaxRange) {
        this.vitalSignsMaxRange = vitalSignsMaxRange;
    }

    public List<SignosVitales> getSelectedVitalSign() {
        return selectedVitalSign;
    }

    public void setSelectedVitalSign(List<SignosVitales> selectedVitalSign) {
        this.selectedVitalSign = selectedVitalSign;
    }

    public int getVitalSignsValor() {
        return vitalSignsValor;
    }

    public void setVitalSignsValor(int vitalSignsValor) {
        this.vitalSignsValor = vitalSignsValor;
    }

    public int getVitalSignsId() {
        return vitalSignsId;
    }

    public void setVitalSignsId(int vitalSignsId) {
        this.vitalSignsId = vitalSignsId;
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

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
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

    public int getSaturación() {
        return saturación;
    }

    public void setSaturación(int saturación) {
        this.saturación = saturación;
    }

    public int getPresiónSistólica() {
        return presiónSistólica;
    }

    public void setPresiónSistólica(int presiónSistólica) {
        this.presiónSistólica = presiónSistólica;
    }

    public int getPresiónDiastólica() {
        return presiónDiastólica;
    }

    public void setPresiónDiastólica(int presiónDiastólica) {
        this.presiónDiastólica = presiónDiastólica;
    }

    public List<Muesta> getCreateSamples() {
        return createSamples;
    }

    public void setCreateSamples(List<Muesta> createSamples) {
        this.createSamples = createSamples;
    }

    public List<Muesta> getCreateSamplesAlways() {
        return createSamplesAlways;
    }

    public void setCreateSamplesAlways(List<Muesta> createSamplesAlways) {
        this.createSamplesAlways = createSamplesAlways;
    }

}
