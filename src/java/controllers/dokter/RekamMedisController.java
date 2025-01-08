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
        // view menu rekam medis dokter
        if ("view".equals(menu)) {
            ArrayList<RekamMedis> rekamMedisList = rekamMedisModel.getAllRekamMedisWithRelations();
            request.setAttribute("rekamMedisList", rekamMedisList);
            request.getRequestDispatcher("/dokter/rekam_medis/view.jsp").forward(request, response);
            
        // view konsultasi dokter
        } else if ("view_konsultasi".equals(menu)) {
            ArrayList<RekamMedis> rekamMedisList = rekamMedisModel.getAllRekamMedisWithRelations();
            request.setAttribute("rekamMedisList", rekamMedisList);
            request.getRequestDispatcher("/dokter/konsultasi/view.jsp").forward(request, response);
            
        // view konsultasi pelanggan
        } else if ("view_konsultasi_pelanggan".equals(menu)) {
            ArrayList<RekamMedis> rekamMedisList = rekamMedisModel.getAllRekamMedisWithRelations();
            request.setAttribute("rekamMedisList", rekamMedisList);
            request.getRequestDispatcher("/pasien/konsultasi/view.jsp").forward(request, response);
          
        // add konsultasi pelanggan
        } else if ("add_konsultasi_pelanggan".equals(menu)) {
            request.getRequestDispatcher("/pasien/konsultasi/add.jsp").forward(request, response);
            
        // edit konsultasi dokter
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
                int dokterId = Integer.parseInt(request.getParameter("dokter"));
                
                int usia;
                usia = Integer.parseInt(usiaStr);

                // Data pelanggan yang login
                User user = (User) session.getAttribute("user");
                Pelanggan pelanggan = user.getPelangganById();
                
                //Pelanggan pelanggan = (Pelanggan) session.getAttribute("pelanggan");
                int idPelanggan = pelanggan.getIdPelanggan();

                // Membuat model Hewan
                Hewan hewan = new Hewan();
                hewan.setNama(namaHewan);
                hewan.setSpesies(spesies);
                hewan.setUsiaBulan(usia);
                hewan.setPemilikId(idPelanggan);
                
                hewan.insert();
                System.out.println("Insert Hewan successfully.");
                System.out.println("ID Hewan: " + hewan.getId());
                System.out.println("Nama Hewan: " + hewan.getNama());
                
                rekamMedisModel.setHewan(hewan.getId());    

                // Membuat model Klinik
                Klinik klinik = new Klinik();
                klinik.setIdKlinik(klinikId);
                
                // Membuat model Dokter
                Dokter dokter = new Dokter();
                dokter.setIdDokter(dokterId);

                // Set nilai-nilai Rekam Medis
                rekamMedisModel.setKlinik(klinik.getIdKlinik());
                rekamMedisModel.setPelanggan(idPelanggan);  // Pemilik diambil dari pelanggan login
                rekamMedisModel.setDiagnosa(null);          // Diagnosa belum diisi
                rekamMedisModel.setPerawatan(null);         // Perawatan belum diisi
                rekamMedisModel.setDokter(dokter.getIdDokter());

                // Insert into database
                rekamMedisModel.insert();
                System.out.println("Insert Rekam Medis successfully.");

                response.sendRedirect("rekam_medis?menu=view_konsultasi_pelanggan"); // Redirect after processing
            } catch (IOException | NumberFormatException e){
                logger.log(Level.SEVERE, "Error inserting Rekam Medis: {0}", e.getMessage());
            }
            
        } else if ("edit".equals(action)) {
            try {
                // Retrieve parameters from input
                String idRekamParam = request.getParameter("id");
                String idHewanParam = request.getParameter("idHewan");
                String idPelangganParam = request.getParameter("idPelanggan");
                String idDokterParam = request.getParameter("idDokter");
                String idKlinikParam = request.getParameter("idKlinik");
                String diagnosa = request.getParameter("diagnosa");
                String perawatan = request.getParameter("perawatan");

                // Log each parameter value
                System.out.println("Editing Rekam Medis");
                System.out.println("ID Rekam: " + idRekamParam);
                System.out.println("ID Hewan: " + idHewanParam);
                System.out.println("ID Pelanggan: " + idPelangganParam);
                System.out.println("ID Dokter: " + idDokterParam);
                System.out.println("ID Klinik: " + idKlinikParam);
                System.out.println("Diagnosa: " + diagnosa);
                System.out.println("Perawatan: " + perawatan);

                // Check and parse parameters, handle potential null values
                if (idRekamParam == null || idHewanParam == null || idPelangganParam == null ||
                    idDokterParam == null || idKlinikParam == null || diagnosa == null || perawatan == null ) {
                    System.out.println("One or more parameters are null.");
                    response.sendRedirect("rekam_medis?menu=view_konsultasi&error=parameter_null");
                    return;
                }

                int idRekam = Integer.parseInt(idRekamParam);
                int idHewan = Integer.parseInt(idHewanParam);
                int idPelanggan = Integer.parseInt(idPelangganParam);
                int idDokter = Integer.parseInt(idDokterParam);
                int idKlinik = Integer.parseInt(idKlinikParam);

                // Set values on the model
                rekamMedisModel.setIdRekam(idRekam);
                rekamMedisModel.setHewan(idHewan);
                rekamMedisModel.setPelanggan(idPelanggan);
                rekamMedisModel.setDokter(idDokter);
                rekamMedisModel.setKlinik(idKlinik);
                rekamMedisModel.setDiagnosa(diagnosa);
                rekamMedisModel.setPerawatan(perawatan);

                // Update the database
                rekamMedisModel.update();
                System.out.println("Successfully updated Rekam Medis with " + diagnosa + " and perawatan as " + perawatan);
                response.sendRedirect("rekam_medis?menu=view_konsultasi");
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, "Error updating Rekam Medis: {0}", e.getMessage());
                System.out.println("Error updating Rekam Medis: Invalid number format. " + e.getMessage());
                response.sendRedirect("rekam_medis?menu=view_konsultasi&error=invalid_number_format");
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error updating Rekam Medis: {0}", e.getMessage());
                System.out.println("Error updating Rekam Medis: " + e.getMessage());
                response.sendRedirect("rekam_medis?menu=view_konsultasi");
            }

        } else if ("delete".equals(action)) {
           int idRekam = Integer.parseInt(request.getParameter("id"));
           rekamMedisModel.setIdRekam(idRekam);  // Updated to use idRekam
           rekamMedisModel.delete();             // Delete data
           
           response.sendRedirect("rekam_medis?menu=view_konsultasi");  // Redirect after processing action
        }
    }
}
