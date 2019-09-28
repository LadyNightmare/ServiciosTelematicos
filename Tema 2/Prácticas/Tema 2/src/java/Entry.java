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
import java.time.LocalDateTime;

/**
 *
 * @author ladynightmare
 */
@WebServlet(urlPatterns = {"/Entry"})
public class Entry extends HttpServlet {

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

        String title = request.getParameter("title");
        String author = (String) request.getSession().getValue("user");
        String text = request.getParameter("text");
        String date = LocalDateTime.now().toString();
        String commentFile = request.getParameter("title") + "comments.txt";
        String dir = request.getParameter("dire");
        int i = 0;
        int prim = 0;

        try {

            File f = new File(dir + "\\entries.txt");
            try {

                FileWriter fileWriter = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                if (prim != 1) {
                    bw.newLine();
                }
                bw.write(title + "\n" + author + "\n" + date + "\n" + text + "\n" + commentFile + "\n" + "&");
                bw.close();
                i = 1;

            } catch (IOException e) {
                System.out.println("COULD NOT CREATE ENTRY");
            }

            response.sendRedirect("blog.jsp");

        } catch (Exception se) {
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
