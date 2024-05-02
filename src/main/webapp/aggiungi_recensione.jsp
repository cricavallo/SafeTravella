<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="backend.Recensione" %> 
<%@ page import="backend.Utente" %>
<%@ page import="backend.Citta" %> 
<%@ page import="backend.ElencoCitta" %> 
<%@ page import="backend.servlets.RecensioneService" %> 
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
        position: relative;
    }

	img {
    	position: absolute;
    	bottom: 0;
    	left: 0;
    	z-index: -1;
    	width: 100%;
    	height: 100%;
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
<%	RecensioneService rs = new RecensioneService(); %>
<img src="img/back2.jpg">
<div class="container">
    <h2>Aggiungi recensione</h2>
    <h3></h3>
	<label>Città:</label>
    <form action="aggiungi_recensione.jsp" method="post">
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
        <label>Data del viaggio:</label>
        <input type="date" name="data" required max="<%= LocalDate.now() %>"><br>
        
        <label>Descrizione:</label>
        <textarea name="descrizione" rows="5" required></textarea><br>
        
        <label>Voto:</label>
        <input type="number" name="voto" required min="0" max="10" step="0.5"><br> 
        
        <input type="submit" value="Conferma">
    </form>
</div>


<%
if ("POST".equals(request.getMethod())) {
	// Controllo sulla data di nascita
	Utente u = (Utente)request.getSession().getAttribute("DATI_UTENTE");
	Citta city = null;
	Recensione rc = new Recensione();
	rc.setU(u);
	LocalDate dataViaggio = null;
	String dataV = null;
	try {
    	dataViaggio = LocalDate.parse(request.getParameter("data"));
    	dataV = String.valueOf(dataViaggio);
	} catch (Exception e) {
		out.println("<h3 class='error-message'>Errore nella data di nascita!</h3>");
    	out.println("</body></html>");
    	return;
	}

    String descr = request.getParameter("descrizione");
    float voto = Float.parseFloat(request.getParameter("voto"));
    rc.setDesc(descr);
    rc.setVoto(voto);
    rc.setDataS(dataV);

	String nomeC = request.getParameter("Citta");
	city = rs.select_Citta(nomeC);
	rc.setC(city);
    if (rc != null){
    	rs.insert_Recensione(rc, city);
    	response.sendRedirect("home.jsp");
    	}
	}
%>
	
</body>
</html>