/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ladynightmare
 */
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 
   private static final long serialVersionUID = 1L;
  
   
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        // get request parameters for userID and password
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String dir = request.getParameter("dire");
        Boolean enc = false;
        
        if ( (user == null) || (user.trim().length() == 0) || password == null || (password.trim().length()== 0)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            PrintWriter out = response.getWriter();
            out.println("<p>No se pueden dejar campos vacios</p>\n");
            rd.include(request, response);
      
    } else {
            
        
        File f = new File(dir + "\\log.txt");
        if(f.exists()){
        Scanner sc = new Scanner (f);
        
        while(sc.hasNext()){
            String us = sc.next();
            String pas = sc.next();
                if(us.equals(user) && pas.equals(password)) {
                    enc = true;
                }
              
                
        }
        if (enc) {
            HttpSession session = request.getSession(true);
            session.putValue("user", user);
            session.putValue("dir", "");
            response.sendRedirect("blog.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            PrintWriter out = response.getWriter();
            out.println("<p>Contrase&ntilde;a o usuario incorrecto</p>\n");
            rd.include(request, response);
        }
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
             PrintWriter out = response.getWriter();
            out.println("<p>No hay registro</p>\n");
            rd.include(request, response); 
            
        }
              
                }
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
