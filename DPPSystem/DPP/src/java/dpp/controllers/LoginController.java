package dpp.controllers;

import dpp.login.LogAppDB;
import dpp.login.NavigationBean;
import dpp.login.SessionBean;
import dpp.dbClasses.User;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Controller binded to login.xhtml
 * logins in the user by setting and terminating a session
 */
@ManagedBean(name="logBean")
@SessionScoped
public class LoginController
{
    @ManagedProperty(value="#{navigationBean}")
    private NavigationBean navigationBean;

    private User user = new User();

    private boolean loggedIn;

    public String logIn()
    {
        LogAppDB tmp = new LogAppDB();
        List<User> tmpuser = tmp.authenticate(this.user.getUsername());     
        
        if(!tmpuser.isEmpty() && user.comparePassword(tmpuser.get(0).getPassword()))
        {
            // set session
            user.setUsername(tmpuser.get(0).getUsername());
            user.setRole(tmpuser.get(0).getRole());

            HttpSession session = SessionBean.getSession();
            session.setAttribute("user", user);
            
            loggedIn = true;
            System.out.println(user.getUsername() + "****************************");
            // if user role is STAFF redirect to staff hme page
            // else: redirect to user home page
            if (user.getRole().equals("STAFF"))           
                return navigationBean.redirectToWelcomeStaff();          
            else
                return navigationBean.redirectToWelcomeUser();
        }
        
        String mes = "Login failed: The login you entered is not valid. Please try again, or contact your healthcare provider.";
        
        // create error message if passworeds are nto equal
        FacesMessage msg = new FacesMessage(mes, "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return navigationBean.toLogin();
    }
    
    /**
     * terminates the session and returns msg
     * @return 
     */
    public String doLogout()
    {
        loggedIn = false;
        
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        return navigationBean.toLogin(); 
    }

    // getters and setters
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public NavigationBean getNavigationBean() {
        return navigationBean;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    } 
}
