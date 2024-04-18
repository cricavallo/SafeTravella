<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="backend.servlets.RecensioneService" %>
<%@ page import="backend.Utente" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account</title>
<link rel="icon" type="image/x-icon" href="img/logo.ico">
</head>
<% 
    Utente u = (Utente)request.getSession().getAttribute("DATI_UTENTE");
    if(u != null){
    	RecensioneService rc = new RecensioneService();
    	
   }
    	
%>
<body>

</body>
</html>