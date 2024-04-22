<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
<link rel="icon" type="image/x-icon" href="img/logo.ico">
</head>
<body>

<% 		session = request.getSession();
        session.invalidate(); // Invalida la sessione dell'utente
        response.sendRedirect("index.jsp"); // Reindirizza alla pagina di login
        %>
        
</body>
</html>