<%-- 
    Document   : entry
    Created on : Aug 31, 2019, 10:57:50 PM
    Author     : ladynightmare
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Entry" method="post">
            Title:<br>
            <input type="text" name="title" required>
            <br>
            Text:<br>
            <input type="textarea" name="text" required>
            <br>
            <% String comments = request.getParameter("title");
            //comments.append("comments.txt");
            comments = comments + "comments.txt";%>
            <INPUT TYPE="hidden" NAME ="comments"  VALUE=<%=comments%> >
            <% String ra = request.getServletContext().getRealPath("/");%>
            <INPUT TYPE="hidden" NAME ="dire"  VALUE=<%=ra%> >
            <input type="submit" value="Create">
        </form>
            
             <div class="footer">
                 <p> Cristina D&iacute;az (Universidad de M&aacute;laga) </p>
            <p>Copyright(c)2019</p>
       </div>
    </body>
</html>
