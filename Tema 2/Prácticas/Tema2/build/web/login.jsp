<%-- 
    Document   : Login
    Created on : 05-feb-2019, 12:34:11
    Author     : Jeronimo
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
        <h1>Esto es el login.</h1>
        
        <form action="Login" method="post">
            Introduzca sus credenciales:
            <input type="text" name="usuario" required>
            <input type="password" name="password" required>
            <% String ra = request.getServletContext().getRealPath("/");%>
                    <INPUT TYPE="hidden" NAME ="dire"  VALUE=<%=ra%> >
            <input type="submit" value="Enviar">
        </form>
        
    </body>
</html>
