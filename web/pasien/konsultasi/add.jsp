<%@page import="java.util.ArrayList"%>
<%@page import="models.Klinik"%>
<%@page import="models.Dokter"%>
<%@page import="models.Hewan"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Ambil daftar klinik dari database
    ArrayList<Klinik> klinikList = new Klinik().get();
%>
<!DOCTYPE html>
<html lang="id">
<head>
    <title>Janji Konsultasi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container my-5">
        <div class="card shadow d-flex col-8 mx-auto">
            <div class="card-header bg-dark text-white text-center">
                <h2>Buat Janji Konsultasi</h2>
            </div>
            <div class="card-body">
                <form method="POST" action="rekam_medis">
                    <input type="hidden" name="action" value="add">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="nama" placeholder="Nama Hewan" required>
                        <label>Nama Hewan</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="spesies" placeholder="Spesies Hewan" required>
                        <label>Spesies Hewan</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="number" class="form-control" name="usia" placeholder="Usia Hewan" required>
                        <label>Usia Hewan (bulan)</label>
                    </div>
                    <select name="klinik" class="form-select mb-3" required>
                        <option value="" disabled selected>-- Pilih Klinik Hewan --</option>
                        <% for (Klinik klinik : klinikList) { %>
                            <option value="<%= klinik.getIdKlinik() %>">
                                <%= klinik.getNama() %> (<%= klinik.getJamOperasional() %>)
                            </option>
                        <% } %>
                    </select>
                    <button type="submit" class="btn btn-dark w-100">Simpan</button>
                </form>
            </div>
        </div>
        
        <div class="mt-3 d-flex col-8 mx-auto">
            <a href="rekam_medis?menu=view_konsultasi_pelanggan" class="btn btn-secondary">Kembali ke Daftar Konsultasi</a>
        </div>
    </div>
</body>
</html>
