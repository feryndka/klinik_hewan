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
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (null == action) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else switch (action) {
            case "login" -> {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                // Use User model to find user by username and password
                User userModel = new User();
                userModel.where("username = '" + username + "' AND password = '" + password + "'");
                User user = userModel.get().stream().findFirst().orElse(null);
                
                // Check if user is found
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    session.setAttribute("username", user.getUsername());
                    session.setAttribute("role", user.getRole());
                    session.setAttribute("pelanggan", user.getPelanggan());
                    session.setAttribute("dokter", user.getDokter());
                    
                    if (null == user.getRole()) { // Redirect based on user role
                        response.sendRedirect(request.getContextPath() + "/index.jsp?error=3"); // Unknown role
                    } else switch (user.getRole()) {
                        case "dokter" -> response.sendRedirect(request.getContextPath() + "/user?menu=view_dokter");
                        case "pasien" -> response.sendRedirect(request.getContextPath() + "/user?menu=view_pasien");
                        default -> response.sendRedirect(request.getContextPath() + "/index.jsp?error=3"); // Unknown role
                    }
                } else {
                    // If login fails, redirect to login page with error message
                    response.sendRedirect(request.getContextPath() + "/index.jsp?error=1");
                }
            }
            case "logout" -> {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }   response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
            default -> response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}