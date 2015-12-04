package dpp.login;

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
        List<User> tmpuser = tmp.authenticate(this.user.getUsername(), this.user.getPassword());     
        
        if(!tmpuser.isEmpty())
        {
            user.setUsername(tmpuser.get(0).getUsername());
            user.setRole(tmpuser.get(0).getRole());

            loggedIn = true;
            
            HttpSession session = SessionBean.getSession();
            session.setAttribute("user", user);
            
            if (user.getRole().equals("STAFF"))           
                return navigationBean.redirectToWelcomeStaff();          
            else
                return navigationBean.redirectToWelcomeUser();
        }
        
        FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
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
