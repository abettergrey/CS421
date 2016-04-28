/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpp.controllers;

import javax.faces.bean.ManagedBean;
import dpp.dbClasses.User;
import dpp.login.NavigationBean;
import dpp.dbClasses.Patient;
import dpp.patient.maintenance.PatientMaintananceAppDB;
import dpp.dbClasses.RoleNode;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
 * class binded to create new patient form
 */
@ManagedBean(name="maintains")
public class CreatePatientAccountController 
{
    private Patient patient = new Patient();
    private User user = new User();
    private String tmpEmail;
    private String searchField;
       
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTmpEmail() {
        return tmpEmail;
    }

    public void setTmpEmail(String tmpEmail) {
        this.tmpEmail = tmpEmail;
    }
    
    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }
    
    public String search()
    {
        NavigationBean nav = new NavigationBean();
        
        String mes = "Patient is already registered!";
        
        PatientMaintananceAppDB tmp = new PatientMaintananceAppDB(); 
        
        if (!tmp.searchForPatient(searchField))
        {
            patient.setSsn(searchField);
            return nav.redirectToAddPatient();
        }
        
        FacesMessage msg = new FacesMessage(mes, "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return nav.redirectToAddPatient(); 
    }
  
    /**
     * create new patient and return a navigation string for redirecting
     * @return String 
     */
    public String create() 
    {
        NavigationBean nav = new NavigationBean();
        
        String mes = "Unable to create Patient!";
        
        // if emails match create patient and redirct to success page
        // else: send back error mes
        if( patient.isEmailValid() && patient.compareEmail(tmpEmail) )
        {
            user.setUsername(user.generateUsername(patient.getFirstName(), patient.getMiddle(), patient.getLastName()));
            user.setPassword(user.generatePassword());
            user.setRole(new RoleNode().USER);
            
            PatientMaintananceAppDB tmp = new PatientMaintananceAppDB();
            int count = tmp.countRows(user);
            patient.setId(count+1);
            tmp.createNewPatient(this.patient, user);
        
            return nav.redirectToAccountCreated();
        }
        else
            mes = "email address is not in the right format";
        
        FacesMessage msg = new FacesMessage(mes, "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return nav.redirectToAddPatient();
    } 
}
