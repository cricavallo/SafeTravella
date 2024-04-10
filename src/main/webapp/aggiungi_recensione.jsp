<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aggiungi Recensione</title>
<link rel="icon" type="image/x-icon" href="img/icon.ico">
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
        margin: 0;
        padding: 0;
    }
    
    .container {
        max-width: 600px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    
    h2 {
        text-align: center;
    }
    
    label {
        font-weight: bold;
        margin-bottom: 5px;
    }
    
    input[type="text"],
    input[type="number"] {
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

    <form action="aggiungi_recensione.jsp" method="post">
        <label>Data:</label>
        <input type="date" name="data" required><br>
        
        <label>Descrizione:</label>
        <textarea name="descrizione" rows="5" required></textarea><br>
        
        <label>Voto:</label>
        <input type="number" name="voto" required><br>
        
        <input type="submit" value="Conferma">
    </form>
</div>

</body>
</html>