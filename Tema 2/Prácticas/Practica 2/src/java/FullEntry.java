/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Locale;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ladynightmare
 */
@WebServlet(urlPatterns = {"/FullEntry"})
public class FullEntry extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String title = "esfd";
        String author = "";
        String date = "";
        String text = "";
        String commentsFile = "";
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FullEntry</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
            out.println("</head>");
            out.println("<body>");

            String ra = request.getServletContext().getRealPath("/");

            File f = new File(ra + "entries.txt");

            Scanner sc = new Scanner(f);
            sc.useLocale(Locale.ENGLISH);
            sc.useDelimiter("[&]");
            Scanner comments;
            Scanner comment;
            Scanner sw;
            while (!title.equals(request.getParameter("title"))) {
                String comp = sc.next();
                sw = new Scanner(comp);
                sw.useLocale(Locale.ENGLISH);
                sw.useDelimiter("[\n]");
                while (sw.hasNext()) {
                    title = sw.next();
                    author = sw.next();
                    date = sw.next();
                    text = sw.next();
                    commentsFile = sw.next();
                }
            }
            out.println("<h1>" + title + "</h1>");
            out.println("<h2>" + author + "</h2>");
            out.println("<h3>" + date + "</h3>");
            out.println("<h4>" + text + "</h4>");

            
            File file = new File(ra + commentsFile);
            file.createNewFile();
            if (file.length() == 0) {

                out.println("No comments.");  
                
        } else {
        out.println("Comments");
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
        out.println("<h3>" + commentAuthor + "</h3>");
        out.println("<h4>" + commentDate + "</h4>");
        out.println("<h5>" + commentToShow + "</h5>");
                                }
                            }
                        }
            
            String fileName = ra + commentsFile;
            
            
            out.println("<form action=\"Comment\" method=\"post\">");
            out.println("Comment:<br>\n" +
"            <input type=\"text\" name=\"comment\" required>");
            out.println("<INPUT TYPE=\"hidden\" NAME =\"commentsFile\"  VALUE=" + fileName + ">");
            out.println("<INPUT TYPE=\"hidden\" NAME =\"dire\"  VALUE=" + ra + ">");
            out.println("<input type=\"submit\" value=\"Comment\">\n" +
"        </form>");
            out.println("<div class=\"footer\">\n" +
"                 <p> Cristina D&iacute;az (Universidad de M&aacute;laga) </p>\n" +
"            <p>Copyright(c)2019</p>\n" +
"       </div>");
            out.println("</body>");
            out.println("</html>");
            
            sc.close();

        } catch (IOException e) {
            System.out.println("COULD NOT LOG!!");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
