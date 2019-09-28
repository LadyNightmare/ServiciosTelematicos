<%-- 
    Document   : index
    Created on : Aug 30, 2019, 6:01:02 PM
    Author     : ladynightmare
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="Login" method="post">
            User:<br>
            <input type="text" name="user" required>
            <br>
            Password:<br>
            <input type="password" name="password" required>
            <br><br>
            <% String ra = request.getServletContext().getRealPath("/");%>
            <INPUT TYPE="hidden" NAME ="dire"  VALUE=<%=ra%> >
            <input type="submit" value="Log in">
        </form> 
        <button><a href="register.jsp">Register</a></button>
        <form action="Anon" method="post">
            <input type="submit" value="Read without account">
        </form> 
        
             <div class="footer">
                 <p> Cristina D&iacute; (Universidad de M&aacute;laga) </p>
            <p>Copyright(c)2019</p>
       </div>
        
    </body>
</html>
