<%-- 
    Document   : blog
    Created on : Aug 31, 2019, 12:26:59 PM
    Author     : ladynightmare
--%>

<%@page import="java.io.IOException"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%-- <button><a href="index.jsp">Login</a></button>
        <br>--%>
        <button><a href="register.jsp">Register</a></button>
        <br>
        <form action="Logout" method="post">
            <input type="submit" value="Log out">
        </form>
        <button><a href="entry.jsp">Create entry</a></button>
        <br>
        ${sessionScope.user}

        <%  String ra = request.getServletContext().getRealPath("/");
            session.setAttribute("dir", ra);

            File f = new File(ra + "entries.txt");
            f.createNewFile();
            try {
                if (f.length() == 0) {
        %>
        No entries.
        <%
            }

            Scanner sc = new Scanner(f);
            sc.useLocale(Locale.ENGLISH);
            sc.useDelimiter("[&]");
            Scanner comments;
            Scanner comment;
            while (sc.hasNext()) {
                String comp = sc.next();
                Scanner sw = new Scanner(comp);
                sw.useLocale(Locale.ENGLISH);
                sw.useDelimiter("[\n]");
                while (sw.hasNext()) {
                    String title = sw.next();
                    String author = sw.next();
                    String date = sw.next();
                    String text = sw.next();
                    String textToShow = text.substring(0, Math.min(text.length(), 99));
                    String commentsFile = sw.next();
        %>
        <h1><%=title%></h1>
        <h2><%=author%></h2>
        <h3><%=date%></h3>
        <h4><%=textToShow%></h4>

        <form action="FullEntry" method="post">
            <input type="hidden" name="title" value=<%=title%>>
            <input type="submit" value="See more">
        </form>
        <%
            
            File file = new File(ra + commentsFile);
            file.createNewFile();
            if (file.length() == 0) {
        %>
        No comments.
        <%
        } else {
        %>
        Comments
        <%
        comments = new Scanner(file);
        comments.useLocale(Locale.ENGLISH);
        comments.useDelimiter("[&]");
        while (comments.hasNext()) {
            String myComment = comments.next();
            System.out.println(myComment);
            comment = new Scanner(myComment);
            comment.useLocale(Locale.ENGLISH);
            comment.useDelimiter("[\n]");
            String commentAuthor;
            String commentDate;
            String commentToShow;
            while (comment.hasNext()) {
                commentAuthor = comment.next();
                commentDate = comment.next();
                commentToShow = comment.next();
        %>
        <h2><%=commentAuthor%></h2>
        <h3><%=commentDate%></h3>
        <h4><%=commentToShow%></h4>
        <%
                                }
                            }
                        }
         
                    }
                    
                    
                }
                sc.close();

            } catch (IOException e) {
                System.out.println("COULD NOT LOG!!");
            }
        %>

             <div class="footer">
                 <p> Cristina D&iacute; (Universidad de M&aacute;laga) </p>
            <p>Copyright(c)2019</p>
       </div>
        
    </body>
</html>
