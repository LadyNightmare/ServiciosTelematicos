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
        <link rel="stylesheet" type="text/css" href="style.css">
         <script src="login.js"></script>
        <title>Login</title>
    </head>
    <body onload="tiempo()" >
        <div id="reloj" style="font-size:20px;"></div>
        <div > <p> Last modified 01-09-2019 <p> </div>
        <form action="Login" method="post">
            User:<br>
            <input type="text" name="user">
            <br>
            Password:<br>
            <input type="password" name="password">
            <br><br>
            <% String ra = request.getServletContext().getRealPath("/");%>
            <INPUT TYPE="hidden" NAME ="dire"  VALUE=<%=ra%> >
            <input type="submit" value="Log in" onclick=" return validateForm()">
            <input type="button" onclick="javascript:window.location.href='register.jsp'; return false;" value="Register">
        </form> 
        <form action="Anon" method="post">
            <input type="submit" value="Read without account">
        </form> 
        
             <div class="footer">
                 <p> Cristina D&iacute;az (Universidad de M&aacute;laga) </p>
            <p>Copyright(c)2019</p>
       </div>
        
    </body>
</html>
