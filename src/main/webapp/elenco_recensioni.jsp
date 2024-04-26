<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="backend.Recensione" %> 
<%@ page import="backend.Utente" %>
<%@ page import="backend.Citta" %> 
<%@ page import="backend.ElencoRecensioni" %> 
<%@ page import="backend.servlets.RecensioneService" %> 
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Elenco Recensioni</title>
<link rel="icon" type="image/x-icon" href="img/logo.ico">
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Parisienne&display=swap">
<style>
	body {
        background-color: white;
        font-family: 'Times New Roman', Times, serif;
        margin: 0;	/* Rimuovi il margine predefinito del body */
    }
    .intestazione{
    	background: white;
        padding: 50px;
        text-align: center;
        color: hotpink;
        position: relative;
    }
    .recensione { 
        background-color: white; 
        border-radius: 10px;
        padding: 20px;
        margin-bottom: 20px;
     }
     .recensione h2 {	
	    color: hotpink; 
     }
     .recensione p {	
        color: #333; /* Nero */
     }
     img {
        width: 100%; /* 100% della larghezza del suo contenitore*/ 
        height: auto; 	/adatta automaticamente l'altezza in proporzione al contenitore/
    	border-radius: 110px;
    }
    .logo-container {
	    position: absolute; /* Imposta il posizionamento assoluto per il logo */
        top: 0; /* Posiziona il logo in alto */
        left: 0; /* Posiziona il logo a sinistra */
        width: 150px;
        height: 150px;
        border-radius: 10px;
	}	
	.logo-image {
	    width: 150px; 
	    height: 150px; 
	    margin-left: 60px; 
		margin-top: 10px;
	}
	.logo-title-container {
        display: flex;
        align-items: center;
        justify-content: center; /* Centra il contenuto orizzontalmente */
    }
</style>
</head>
<body>
	<div class="intestazione">
        <div class="logo-title-container">
            <div class="logo-container">
                <img src="img/2.ico" alt="Logo" class="logo-image">
            </div>
            <h1>Recensioni degli utenti</h1>
        </div>
    </div>
	
     <% 
     	RecensioneService rs = new RecensioneService(); 
     	ElencoRecensioni elencoRecensioni = null;
     	elencoRecensioni = rs.select_Recensioni(); 
     	System.out.println(elencoRecensioni.toString());
     	     	
        for(Recensione recensione : elencoRecensioni) {
    	%>
    	<div class="recensione">        	   	
	    			<h2><%= recensione.getU() %></h2>
	                <p>Citta:<%= recensione.getC() %></p>
	                <p>Descrizione:<%= recensione.getDesc() %></p>
	                <p>Voto: <%= recensione.getVoto() %></p>
	                <p>Data: <%= recensione.getData() %></p>                              
        </div>
    <%}%>
    
</body>
</html>