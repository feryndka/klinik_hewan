package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

@WebServlet(name = "AuthController", urlPatterns = {"/AuthController"})
public class AuthController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lakukan send redirect langsung ke jsp index
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        // Lakukan pengecekan apakah var action bernilai "login"
        if ("login".equals(action)) {
            // Lakukan get request parameter username
            String username = request.getParameter("username");
            // Lakukan get request parameter password 
            String password = request.getParameter("password");
            
            // Menggunakan model User untuk mencari data berdasarkan username dan password
            User userModel = new User();
            userModel.where("username = '" + username + "' AND password = '" + password + "'");
            User user = userModel.get().stream().findFirst().orElse(null);
            
            // Pengecekan apakah user ditemukan
            if (user != null) {
                // Simpan data user ke dalam session
                HttpSession session = request.getSession();
                session.setAttribute("user", user.getUsername());
                session.setAttribute("role", user.getRole());

                // Redirect berdasarkan role user
                if ("dokter".equals(user.getRole())) {
                    response.sendRedirect(request.getContextPath() + "/user?menu=view_dokter");
                } else if ("pasien".equals(user.getRole())) {
                    response.sendRedirect(request.getContextPath() + "/user?menu=view_pasien");
                } else {
                    response.sendRedirect(request.getContextPath() + "/index.jsp?error=3"); // Role tidak diketahui
                }
            } else {
                // Jika login gagal, redirect ke halaman login dengan pesan error
                response.sendRedirect(request.getContextPath() + "/index.jsp?error=1");
            }
        } else if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            // Lakukan pengecekan jika session tidak bernilai null
            if (session != null) {
                // Lakukan pengecekan jika session invalidate dengan memanggil fungsi bawaannya
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
