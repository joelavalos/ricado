/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeanPatologia;

import entities.Patologia;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessionbeans.PatologiaFacadeLocal;

/**
 *
 * @author Joel
 */
@ManagedBean
@ViewScoped
public class mostrarPatologias {
    @EJB
    private PatologiaFacadeLocal patologiaFacade;
    
    private List<Patologia> patologias;
    private String nombrePatologia;
    private boolean gesPatologia;

    /**
     * Creates a new instance of mostrarPatologias
     */
    public mostrarPatologias() {
    }
    
    public void mostrarPatologias(){
        patologias = patologiaFacade.findAll();
    }
    
    public void crearPatologia(){
        gesPatologia = true;
        Patologia nuevaPatologia = new Patologia(null, nombrePatologia, gesPatologia);
        patologiaFacade.create(nuevaPatologia);
        System.out.println("Patologia creada");
    }

    public List<Patologia> getPatologias() {
        return patologias;
    }

    public void setPatologias(List<Patologia> patologias) {
        this.patologias = patologias;
    }

    public String getNombrePatologia() {
        return nombrePatologia;
    }

    public void setNombrePatologia(String nombrePatologia) {
        this.nombrePatologia = nombrePatologia;
    }

    public boolean isGesPatologia() {
        return gesPatologia;
    }

    public void setGesPatologia(boolean gesPatologia) {
        this.gesPatologia = gesPatologia;
    }
    
}
