<%-- 
    Document   : register
    Created on : Aug 30, 2019, 6:13:11 PM
    Author     : ladynightmare
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <form action="Register" method="post">
            User:<br>
            <input type="text" name="user" required>
            <br>
            Password:<br>
            <input type="password" name="password" required>
            <br><br>
            <% String ra = request.getServletContext().getRealPath("/");%>
            <INPUT TYPE="hidden" NAME ="dire"  VALUE=<%=ra%> >
            <input type="submit" value="Register">
        </form> 
            <button><a href="index.jsp">Back to login</a></button>
            
             <div class="footer">
                 <p> Cristina D&iacute; (Universidad de M&aacute;laga) </p>
            <p>Copyright(c)2019</p>
       </div>
    </body>
</html>
