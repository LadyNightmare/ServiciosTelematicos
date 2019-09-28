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
@WebServlet(urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
	
        String user = request.getParameter("user");
        System.out.println("user "+ user);
        String password = request.getParameter("password");
        String dir = request.getParameter("dire");
       int i = 0;
       int prim = 0;
        boolean existe = false;
       
           try{  
            if ( (user == null) || (user.trim().length() == 0) || password == null || (password.trim().length()== 0)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
             out = response.getWriter();
            out.println("<p>No se pueden dejar campos vacios</p>\n");
            rd.include(request, response);
           
    }else{
            
                
  
         String directorio = new java.io.File(".").getCanonicalPath();
    System.out.println(directorio);
        File f = new File(dir+"\\log.txt");
                     try{
                    if(!f.exists()){
                    f.createNewFile();
                    prim = 1;
                 }
               
                
                Scanner sc = new Scanner(f);
                sc.useLocale(Locale.ENGLISH);
                sc.useDelimiter("[\n]");
                while (sc.hasNext()){
                    String comp = sc.next();
                    Scanner sw = new Scanner(comp);
                    sw.useLocale(Locale.ENGLISH);
                    sw.useDelimiter("[ ]");
                    while(sw.hasNext()){
                        String namef = sw.next();
                        if(namef.equals(user)){
                            existe = true;
                        }
                    }
                      
                
                            
                }
                sc.close();
               if(!existe){
             
                FileWriter fileWriter = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                if(prim != 1){
                bw.newLine();
                }
                bw.write(user+" "+password);
                bw.close();
                i = 1;
               }
    }catch(IOException e){
        System.out.println("COULD NOT LOG!!");
    }
            
          
     
          if(i>0)
          {
           response.sendRedirect("index.jsp");
          }else{
             RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
            out = response.getWriter();
            out.println("<<p>usuario ya registrado</p>\n");
            rd.include(request, response);
          }
        }
        
    } catch(Exception se)
        {
            se.printStackTrace();
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


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
