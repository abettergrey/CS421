<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<jsp:useBean id="login" class="dpp.Login">
    <jsp:setProperty name="login" property="*"/>
</jsp:useBean>

<!DOCTYPE html>
<html>
    <head></head>
<body>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    //if(login.authenticate(username, password))
    //{
        //session.setAttribute("userid", username);
        response.sendRedirect("welcome.xhtml");
        out.println("ok");
    //}
    //else
        //response.sendRedirect("login.xhtml");
        //out.println("not ok");
    
%>  
    
</body>
</html>
