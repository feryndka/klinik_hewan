<%@page import="models.RekamMedis"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="id">
<head>
    <title>Diagnosa Hewan</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%               
        RekamMedis rekamMedis = (RekamMedis) request.getAttribute("rekamMedis");
        if (rekamMedis == null) {
    %>
        <div class="container my-5">
            <div class="alert alert-danger text-center">
                <h4>Data konsultasi tidak ditemukan.</h4>
                <a href="rekam_medis?menu=view_konsultasi" class="btn btn-primary mt-3">Kembali ke Dashboard</a>
            </div>
        </div>
    <%
        } else {

        /** out.println("ID Rekam : " + rekamMedis.getIdRekam());
        out.println("ID Pelanggan : " + rekamMedis.getPelangganById().getIdPelanggan());
        out.println("ID Hewan : " + rekamMedis.getHewanById().getId());
        out.println("ID Dokter : " + rekamMedis.getDokterById().getIdDokter()); */
    %>
    <div class="container my-5">
        <div class="card shadow-lg border-0">
            <div class="card-header bg-dark text-white text-center">
                <h2 class="fw-bold">Diagnosa Hewan</h2>
            </div>
            <div class="card-body p-5">
                <form method="POST" action="rekam_medis">
                    <!-- Hidden inputs untuk operasi edit -->
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="id" value="<%= rekamMedis.getIdRekam() %>">
                    
                    <!-- Nama Pemilik -->
                    <div class="row mb-3">
                        <div class="col-sm-2 fw-bold d-flex justify-content-between"><span>Nama Pemilik</span> :</div>
                        <div class="col-sm-8"><%= rekamMedis.getPelangganById().getNama() %></div>
                        <input type="hidden" name="idPelanggan" value="<%= rekamMedis.getPelangganById().getIdPelanggan() %>">
                        
                    </div>

                    <!-- Nama Hewan -->
                    <div class="row mb-3">
                        <div class="col-sm-2 fw-bold d-flex justify-content-between"><span>Nama Hewan</span> :</div>
                        <div class="col-sm-8"><%= rekamMedis.getHewanById().getNama() %></div>
                        <input type="hidden" name="idHewan" value="<%= rekamMedis.getHewanById().getId() %>">
                    </div>

                    <!-- Spesies Hewan -->
                    <div class="row mb-3">
                        <div class="col-sm-2 fw-bold d-flex justify-content-between"><span>Spesies Hewan</span> :</div>
                        <div class="col-sm-8"><%= rekamMedis.getHewanById().getSpesies() %></div>
                    </div>
                    
                    <!-- Dokter -->
                    <div class="row mb-3">
                        <div class="col-sm-2 fw-bold d-flex justify-content-between"><span>Dokter</span> :</div>
                        <div class="col-sm-8"><%= rekamMedis.getDokterById().getNama() %></div>
                        <input type="hidden" name="idDokter" value="<%= rekamMedis.getDokterById().getIdDokter() %>">
                    </div>
                    
                    <!-- Klinik -->
                    <div class="row mb-4">
                        <div class="col-sm-2 fw-bold d-flex justify-content-between"><span>Klinik</span> :</div>
                        <div class="col-sm-8"><%= rekamMedis.getKlinikById().getNama() %></div>
                        <input type="hidden" name="idKlinik" value="<%= rekamMedis.getKlinikById().getIdKlinik() %>">
                    </div>

                    <!-- Diagnosa Hewan -->
                    <div class="form-floating mb-3">
                        <input 
                            type="text" 
                            class="form-control" 
                            id="diagnosa" 
                            name="diagnosa" 
                            value="<%= rekamMedis.getDiagnosa() %>" 
                            placeholder="Masukkan Diagnosa Hewan"
                            required>
                        <label for="diagnosa">Diagnosa Penyakit</label>
                    </div>
                    
                    <!-- Perawatan Hewan -->
                    <div class="form-floating mb-4">
                        <input 
                            type="text" 
                            class="form-control" 
                            id="perawatan" 
                            name="perawatan" 
                            value="<%= rekamMedis.getPerawatan() %>" 
                            placeholder="Masukkan Perawatan Hewan"
                            required>
                        <label for="perawatan">Perawatan Hewan</label>
                    </div>

                    <div class="d-flex justify-content-between">
                        <a href="rekam_medis?menu=view_konsultasi" class="btn btn-outline-secondary btn-md">Kembali</a>
                        <button type="submit" class="btn btn-dark btn-md">Simpan Perubahan</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%
        }
    %>
</body>
</html>
