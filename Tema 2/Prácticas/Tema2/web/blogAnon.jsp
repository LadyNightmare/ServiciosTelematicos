<%-- 
    Document   : BlogAnon
    Created on : 08-feb-2019, 23:27:13
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
        
         Bienvenido ${sessionScope.usuario}
         
         <br>
         
         Entradas disponibles: ${sessionScope.entradas}
         
         <br>
         
        <button>
            <a href="index.jsp">Inicio</a>
        </button> 
        <button>
            <a href="registro.jsp">Registro</a>
        </button>
    </body>
</html>

