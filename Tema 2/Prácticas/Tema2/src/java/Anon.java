/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jeronimo
 */
@WebServlet(urlPatterns = {"/Anon"})
public class Anon extends HttpServlet {

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
            out.println("<title>Servlet Anon</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String directorio_de_ejecucion_de_la_aplicacion = new java.io.File( "." ).getCanonicalPath();
            
            out.println(directorio_de_ejecucion_de_la_aplicacion);
            
            File file = new File("zentradas.txt");

		Scanner sc = new Scanner(file);

		sc.useDelimiter("[\n]");
		
		String titulo = null, autor = null, fecha = null, texto = null, fichero = null;
		
		String autorC, fechaC, comentario;
		
		Scanner scanner;

		while(sc.hasNext()) {
			
			out.println("\nPOST\n");

			titulo = sc.next();
			autor = sc.next();
			fecha = sc.next();
			texto = sc.next();
			fichero = sc.next();
			sc.next();
			
			out.println(titulo);
			out.println(autor);
			out.println(fecha);
			out.println(texto.substring(0, 100));
			
			File ficher = new File (fichero);
			
			scanner = new Scanner(ficher);
			scanner.useDelimiter("[\n]");
			
			while(scanner.hasNext()) {

				autorC = scanner.next();
				fechaC = scanner.next();
				comentario = scanner.next();
				
				out.println("\nCOMENTARIO\n");
				
				out.println(autorC);
				out.println(fechaC);
				out.println(comentario);
				
				scanner.next();

			}
			
			scanner.close();

		}

		sc.close();
                
            HttpSession session = request.getSession(true);
            String user = "anonimo";
            session.putValue("usuario", user);
            
            if (true) {
                    
                   session.putValue("entradas", 0);
                    
                }
            
            response.sendRedirect("blogAnon.jsp");
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
