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
import models.Pelanggan;

@WebServlet(name = "PelangganController", urlPatterns = {"/pelanggan"})
public class PelangganController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        // Ambil data pelanggan dari model
        Pelanggan pelangganModel = new Pelanggan();
        ArrayList<Pelanggan> pelangganList = pelangganModel.get();

        // Set data pelanggan ke request attribute
        request.setAttribute("pelangganList", pelangganList);

        // Forward ke view.jsp
        request.getRequestDispatcher("/dokter/pelanggan/view.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.sendRedirect(request.getContextPath() + "/index.jsp");
   }

}
