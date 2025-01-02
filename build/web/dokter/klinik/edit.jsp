<%@page import="models.Klinik"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="id">
<head>
    <title>Edit Barang</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%
        Klinik klinik = (Klinik) request.getAttribute("klinik");
        if (klinik == null) {
    %>
        <div class="container my-5">
            <div class="alert alert-danger text-center">
                <h4>Data klinik tidak ditemukan.</h4>
                <a href="klinik?menu=view" class="btn btn-primary mt-3">Kembali ke Dashboard</a>
            </div>
        </div>
    <%
        } else {
    %>
    <div class="container my-5">
        <div class="card shadow d-flex col-8 mx-auto">
            <div class="card-header bg-dark text-white text-center">
                <h2>Edit Klinik</h2>
            </div>
            <div class="card-body">
                <form method="POST" action="klinik">
                    <!-- Hidden inputs untuk operasi edit -->
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="id" value="<%= klinik.getIdKlinik() %>">
                    
                    <!-- Input Nama Klinik -->
                    <div class="form-floating mb-3">
                        <input 
                            type="text" 
                            class="form-control" 
                            id="nama" 
                            name="nama" 
                            value="<%= klinik.getNama() %>" 
                            required>
                        <label for="namaKlinik">Nama Klinik</label>
                    </div>

                    <!-- Input Alamat Klinik -->
                    <div class="form-floating mb-3">
                        <input 
                            type="text" 
                            class="form-control" 
                            id="alamat" 
                            name="alamat" 
                            value="<%= klinik.getAlamat() %>" 
                            required>
                        <label for="alamat">Alamat</label>
                    </div>
                            
                     <!-- Dropdown Jam Operasional Klinik -->
                    <div class="mb-3">
                        <label for="jamOperasional" class="form-label">Jam Operasional</label>
                        <select name="jamOperasional" id="jamOperasional" class="form-select" required>
                            <option value="" disabled>-- Pilih Jam Operasional --</option>
                            <option value="07:00 - 16:00" <%= klinik.getJamOperasional().equals("07:00 - 16:00") ? "selected" : "" %>>07:00 - 16:00</option>
                            <option value="08:00 - 17:00" <%= klinik.getJamOperasional().equals("08:00 - 17:00") ? "selected" : "" %>>08:00 - 17:00</option>
                            <option value="09:00 - 18:00" <%= klinik.getJamOperasional().equals("09:00 - 18:00") ? "selected" : "" %>>09:00 - 18:00</option>
                        </select>
                    </div>

                    <div class="d-flex justify-content-between">
                        <a href="klinik?menu=view" class="btn btn-secondary btn-lg">Kembali</a>
                        <button type="submit" class="btn btn-dark btn-lg">Simpan Perubahan</button>
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
