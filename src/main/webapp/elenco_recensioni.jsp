<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="backend.Recensione" %> 
<%@ page import="backend.Utente" %>
<%@ page import="backend.Citta" %> 
<%@ page import="backend.ElencoCitta" %> 
<%@ page import="backend.ElencoRecensioni" %> 
<%@ page import="backend.servlets.RecensioneService" %> 
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Elenco delle recensioni</title>
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
	 	position: relative;
	    width: calc(33.33% - 20px);
	    background-color: lightpink; 
	    border-radius: 10px;
	    padding: 20px;
	    margin-bottom: 20px;
	    box-sizing: border-box; /* il padding e il margine non aumentano la larghezza */
	    margin-left: 20px;
	    
	}
	
	/* per schermi con dimensioni medie */
	@media (max-width: 992px) {
	    .recensione {
	        width: calc(50% - 20px);
	    }
	}
	
	/* dimensioni piccole */
	@media (max-width: 600px) {
	    .recensione {
	        width: calc(100% - 20px);
	    }
	}
    .recensione h2 {	/*intestazione h2'*/
	    color: mediumvioletred; 
    }
    .recensione p {	/*testo della recensione*/
        color: black; 
        font-size: 18px;
    }
    .voto {
	    position: absolute; /* posizionamento assoluto per il voto */
	    top: 10px; 
	    right: 10px; 
	    background-color: lightpink; /* Colore di sfondo del voto */
	    color: mediumvioletred; /* Colore testo del voto */
	    font-size: 23px;
	    font-weight: bold;
	    padding: 5px 10px; /* Spaziatura interna */
	    border-radius: 5px; /* Bordi arrotondati */
	}
    img {
        width: 100%; /* 100% della larghezza del suo contenitore*/ 
        height: auto; 	
    	border-radius: 110px;
    }
    .logo-container {
	    position: absolute; 
        top: 0; 
        left: 0; 
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
	<div class="container">
     <% 
     	RecensioneService rs = new RecensioneService(); 
     	ElencoRecensioni elencoRecensioni = null;
     	elencoRecensioni = rs.select_Recensioni(); 
     	Utente u = null;
        for(Recensione recensione : elencoRecensioni) {
        	u = recensione.getU();
    	%>
    	
    	<div class="recensione">        	   	
	    			<h2><%= u.getNome()+" "%><%= u.getCognome()%>, <%= recensione.getC().getNome()%></h2>   
	    			<h2><%= recensione.getVoto() %></h2>	
	    			<p>Data  <%= recensione.getData() %></p> 		
	                <p><%= recensione.getDesc() %></p>
	               	                                             
        </div>
    <%}%>
    </div>
</body>
</html>