/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean.vitalSigns;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gustavo Salvo Lara
 */
@ManagedBean
@SessionScoped
public class DateGroup {
   private Integer groups = 0;
   private String dates = new String();

    public DateGroup(Integer g, String d) {
        groups=g;
        dates=d;
    }

   
   
    public Integer getGroups() {
        return groups;
    }

    public void setGroups(Integer groups) {
        this.groups = groups;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }
   
   
    
    
    
    
}
