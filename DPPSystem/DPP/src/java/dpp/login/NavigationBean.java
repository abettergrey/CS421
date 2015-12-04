
package dpp.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 * class that forwards urls
 */
@ManagedBean
@SessionScoped
public class NavigationBean implements java.io.Serializable 
{
    public String toLogin()
    {
        return "/login.xhtml";
    }
    
    public String redirectToWelcomeUser()
    {
        return "/user/index.xhtml";
    }
    
    public String redirectToInfo()
    {
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String redirectToWelcomeStaff()
    {
        return "/staff/index.xhtml";
    }
    
    public String redirectToAccountCreated()
    {
        return "/staff/accountOK.xhtml";
    }
    
    public String redirectToAddPatient()
    {
        return "/staff/addPatient.xhtml";
    }
}