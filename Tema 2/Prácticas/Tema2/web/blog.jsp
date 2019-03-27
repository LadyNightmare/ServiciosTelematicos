<%-- 
    Document   : blog
    Created on : 05-feb-2019, 12:38:22
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
        
         Bienvenido ${sessionScope.usuario}
     
        <button>
            <a href="post.jsp">Escribe un post</a>
        </button>
        <button>
            <a href="index.jsp">Inicio</a>
        </button> 
        <button>
            <a href="registro.jsp">Registro</a>
        </button>
    </body>
</html>

