<%-- 
    Document   : index
    Created on : May 20, 2019, 12:34:46 PM
    Author     : ladynightmare
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog de Cristina</title>
    </head>
    <body>
        <h1>Introduce tus credenciales:</h1>
        <form action="Inicio" method="post">
            Usuario:
            <input type="text" name="user">
            <br>
            Contraseña:
            <input type="password" name="pass">
            <br>
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
