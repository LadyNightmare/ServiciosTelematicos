<%-- 
    Document   : index
    Created on : 03-feb-2019, 23:46:55
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
        <div><h1>Blog de noticias</h1></div>
        <form action="Login" method="post">
            Introduzca sus credenciales:
            <input type="text" name="usuario" required>
            <input type="password" name="password" required>
            <input type="hidden" name="dire"  VALUE=<%request.getServletContext().getRealPath("/");%> >
            <input type="submit" value="Autenticarse">
        </form>
        <button>
            <a href="registro.jsp">Registrarse</a>
        </button>
        <form action="Anon" method="post">
            <input type="submit" value="Blog de noticias">
        </form>

    </body>
</html>
