<jsp:useBean id="user" class="user.UserData" scope="session"/>
<jsp:setProperty name="user" property="*"/>
<h:form>
    <h:inputText />
    <!--<h:outputLabel value="username: " style="font-weight:bold"/>
    <h:inputText id="username" value="#{logBean.name}"/>
    <h:commandButton value ="Add User" action=logBeanser.save()}"/>-->
</h:form>
<!--<h:form action="login.jsp" method="post">  
    username:<input type="text" placeholder="username" name="username"/> 
    Password:<input type="password" placeholder= "password" name="password"/> 
    <input type="submit" value="login"/>
</h:form>-->