<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="backend.Utente" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SafeTravella</title>
<link rel="icon" type="image/x-icon" href="img/icon.ico">
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Parisienne&display=swap">
<style>
    img {
        width: 100%; 
        height: auto; 
    }
     body {
        margin: 0;
        padding: 0;
        font-family: 'Times New Roman', Times, serif;
    }
 	.header-container {
        background: rgb();
        background-size: cover;
        padding: 20px;
        text-align: center;
    }
    .header-container h1 {
        margin: 0;
        color: #000;
        font-family: 'Parisienne', cursive;
        font-size: 60px;
    }
    #accountButton {
    	background-color: rgb(168, 168, 255); 
    	color: #fff; 
    	border-radius: 5px; 
    	border: none; 
    	padding: 10px 20px; 
    	font-size: 16px; 
    	position: absolute; 
    	top: 40px; /* Distanza dal top */
    	right: 50px; /* Distanza dalla destra */
    	cursor: pointer;
	}
	
    .button-container {
        margin-top: 20px;
        text-align: center;
    }
    .button-container button {
        margin: 5px;
        padding: 10px 20px;
        background-color: rgb(255, 128, 192);
        border: none;
        border-radius: 5px;
        color: #fff;
        font-size: 16px;
        cursor: pointer;
    }
    .panel {
    	background-color: rgb(255, 255, 255);
    	padding: 5px 5px; /* Riduzione del padding */
    	text-align: center;
    	width: 20%; /* Imposta una larghezza percentuale */
    	margin: 0 auto; /* Centra il pannello */
    	border-radius: 10px; /* Arrotonda i bordi */
	}

</style>
</head>

<% 
    Utente u = (Utente)request.getSession().getAttribute("DATI_UTENTE");
    if(u != null){
    	//if(u.getProfessione().equals(Professione.ASSISTENTE)){
    	//Assistente ass = sc.leggiAssistente(u.getIdProfessionista());
    	
%>
<body>

    <div class="header-container">
        <h1>SafeTravella</h1>
        <h2><button id="accountButton" onclick="location.href='account.jsp'">Account</button></h2>

        <nav class="button-container">
            <button onclick="location.href='aggiungi_recensione.jsp'">Aggiungi Recensione</button>
            <button onclick="location.href='elenco_recensioni.jsp'">Elenco Recensioni</button>
            <button onclick="location.href='ricerca_citta.jsp'">Ricerca Città</button>
        	<button onclick="location.href='stato.jsp'">Stato</button>
        </nav>
    </div>

    <div class="panel">
        <h2>Benvenuta <%=u.getNome()+" "+u.getCognome() %></h2>
    </div>
    
    <% //accesso alla base dati per ricercare le recensioni %>
    
    <img src="img/img3.jpg" width="1000" height="600">
    
<% } else {
    response.sendRedirect("index.jsp");
} %>
</body>
</html>