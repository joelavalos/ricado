/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean.consultation;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@SessionScoped
public class DiagnosesPathology {
    private Integer diagnosticId;
    private Date diagnosticDate;
    private boolean diagnosticGes;
    private String diagnosticState;
    private String pathologyId;
    private String pathologyName;
    private boolean pathologyGes;

    public DiagnosesPathology(Date diagnosticDate, boolean diagnosticGes, String diagnosticState, String pathologyId, String pathologyName, boolean pathologyGes) {
        this.diagnosticDate = diagnosticDate;
        this.diagnosticGes = diagnosticGes;
        this.diagnosticState = diagnosticState;
        this.pathologyId = pathologyId;
        this.pathologyName = pathologyName;
        this.pathologyGes = pathologyGes;
    }

    
    
    public Integer getDiagnosticId() {
        return diagnosticId;
    }

    public void setDiagnosticId(Integer diagnosticId) {
        this.diagnosticId = diagnosticId;
    }

    public Date getDiagnosticDate() {
        return diagnosticDate;
    }

    public void setDiagnosticDate(Date diagnosticDate) {
        this.diagnosticDate = diagnosticDate;
    }

    public boolean isDiagnosticGes() {
        return diagnosticGes;
    }

    public void setDiagnosticGes(boolean diagnosticGes) {
        this.diagnosticGes = diagnosticGes;
    }

    public String getDiagnosticState() {
        return diagnosticState;
    }

    public void setDiagnosticState(String diagnosticState) {
        this.diagnosticState = diagnosticState;
    }

    public String getPathologyId() {
        return pathologyId;
    }

    public void setPathologyId(String pathologyId) {
        this.pathologyId = pathologyId;
    }

    public String getPathologyName() {
        return pathologyName;
    }

    public void setPathologyName(String pathologyName) {
        this.pathologyName = pathologyName;
    }

    public boolean isPathologyGes() {
        return pathologyGes;
    }

    public void setPathologyGes(boolean pathologyGes) {
        this.pathologyGes = pathologyGes;
    }
    
    
    
}
