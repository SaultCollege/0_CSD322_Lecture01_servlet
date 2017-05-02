/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.csd322;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fred
 */
@WebServlet(name = "ListUsers", urlPatterns = {"/listUsers"})
public class ListUsers extends HttpServlet {

    private ArrayList<User> users;

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

        ServletContext context = request.getSession().getServletContext();
        users = (ArrayList<User>) context.getAttribute("users");
        if (users == null) {
            users = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                User u = new User("firstname_" + i, "lastname_" + i);
                users.add(u);
            }
            context.setAttribute("users", users);
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListUsers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListUsers at " + request.getContextPath() + "</h1>");
            out.println("<h1>users== " + users + "</h1>");

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Firstname</th>");
            out.println("<th>Lastname</th>");
            out.println("</tr>");
            for (User u : users) {
                out.println("<tr>");
                out.println("<td>");
                out.println(u.getFirstname());
                out.println("</td>");
                out.println("<td>");
                out.println(u.getLastname());
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
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
