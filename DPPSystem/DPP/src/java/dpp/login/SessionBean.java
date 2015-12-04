package dpp.login;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * class that manages session
 */
public class SessionBean {
 
    /**
     * Returns the current HttpSession associated with this request 
     * @return current HttpSession associated with this request
     */
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
 
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }
 
    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("user").toString();
    }
 
    public static String getUserId() {
        HttpSession session = getSession();
  
        if (session != null)
            return (String) session.getAttribute("id");
        else
            return null;
    }
}
