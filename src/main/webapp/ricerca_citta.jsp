<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Recensioni citta</title>
<link rel="icon" type="image/x-icon" href="img/logo.ico">
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Parisienne&display=swap">
<style>
    
    select {
        width: calc(30% - 12px);
        padding: 8px;
        margin-bottom: 10px;
        border-radius: 3px;
        border: 1px solid #ccc;
        background-color: #f8f8f8; /* Colore di sfondo leggermente diverso */
        font-size: 16px; /* Dimensione del testo */
        color: #555; /* Colore del testo */
    }

    /* Aggiungi stile al bottone di submit */
    input[type="submit"] {
        width: calc(20% - 12px);
        padding: 10px;
        margin-top: 10px; /* Aggiungi margine superiore */
        border: none;
        border-radius: 3px;
        background-color: hotpink; /* Colore di sfondo */
        color: white; /* Colore del testo */
        font-size: 16px; /* Dimensione del testo */
        cursor: pointer; /* Cambia il cursore al passaggio */
    }

    /* Aggiungi stile al bottone di submit al passaggio del mouse */
    input[type="submit"]:hover {
        background-color: #ff69b4; /* Cambia colore al passaggio del mouse */
    }
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
	    color: black; 
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
<%	RecensioneService rs = new RecensioneService(); %>
<body>
	<div class="intestazione">
        <div class="logo-title-container">
            <div class="logo-container">
                <img src="img/2.ico" alt="Logo" class="logo-image">
            </div>
            <h1>Recensioni della città</h1>
        </div>
    </div>
    <label>Citta:</label>
    <form action="ricerca_citta.jsp" method="post">
    	<select name="Citta">
    	<%		
    		ElencoCitta ec = null;
    		ec = rs.select_Citta();
    		for(Citta c : ec){
    			%>
    			<option value="<%=c.getNome()%>">
    			<%=c.getNome()%>
    			</option>
    			<%
    		}
    		%>
   
        </select>
        <input type="submit" value="Conferma">
    </form>
	<div class="container">
     <% 
     	ElencoRecensioni elencoRecensioni = null;
     	String nomeC = request.getParameter("Citta");
    	System.out.println(nomeC);
    	Citta city = rs.select_Citta(nomeC);
    	//System.out.println(city.toString());
     	elencoRecensioni = rs.select_RecensioniCitta(city);
     	     	
        for(Recensione recensione : elencoRecensioni) {
        	Utente u = recensione.getU();
    	%>
    	<div class="recensione">        	   	
	    	<p>Utente:<%= recensione.getU().getNome()+" "%><%= recensione.getU().getCognome()%></p>
	        <p>Descrizione:<%= recensione.getDesc()%></p>
	        <p>Voto: <%= recensione.getVoto() %></p>
	        <p>Data: <%= recensione.getData() %></p>                           
        </div>
    <%}%>
    </div>
</body>
</html>