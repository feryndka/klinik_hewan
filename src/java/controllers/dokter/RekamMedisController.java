package controllers.dokter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;
import java.util.logging.Level;
import models.Dokter;
import models.Hewan;
import models.Pelanggan;
import models.Klinik;
import models.RekamMedis;
import models.User;

@WebServlet(name = "RekamMedisController", urlPatterns = {"/rekam_medis"})
public class RekamMedisController extends HttpServlet {
    
    private static final Logger logger = Logger.getLogger(RekamMedisController.class.getName());

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
            
        } else if ("add_konsultasi_pelanggan".equals(menu)) {
            request.setAttribute("klinikList", new Klinik().get());
            request.getRequestDispatcher("/pasien/konsultasi/add.jsp").forward(request, response);
            
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String action = request.getParameter("action");
        RekamMedis rekamMedisModel = new RekamMedis();

        if ("add".equals(action)) {
            try {
                // Data dari input pengguna
                String namaHewan = request.getParameter("nama");
                String spesies = request.getParameter("spesies");
                String usiaStr = request.getParameter("usia");
                int klinikId = Integer.parseInt(request.getParameter("klinik"));
                
                int usia;
                
                usia = Integer.parseInt(usiaStr);

                // Data pelanggan yang login
                //User user = (User) session.getAttribute("user");
                Pelanggan pelanggan = (Pelanggan) session.getAttribute("pelanggan");
                int idPelanggan = pelanggan.getIdPelanggan();

                // Membuat model Hewan
                Hewan hewan = new Hewan();
                hewan.setNama(namaHewan);
                hewan.setSpesies(spesies);
                hewan.setUsiaBulan(usia);
                hewan.setPemilikId(idPelanggan);
                
                hewan.insert();
                logger.info("Insert Hewan successfully.");
                
                rekamMedisModel.setHewan(1);    

                // Membuat model Klinik
                Klinik klinik = new Klinik();
                klinik.setIdKlinik(klinikId);

                // Set nilai-nilai Rekam Medis
                rekamMedisModel.setKlinik(klinik.getIdKlinik());
                rekamMedisModel.setPelanggan(idPelanggan); // Pemilik diambil dari pelanggan login
                rekamMedisModel.setDiagnosa(null);       // Diagnosa belum diisi
                rekamMedisModel.setPerawatan(null);      // Perawatan belum diisi
                rekamMedisModel.setDokter(null);         // Dokter belum diisi

                // Insert into database
                rekamMedisModel.insert();
                logger.info("Insert Rekam Medis successfully.");

                response.sendRedirect("rekam_medis?menu=view_konsultasi_pelanggan"); // Redirect after processing
            } catch (IOException | NumberFormatException e){
                logger.log(Level.SEVERE, "Error inserting Rekam Medis: {0}", e.getMessage());
            }
            
        } else if ("delete".equals(action)) {
           int idRekam = Integer.parseInt(request.getParameter("id"));
           rekamMedisModel.setIdRekam(idRekam);  // Updated to use idRekam
           rekamMedisModel.delete();  // Ensure this method is correctly implemented in your Model class
           
           response.sendRedirect("rekam_medis?menu=view");  // Redirect after processing action
        }
    }
}
