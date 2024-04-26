<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="backend.Utente" %> 
<%@ page import="backend.servlets.RecensioneService" %> 
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione</title>
<link rel="icon" type="image/x-icon" href="img/logo.ico">
<style>
    /* Cambia il font del testo */
    body {
        font-family: Arial, sans-serif; 
        text-align: center; 
        margin-top: 100px; /* Spazio sopra il form */
    }

    /* Stile per i bottoni */
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
	
    input{
    	font-family: Arial, sans-serif; /* Stessa famiglia di font usata per il testo */
    	font-size: 14px; /* Dimensione del testo */
    	padding: 5px; /* Spaziatura all'interno dell'input */
    	border-radius: 5px; /* Bordo arrotondato */
    	border: 1px solid #ccc; /* Bordo grigio */
	}
    
    /* Stile per il div contenitore */
    .form-container {
        display: inline-block; /* Permette al div di contenere gli elementi all'interno */
        text-align: left; /* Allinea il testo a sinistra all'interno del div */
    }

    /* Stile per messaggio di conferma */
    .success-message {
        color: green;
    }
</style>
</head>
<body>
<h2>Registrazione a SafeTravella</h2>
<form action="registrazione.jsp" method="POST">
    <div class="form-container">
        <label for="cognome">Cognome:</label><br/>
        <input type="text" name="cognome" required style="width: 300px;"/><br/><br/>
        <label for="nome">Nome:</label><br/>
        <input type="text" name="nome" required style="width: 300px;"/><br/><br/>
        <label for="email">Email:</label><br/>
        <input type="email" name="email" required style="width: 300px;"/><br/><br/>
        <label for="user">User:</label><br/>
        <input type="text" name="user" required style="width: 300px;"/><br/><br/>
        <label for="pwd">Password:</label><br/>
        <input type="password" name="pwd" required style="width: 300px;"/><br/><br/>
        <label for="nazionalita">Nazionalità:</label><br/>
        <input type="text" name="nazionalita" required style="width: 300px;"/><br/><br/>
        <label for="data_nascita">Data di nascita:</label><br/>
        <input type="date" name="data_nascita" required max="<%= LocalDate.now() %>" style="width: 300px;"/><br/><br/>
        <input type="submit" value="Registrati" class="btn"/>
    </div>
</form>


<%
// Verifica se la richiesta è una richiesta POST
if ("POST".equals(request.getMethod())) {
    // Prendi i valori dai parametri della richiesta
    String cognome = request.getParameter("cognome");
    String nome = request.getParameter("nome");
    String email = request.getParameter("email");
    String user = request.getParameter("user");
    String pwd = request.getParameter("pwd");
    String nazionalita = request.getParameter("nazionalita");
    
    // Controllo sulla data di nascita
    LocalDate dataNascita = null;
    String dataS = null;
    try {
        dataNascita = LocalDate.parse(request.getParameter("data_nascita"));
        dataS = String.valueOf(dataNascita);
    } catch (Exception e) {
        out.println("<h3 class='error-message'>Errore nella data di nascita!</h3>");
        out.println("</body></html>");
        return;
    }

    // Crea un nuovo oggetto Utente
    Utente u = new Utente(cognome, nome, email, user, pwd, nazionalita, dataS);

    // Salva l'utente nel database 
    RecensioneService rs = new RecensioneService();
    if (u != null){
    	rs.insert_UtenteRagazza(u);
    	System.out.println(u.toString());
    	response.sendRedirect("index.jsp");
    	}
	}
    %>
    
</body>
</html>
