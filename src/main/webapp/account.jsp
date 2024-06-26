<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="backend.servlets.RecensioneService" %>
<%@ page import="backend.Utente" %>      
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account</title>
<link rel="icon" type="image/x-icon" href="img/logo.ico">
  <style>
	body {
		font-family: Arial, sans-serif;
		margin: 0;
		padding: 0;
		background-color: #f2f2f2;
	}
	
	.header-container {
		text-align: center;
	}
	
	nav {
		background-color: rgb(255, 15, 135);
		overflow: hidden;
		text-align: center;
		margin-bottom: 20px;
		height: 70px;
	}
	
	button {
		background-color: rgb(255, 181, 218);
		border: none;
		color: white;
		padding: 14px 20px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 18px;
		border-radius: 12px; /* bordi arrotondati */
		cursor: pointer;
		margin-top: 13px;
		margin-right: 10px; /* margine a destra per separare i pulsanti */
	}
	
	button:hover {
		background-color: rgb(255, 128, 192);
		/* sfumatura al passaggio del mouse */
	}
	
	table {
		width: 50%;
		margin: 50px auto;
		border-collapse: collapse;
		/* Colore pi� scuro per il bordo della tabella */
	}
	
	table, th, td {
		border: 1px solid #ddd;
		padding: 8px;
		text-align: left;
	}
	
	th {
		background-color: rgb(255, 15, 135);
		color: white;
		font-size: 20px;
	}
	
	tr, td {
		background-color: #f2f2f2;
		font-size: 18px;
	}
</style>
</head>
<body>
<%
Utente u = (Utente) request.getSession().getAttribute("DATI_UTENTE");
if (u != null) {
	RecensioneService rc = new RecensioneService();
%>

<div class="header-container">
    <nav class="button-container">
        <button onclick="location.href='home.jsp'">Home</button>
    	<button onclick="location.href='logout.jsp'">Logout</button>
    </nav>
</div>
<table>
    <tr>
        <th>Campo</th>
        <th>Valore</th>
    </tr>
    <tr>
        <td><strong>Cognome:</strong></td>
        <td><%= u.getCognome() %></td>
    </tr>
    <tr>
        <td><strong>Nome:</strong></td>
        <td><%= u.getNome() %></td>
    </tr>
    <tr>
        <td><strong>Email:</strong></td>
        <td><%= u.getEmail() %></td>
    </tr>
    <tr>
        <td><strong>User:</strong></td>
        <td><%= u.getUser() %></td>
    </tr>
    <tr>
        <td><strong>Password:</strong></td>
        <td><%= u.getPwd() %></td>
    </tr>
    <tr>
        <td><strong>Nazionalita':</strong></td>
        <td><%= u.getNazionalit�() %></td>
    </tr>
     <tr>
        <td><strong>Data di nascita:</strong></td>
        <td><%= u.getDataNascitaS() %></td>
    </tr>
</table>
<% 
    } else {
        response.sendRedirect("index.jsp");
    } 
%>
</body>
</html>