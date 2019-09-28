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
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Registration</title>
    </head>
    <body onload="document.registration.name.focus();>
        <form action="Register" method="post">
            User:<br>
            <input type="text" name="user">
            <br>
            Password:<br>
            <input type="password" name="password">
            <br>
            Repeat password:<br>
            <input type="password" name="password2">
            
            Email<br>
            <input type="text" name="ema">
            <br>
            Repeat email:<br>
            <input type="text" name="ema">
            
            Country: <br> 
            <select name="country">
                     <option selected="" value="Default">(Select your country)</option>
                     <option value="ES">Spain</option>
                     <option value="CA">Canada</option>
                     <option value="IN">India</option>
                     <option value="RU">Russia</option>
                     <option value="US">USA</option>
                 </select>
            Post Code: <br> <input type="text" name="zip">
            
            <% String ra = request.getServletContext().getRealPath("/");%>
            
            <INPUT TYPE="hidden" NAME ="dire"  VALUE=<%=ra%> >
            <input type="submit" value="Registrar" onclick ="return formValidation()">
        </form> 
            <button><a href="index.jsp">Back to login</a></button>
            
             <div class="footer">
                 <p> Cristina D&iacute;az (Universidad de M&aacute;laga) </p>
            <p>Copyright(c)2019</p>
       </div>
    </body>
</html>
