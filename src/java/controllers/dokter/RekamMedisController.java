package controllers.dokter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.RekamMedis;

@WebServlet(name = "RekamMedisController", urlPatterns = {"/rekam_medis"})
public class RekamMedisController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        String menu = request.getParameter("menu");
        if (menu == null || menu.isEmpty()) {
            response.sendRedirect("rekam_medis?menu=view");
            return;
        }

        RekamMedis rekamMedisModel = new RekamMedis();
        if ("view".equals(menu)) {
            ArrayList<RekamMedis> rekamMedisList = rekamMedisModel.getAllRekamMedisWithRelations();
            request.setAttribute("rekamMedisList", rekamMedisList);
            request.getRequestDispatcher("/dokter/rekam_medis/view.jsp").forward(request, response);
        } else if ("view_konsultasi".equals(menu)) {
            ArrayList<RekamMedis> rekamMedisList = rekamMedisModel.getAllRekamMedisWithRelations();
            request.setAttribute("rekamMedisList", rekamMedisList);
            request.getRequestDispatcher("/dokter/konsultasi/view.jsp").forward(request, response);
        } else if ("view_konsultasi_pelanggan".equals(menu)) {
            ArrayList<RekamMedis> rekamMedisList = rekamMedisModel.getAllRekamMedisWithRelations();
            request.setAttribute("rekamMedisList", rekamMedisList);
            request.getRequestDispatcher("/pasien/konsultasi/view.jsp").forward(request, response);
        } else if ("edit_konsultasi".equals(menu)) {
            String id = request.getParameter("id");
            RekamMedis rm = rekamMedisModel.find(id);
            if (rm != null) {
                request.setAttribute("rekamMedis", rm);
                request.getRequestDispatcher("/dokter/konsultasi/edit.jsp").forward(request, response);
            } else {
                response.sendRedirect("rekam_medis?menu=view_konsultasi");
            }
        } else {
            response.sendRedirect("rekam_medis?menu=view");
        }
    }

}
