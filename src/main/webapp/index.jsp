<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="backend.servlets.RecensioneService" %>
<%@ page import="backend.Utente" %>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SafeTravella</title>
<link rel="icon" type="image/x-icon" href="img/logo.ico">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<style>

    body {
    	background-color: white;
        font-family: Arial, sans-serif; 
        text-align: center; 
    }

    .btn {
        font-family: Arial, sans-serif; 
        font-size: 16px; 
        background-color: rgb(255, 159, 207);
    	border: 2px solid rgb(255, 128, 192); 
        color: white; /*colore del testo*/
        padding: 10px 20px; /* Spaziatura all'interno del pulsante */
        cursor: pointer; /* Cambia il cursore quando si passa sopra */
        border-radius: 5px; /* Bordo arrotondato */
    }
    
    .btn:hover{
    	background-color: rgb(255, 128, 192);
    }
    h2{
    	margin-top: 80px;
    	font-size: 25px;
    }
    
    h3{
    	font-size: 20px;
    }
 
    input[type="text"],
    input[type="password"] {
        font-family: Arial, sans-serif; /* Stessa famiglia di font usata per il testo */
        font-size: 16px; /* Dimensione del testo */
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
        font-size: 16px;
        background-color: #b000b0;
    	border: 2px solid purple;
        color: white;
        padding: 10px 20px;
        cursor: pointer;
        border-radius: 5px;
        text-decoration: none; /* Rimuove la sottolineatura */
    }
    .register-btn:hover{
    	background-color: purple;
    }
    label{
    	font-size: 18px;
    }
    
    .show-btn {
        font-family: Arial, sans-serif;
        font-size: 14px;
        background-color: #fff;
        color: black;
        padding: 8px 16px;
        cursor: pointer;
        border-radius: 5px;
    }
    
    .show-btn:hover{
        background-color: rgb(211, 211, 211);
    }
    
    .eye-icon {
        position: absolute; /* Posizione assoluta per posizionare l'icona all'interno dell'input */
        top: 50%; /* Posiziona l'icona verticalmente al centro */
        right: 10px; /* Posiziona l'icona a 10px dalla destra */
        transform: translateY(-50%); /* Centra verticalmente l'icona */
        cursor: pointer; /* Cambia il cursore quando si passa sopra */
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
        <div style="position: relative;"> <!-- Contenitore per l'input della password e il pulsante Show -->
            <input type="password" name="pwd" id="pwdInput"/>
            <button type="button" class="show-btn" onclick="togglePasswordVisibility()">
                <i class="far fa-eye"></i> 
            </button>
        </div>
        <br/><br/>
        <input type="submit" value="Login" class="btn"/><br/><br/> <!-- Spostato qui -->
        <h4>Non sei ancora registrato?</h4> 
        <a href="registrazione.jsp" class="register-btn">Registrati</a>
    </div>
</form>

<%} //controllo con database
	else { 
    RecensioneService s = new RecensioneService();
    Utente u = s.select_Utente(n, pwd);
    
    if(u == null) {
        response.sendRedirect("index.jsp");
    } else {
        request.getSession().setAttribute("DATI_UTENTE", u);
        response.sendRedirect("home.jsp");
    }
}%>

	<script>
    function togglePasswordVisibility() {
        var pwdInput = document.getElementById("pwdInput");
        var eyeIcon = document.querySelector(".show-btn i");

        if (pwdInput.type == "password") {
            pwdInput.type = "text";
            eyeIcon.classList.remove("fa-eye");
            eyeIcon.classList.add("fa-eye-slash");
        } else {
            pwdInput.type = "password";
            eyeIcon.classList.remove("fa-eye-slash");
            eyeIcon.classList.add("fa-eye");
        }
    }
</script>
</body>
</html>