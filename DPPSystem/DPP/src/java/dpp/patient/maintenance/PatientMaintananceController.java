/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpp.patient.maintenance;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import javax.faces.bean.ManagedBean;
import dpp.login.User;
import java.util.Random;
import java.util.Calendar;
import java.util.Date;
import dpp.login.NavigationBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
 * class binded to create new patient form
 */
@ManagedBean(name="maintains")
public class PatientMaintananceController 
{
    private Patient patient = new Patient();
    private User user = new User();
    private String tmpEmail;
    private String tmpPassword;
    private String tmpusername;
       
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

    public String getTmpPassword() {
        return tmpPassword;
    }

    public void setTmpPassword(String tmpPassword) {
        this.tmpPassword = tmpPassword;
    }

    public String getTmpusername() {
        return tmpusername;
    }

    public void setTmpusername(String tmpusername) {
        this.tmpusername = tmpusername;
    }

    /**
     * create new patient and return a navigationstring for redirecting
     * @return String 
     */
    public String create() 
    {
        NavigationBean nav = new NavigationBean();
        
        String mes = "Unable to create Patient!";
        
        // if emails match create patient and redirct to success page
        // else: send back error mes
        if(tmpEmail.equals(patient.getEmail()))
        {
            String charFirstName = "" + patient.getFirstName().toLowerCase().charAt(0);
               
            user.setUsername(charFirstName + patient.getMiddle().toLowerCase() + patient.getLastName().toLowerCase());
            user.setPassword(generatePassword());
            user.setRole(new RoleNode().USER);
        
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            patient.setCreatedDate(date.toString());
        
            PatientMaintananceAppDB tmp = new PatientMaintananceAppDB();
            tmp.createNewPatient(this.patient, this.user);
        
            return nav.redirectToAccountCreated();
        }
        else
            mes = "email addresses do not match";
        
        FacesMessage msg = new FacesMessage(mes, "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return nav.redirectToAddPatient();
    } 
    
    /**
     * generate temp password call encryptPassword to return encrpted password
     * @return String
     */
    private String generatePassword()
    {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        int len = 9;
        
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ ) 
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
    
        tmpPassword = sb.toString();
        
        return encryptPassword(sb.toString());
    }
    
    /**
     * return the generated encypted password
     * @param password
     * @return String
     */
    private String encryptPassword(String password)
    {
        return Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
    }
}
