/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ladynightmare
 */
@WebServlet(urlPatterns = {"/registro"})
public class registro extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet registro</title>");            
            out.println("</head>");
            out.println("<body>");
            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");

            int num = 0;
            boolean found = false;

            File file = new File("usuarios.txt");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                    num = 1;
                }

                Scanner sc = new Scanner(file);
                sc.useLocale(Locale.ENGLISH);
                sc.useDelimiter("[\\n]");

                while (sc.hasNext()) {
                    String line = sc.next();
                    Scanner sw = new Scanner(line);
                    sw.useLocale(Locale.ENGLISH);
                    sw.useDelimiter("[\\s]");
                    String user = sw.next();
                    String pas = sw.next();
                    if (user.equals(usuario)) {

                        found = true;

                    }

                }
                sc.close();
                if (!found) {

                    FileWriter fileWriter = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(fileWriter);
                    if (num != 1) {
                        bw.newLine();
                    }
                    bw.write(usuario + " " + password);
                    bw.close();
                    
                    response.sendRedirect("index.jsp");

                } else {
                    
                RequestDispatcher rd = getServletContext().getRequestDispatcher("index.jsp");
                out.println("<p id='warning'>usuario ya registrado</p>\n");
                rd.include(request, response);
                
            }
            } catch (IOException e) {
                System.out.println("COULD NOT REGISTER!!");
            }
            out.println("</body>");
            out.println("</html>");
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
