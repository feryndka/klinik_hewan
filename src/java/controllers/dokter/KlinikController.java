package controllers.dokter;

import java.io.IOException;
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
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        String menu = request.getParameter("menu");
        if (menu == null || menu.isEmpty()) {
            response.sendRedirect("klinik?menu=view");
            return;
        }

        Klinik klinikModel = new Klinik();
        // view klinik
        if ("view".equals(menu)) {
            ArrayList<Klinik> kln = klinikModel.get();
            request.setAttribute("klinik", kln);
            request.getRequestDispatcher("/dokter/klinik/view.jsp").forward(request, response);
        // add klinik
        } else if ("add".equals(menu)) {
            request.getRequestDispatcher("/dokter/klinik/add.jsp").forward(request, response);
        // edit klinik
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

            // Set values in the model
            klinikModel.setNama(nama);
            klinikModel.setAlamat(alamat);
            klinikModel.setJamOperasional(jamOperasional);

            // Insert into database
            klinikModel.insert();  // Insert data

        } else if ("edit".equals(action)) {
            int idKlinik = Integer.parseInt(request.getParameter("id"));
            String nama = request.getParameter("nama");
            String alamat = request.getParameter("alamat");
            String jamOperasional = request.getParameter("jamOperasional");

            // Set values in the model
            klinikModel.setIdKlinik(idKlinik);  // Updated to use idKlinik
            klinikModel.setNama(nama);
            klinikModel.setAlamat(alamat);
            klinikModel.setJamOperasional(jamOperasional);

            // Update database entry
            klinikModel.update();

        } else if ("delete".equals(action)) {
            int idKlinik = Integer.parseInt(request.getParameter("id"));
            klinikModel.setIdKlinik(idKlinik);  // Updated to use idKlinik
            klinikModel.delete();               // Delete data
        }
        
        response.sendRedirect("klinik?menu=view");  // Redirect after processing action
   }
}