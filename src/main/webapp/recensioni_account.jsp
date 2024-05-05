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
<title>Recensioni account</title>
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
        color: lightpink;
        font-size: 25px;
        position: relative;
        margin-bottom: 20px; 
    }
    .container {
	    display: flex;
	    flex-wrap: wrap;
	    justify-content: space-between;
	}
	
	.recensione {
	    width: calc(33.33% - 20px);
	    background-color: lightpink; 
	    border-radius: 10px;
	    padding: 20px;
	    margin-bottom: 20px;
	    box-sizing: border-box; /* Assicura che il padding e il margine non aumentino la larghezza */
	    margin-left: 20px;
	    
	}
	
	/* Media query per schermi di dimensioni medie */
	@media (max-width: 992px) {
	    .recensione {
	        width: calc(50% - 20px);
	    }
	}
	
	/* Media query per schermi di dimensioni piccole */
	@media (max-width: 600px) {
	    .recensione {
	        width: calc(100% - 20px);
	    }
	}
     .recensione h2 {	
	    color: mediumvioletred; 
     }
     .recensione p {	
        color: black; 
        font-size: 18px;
     }
    img {
        width: 100%; /* 100% della larghezza del suo contenitore*/ 
        height: auto; 	
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
            <h1>Le tue recensioni</h1>
        </div>
    </div>
	<div class="container">
     <% 
     	Utente u = (Utente)request.getSession().getAttribute("DATI_UTENTE");
     	RecensioneService rs = new RecensioneService(); 
     	ElencoRecensioni elencoRecensioni = null;
     	elencoRecensioni = rs.Select_RecensioniUtente(u); 
     	     	
        for(Recensione recensione : elencoRecensioni) {
    	%>
    	<div class="recensione">        	   	
	                <h2>Citta':<%=" "+recensione.getC().getNome()%></h2>
	                <p>Descrizione:<%=" "+recensione.getDesc() %></p>
	                <p>Voto: <%=" "+recensione.getVoto() %></p>
	                <p>Data: <%=" "+recensione.getData() %></p>                              
        </div>
    <%}%>
     </div>
</body>
</html>