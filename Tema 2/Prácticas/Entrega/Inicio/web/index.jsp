<%-- 
    Document   : index
    Created on : Apr 2, 2019, 9:29:52 PM
    Author     : ladynightmare
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
            <form action="Login" method="post">
                Usuario: <input type="text" name="usuario" required>
                <br>
                Contraseña: <input type="password" name="password" required>
                <br>
                <input type="submit" value="Enviar">
                <button>
                <a href="registro">Registrarse</a>
            </button>
            <button>
                <a href="Blog.jsp">Blog de noticias</a>
            </button>
            </form>
        <input type="hidden" name="dire"  VALUE=<%request.getServletContext().getRealPath("/");%> >
        
    </body>
</html>