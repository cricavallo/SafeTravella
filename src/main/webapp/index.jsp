<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="backend.UtenteService" %>
<%@ page import="backend.Utente" %>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SafeTravella</title>
<link rel="icon" type="image/x-icon" href="img/icon.ico">
<style>

    body {
        font-family: Arial, sans-serif; 
        text-align: center; 
        margin-top: 100px; /* Spazio sopra il form */
    }

    .btn {
        font-family: Arial, sans-serif; 
        font-size: 14px; 
        background-color: #007bff; 
        color: #fff; 
        border: none; 
        padding: 10px 20px; /* Spaziatura all'interno del pulsante */
        cursor: pointer; /* Cambia il cursore quando si passa sopra */
        border-radius: 5px; /* Bordo arrotondato */
    }
    

    input[type="text"],
    input[type="password"] {
        font-family: Arial, sans-serif; /* Stessa famiglia di font usata per il testo */
        font-size: 14px; /* Dimensione del testo */
        padding: 8px; /* Spaziatura all'interno dell'input */
        border-radius: 5px; /* Bordo arrotondato */
        border: 1px solid #ccc; /* Bordo grigio */
        width: 250px; /* Larghezza degli input */
    }

    .form-container {
        display: inline-block; /* Permette al div di contenere gli elementi all'interno */
        text-align: left; /* Allinea il testo a sinistra all'interno del div */
    }

    .register-btn {
        font-family: Arial, sans-serif;
        font-size: 14px;
        background-color: #28a745; /* Colore verde */
        color: #fff;
        border: none;
        padding: 10px 20px;
        cursor: pointer;
        border-radius: 5px;
        text-decoration: none; /* Rimuove la sottolineatura */
    }
</style>
</head>
<body>
<h2>Ciao, stai accedendo al sito SafeTravella</h2>
<% 
String n = request.getParameter("user");
String pwd = request.getParameter("pwd");
if(n == null) {%>
<form action="index.jsp" method="POST">
    <div class="form-container">
        <h3>Effettua l'autenticazione</h3>
        <label for="user">User:</label><br/>
        <input type="text" name="user"/><br/><br/>
        <label for="pwd">Password:</label><br/>
        <input type="password" name="pwd"/><br/><br/>
        <input type="submit" value="Login" class="btn"/><br/><br/>
        <h4>Non sei ancora registrato?
        </h4> <a href="registrazione.jsp" class="register-btn">Registrati</a>
    </div>
</form>

<%} //controllo con database
	else { 
    UtenteService s = new UtenteService();
    Utente u = s.getUtente(n, pwd);
    
    if(u == null) {
        response.sendRedirect("index.jsp");
    } else {
        request.getSession().setAttribute("DATI_UTENTE", u);
        response.sendRedirect("home.jsp");
    }
}%>

	<script>
    	console.log("ciao");
    </script>
</body>
</html>