<%-- 
    Document   : post
    Created on : 07-feb-2019, 0:45:46
    Author     : Jeronimo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Escibir un post</title>
    </head>
    <body>
        <form action="Posts" method="post">
            Ttulo:
            <br>
            <input type="text" name="titulo">
            <br>
            Texto:
            <br>
            <input type="text" name="texto">
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
