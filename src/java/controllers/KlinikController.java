package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import models.Klinik;

@WebServlet(name = "KlinikController", urlPatterns = {"/klinik"})
public class KlinikController extends HttpServlet {

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
            // Lakukan send redirect ke view, gunakan klinik?
            response.sendRedirect("klinik?menu=view");
            return;
        }

        Klinik klinikModel = new Klinik();

        if ("view".equals(menu)) {
            ArrayList<Klinik> kln = klinikModel.get();
            request.setAttribute("klinik", kln);
            request.getRequestDispatcher("/dokter/klinik/view.jsp").forward(request, response);

        } else if ("add".equals(menu)) {
            request.getRequestDispatcher("/dokter/klinik/add.jsp").forward(request, response);

        } else if ("edit".equals(menu)) {
            String id = request.getParameter("id");
            Klinik klinik = klinikModel.find(id);
            if (klinik != null) {
                request.setAttribute("klinik", klinik);
                request.getRequestDispatcher("/dokter/klinik/edit.jsp").forward(request, response);
            } else {
                response.sendRedirect("klinik?menu=view");
            }

        } else {
            response.sendRedirect("klinik?menu=view");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String action = request.getParameter("action");
        Klinik klinikModel = new Klinik();

        if ("add".equals(action)) {
            String nama = request.getParameter("nama");
            String alamat = request.getParameter("alamat");
            String jamOperasional = request.getParameter("jamOperasional");

            klinikModel.setNama(nama);
            klinikModel.setAlamat(alamat);
            klinikModel.setJamOperasional(jamOperasional);
            klinikModel.insert();

        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nama = request.getParameter("nama");
            String alamat = request.getParameter("alamat");
            String jamOperasional = request.getParameter("jamOperasional");

            klinikModel.setId(id);
            klinikModel.setNama(nama);
            klinikModel.setAlamat(alamat);
            klinikModel.setJamOperasional(jamOperasional);
            klinikModel.update();

        // Lakukan pengecekan apakah var action bernilai "delete"
        } else if ("delete".equals(action)) {
            // var id menyimpan nilai request parameter "id", lakukan juga parseInt
            int id = Integer.parseInt(request.getParameter("id"));
            // Dari object Product yang telah dibuat sebelumnya, lakukan setId dengan nilai var id
            klinikModel.setId(id);
            // Dari object Product yang telah dibuat sebelumnya, panggil method delete
            klinikModel.delete();
        }

        response.sendRedirect("klinik?menu=view");
    }
}
