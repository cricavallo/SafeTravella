<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="backend.Recensione" %> 
<%@ page import="backend.UtenteService" %> 
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aggiungi Recensione</title>
<link rel="icon" type="image/x-icon" href="img/logo.ico">
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #fff;
        margin: 0;
        padding: 0;
    }
    
    .container {
        max-width: 600px;
        margin: 20px auto;
        padding: 20px;
        background-color: rgb(255, 181, 218);
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    
    h2 {
        text-align: left;
    }
    
    label {
        font-weight: bold;
        margin-bottom: 5px;
    }
    
    input[type="text"],
    input[type="number"],
    select {
        width: calc(100% - 12px);
        padding: 8px;
        margin-bottom: 10px;
        border-radius: 3px;
        border: 1px solid #ccc;
    }
    
    textarea {
        width: calc(100% - 12px);
        padding: 8px;
        margin-bottom: 10px;
        border-radius: 3px;
        border: 1px solid #ccc;
        resize: vertical;
    }
    
    input[type="submit"] {
        background-color: #007bff;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 3px;
        cursor: pointer;
        font-size: 16px;
    }
    
    input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>

<div class="container">
    <h2>Aggiungi Recensione</h2>
    <h3></h3>

    <form action="aggiungi_recensione.jsp" method="post">
    	<label>Città:</label>
        <select name="città">
        	<option value="Amsterdam">Amsterdam</option>
            <option value="Andorra La Vella">Andorra La Vella</option>
            <option value="Ankara">Ankara</option>
            
            
        </select><br>
        
        <label>Data:</label>
        <input type="date" name="data" required><br>
        
        <label>Descrizione:</label>
        <textarea name="descrizione" rows="5" required></textarea><br>
        
        <label>Voto:</label>
        <input type="number" name="voto" required min="0" max="10" step="0.5"><br>
        
        
        <input type="submit" value="Conferma">
    </form>
</div>


<%
// Verifica se la richiesta è una richiesta POST
if ("POST".equals(request.getMethod())) {
    // Prendi i valori dai parametri della richiesta
    // Controllo sulla data del viaggio
    LocalDate data = null;
    try {
        data = LocalDate.parse(request.getParameter("data"));
    } catch (Exception e) {
        out.println("<h3 class='error-message'>Errore nella data del viaggio!</h3>");
        out.println("</body></html>");
        return;
    }
    String descr = request.getParameter("descrizione");
    float voto = Float.parseFloat(request.getParameter("voto"));
    String colore = request.getParameter("colore");
    		
    // Crea una nuova recensione
    Recensione rc = new Recensione(data, descr, voto);

    // Salva la recensione nel database 
    UtenteService us = new UtenteService();
    if (rc != null){
    	//rc.salvaUtente(u);
    	System.out.println(rc.toString());
    	response.sendRedirect("home.jsp");
    	}
	}
%>
</body>
</html>