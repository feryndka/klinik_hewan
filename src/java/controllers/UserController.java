/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author Asus
 */
@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Inisialisasi object HttpSession untuk melakukan request session
        HttpSession session = request.getSession();
        // Lakukan pengecekan apakah var session untuk atribut "user" bernilai null
        if (session == null || session.getAttribute("user") == null) {
            // Lakukan send redirect secara langsung ke index.jsp
            response.sendRedirect("index.jsp");
            return;
        }

        String menu = request.getParameter("menu");
        // Lakukan pengecekan jika var menu bernilai null atau kosong
        if (menu == null || menu.isEmpty()) {
            // Lakukan send redirect ke index.jsp
            response.sendRedirect("index.jsp");
            return;
        }

        if ("view_dokter".equals(menu)) {
            request.getRequestDispatcher("/dokter/view.jsp").forward(request, response);
        } else if ("view_pasien".equals(menu)) {
            request.getRequestDispatcher("/pasien/view.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

}
