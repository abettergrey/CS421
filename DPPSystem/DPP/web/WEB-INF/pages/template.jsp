<%-- 
    Document   : template
    Created on : Nov 29, 2015, 5:41:50 PM
    Author     : dggodi
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${param.title}}</title>
        <link rel="stylesheet" type="text/css" 
              href="${pageContext.request.contextPath}/resources/dpp.css" />
    </head>
    <body>
        <jsp:include page="/WEB-INF/pages/header.jsp"/>

	
	<h1>${param.title}</h1>

	<jsp:include page="/WEB-INF/pages/${param.content}.jsp"/>
	
	<jsp:include page="/WEB-INF/pages/footer.jsp"/>
    </body>
</html>
