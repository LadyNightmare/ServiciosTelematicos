<%-- 
    Document   : registro
    Created on : 03-feb-2019, 16:31:23
    Author     : Cristina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Blog de noticias</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Esto es el registro.</h1>
        
        <form action="Registro" method="post">
            Introduzca sus credenciales:
            <input type="text" name="usuario" required>
            <input type="password" name="password" required>
            <input type="submit" value="Enviar">
        </form>
        
    </body>
</html>
