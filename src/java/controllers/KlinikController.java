package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.Klinik;

@WebServlet(name = "KlinikController", urlPatterns = {"/klinik"})
public class KlinikController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // lakukan request parameter menu
        String menu = request.getParameter("menu");
        // buat objek Klinik
        Klinik klinik = new Klinik();

        if ("view".equals(menu)) {
            // melakukan get data dari db dengan memanggil func get
            ArrayList<Klinik> kln = klinik.get();
            // dari data yang telah di get diatas, lakukan set atribut ke kln 
            request.setAttribute("kln", kln);
            // (mengarahkan ke halaman dashbord (view.jsp))
            request.getRequestDispatcher("klinik/view.jsp").forward(request, response);

        } else if ("add".equals(menu)) {
            // (mengarahkan ke halaman tambah klinik (add.jsp))
            request.getRequestDispatcher("klinik/add.jsp").forward(request, response);

        } else if ("edit".equals(menu)) {
            // lakukan request parameter id
            String id = request.getParameter("id");
            // lakukan search id sebelumnya menggunakan func find pada objek yang telah dibuat
            Klinik foundKlinik = klinik.find(id);
            if (foundKlinik != null) {
                // lakukan set atribut klinik
                request.setAttribute("klinik", foundKlinik);
                // mengarahkan ke halaman edit klinik (edit.jsp)
                request.getRequestDispatcher("klinik/edit.jsp").forward(request, response);
            } else {
                // mengarahkan ke halaman dashboard (view.jsp)
                response.sendRedirect("klinik?menu=view");
            }

        } else {
            // mengarahkan ke halaman dashboard (view.jsp)
            response.sendRedirect("klinik?menu=view");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // lakukan request parameter action
        String action = request.getParameter("action");
        // buat objek Klinik
        Klinik klinik = new Klinik();

        if ("add".equals(action)) {
            // lakukan request parameter nama
            String nama = request.getParameter("nama");
            // lakukan request parameter alamat
            String alamat = request.getParameter("alamat");
            // lakukan request parameter jam operasional
            String jamOperasional = request.getParameter("jamOperasional");

            // set kolom nilai form dari objek yang telah dibuat menggunakan func set
            klinik.setNama(nama);
            klinik.setAlamat(alamat);
            klinik.setJamOperasional(jamOperasional);
            // panggil func insert pada objek yang telah dibuat
            klinik.insert();

        } else if ("edit".equals(action)) {
            // lakukan request parameter id
            int id = Integer.parseInt(request.getParameter("id"));
            // lakukan request parameter nama
            String nama = request.getParameter("nama");
            // lakukan request parameter alamat
            String alamat = request.getParameter("alamat");
            // lakukan request parameter jam operasional
            String jamOperasional = request.getParameter("jamOperasional");

            // set kolom nilai form dari objek yang telah dibuat menggunakan func set
            klinik.setId(id);
            klinik.setNama(nama);
            klinik.setAlamat(alamat);
            klinik.setJamOperasional(jamOperasional);
            // panggil func update pada objek yang telah dibuat
            klinik.update();
        }
        
        // mengarahkan ke halaman dashboard (view.jsp)
        response.sendRedirect("klinik?menu=view");
    }
}
