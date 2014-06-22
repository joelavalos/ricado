/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import sessionbeans.PatologiaFacadeLocal;

/**
 *
 * @author Martín
 */
@ManagedBean
@RequestScoped
public class validator {

    private PatologiaFacadeLocal patologiaFacade;
        
    /**
     * Creates a new instance of validator
     */
    public validator() {
    }
    
    public void selectOneEpisode(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un episodio", "");
        String valor = value.toString();
        if(valor.equals("0")){            
            System.out.println("Error de validacion");
            throw new ValidatorException(fm);
        }
    }
}
