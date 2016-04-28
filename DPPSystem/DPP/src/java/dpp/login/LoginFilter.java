/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpp.login;

import dpp.dbClasses.User;
import dpp.controllers.LoginController;
import dpp.dbClasses.RoleNode;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * creates a response. 
 * They are preprocessors of the request before it reaches a servlet
 */
public class LoginFilter implements javax.servlet.Filter
{
 
    
    /**
     * forwards a a new url according to the conditions
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException         
    {
        LoginController login = (LoginController)((HttpServletRequest)request).getSession().getAttribute("logBean");
   
        HttpServletRequest req = (HttpServletRequest)request;
        
        HttpSession session = ((HttpServletRequest)request).getSession(false);
        User currentUser = (User)session.getAttribute("user");
         
        
       
        // if no session exists move to forward to login page
        // else if: if users role is USER and url has the substring staff 
        //          then terminate session and forward to login
        // else: send to servlet to run main program
        if(login == null || !login.isLoggedIn())
        {
            NavigationBean navigationBean  = new NavigationBean();
            //req.getRequestDispatcher(navigationBean.toLogin()).forward(request, response);
        
            RequestDispatcher dispatcher = request.getRequestDispatcher(navigationBean.toLogin());
            dispatcher.forward(request, response);
            
        }
        else if(currentUser.getRole().equals(new RoleNode().USER) && req.getRequestURI().contains("staff"))
        {
            session.invalidate();
            req.getRequestDispatcher("/error.xhtml").forward(request, response);
        }    
        else
            chain.doFilter(request, response);   
    }
  
    @Override
    public void init(FilterConfig config) throws ServletException {}
    
    @Override
    public void destroy(){}
}