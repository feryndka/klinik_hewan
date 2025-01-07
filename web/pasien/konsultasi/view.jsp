<%@page import="models.Klinik"%>
<%@page import="models.Dokter"%>
<%@page import="models.Hewan"%>
<%@page import="models.RekamMedis"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    ArrayList<RekamMedis> rekamMedisList = (ArrayList<RekamMedis>) request.getAttribute("rekamMedisList");
%>
<!DOCTYPE html>
<html lang="id">
<head>
    <title>Dashboard Konsultasi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../components/navbar.jsp"/>
    
    <!-- Page Content -->         
    <div class="container my-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-dark">Daftar Janji Konsultasi</h2>
            <a href="<%= request.getContextPath() %>/rekam_medis?menu=add_konsultasi_pelanggan" class="btn btn-primary btn-lg">Buat Janji Konsultasi</a>
        </div>
        <div class="table-responsive shadow">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nama Hewan</th>
                        <th>Spesies Hewan</th>
                        <th>Usia Hewan</th>
                        <th>Diagnosa</th>
                        <th>Perawatan</th>
                        <th>Dokter</th>
                        <th>Klinik</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (rekamMedisList != null && !rekamMedisList.isEmpty()) {
                            for (RekamMedis rm : rekamMedisList) {
                                Hewan hewan = rm.getHewanById();
                                String namaHewan = hewan != null ? hewan.getNama() : "Tidak Diketahui";
                                String spesiesHewan = hewan != null ? hewan.getSpesies() : "Tidak Diketahui";
                                int usiaHewan = hewan != null ? hewan.getUsiaBulan() : 0;
                                
                                Dokter dokter = rm.getDokterById();
                                String namaDokter = dokter != null ? dokter.getNama() : "Tidak Diketahui";
                                
                                Klinik klinik = rm.getKlinikById();
                                String namaKlinik = klinik != null ? klinik.getNama() : "Tidak Diketahui";
                    %>
                    <tr>
                        <td><%= rm.getIdRekam() %></td>
                        <td><%= namaHewan %></td>
                        <td><%= spesiesHewan %></td>
                        <td><%= usiaHewan %></td>
                        <td><%= rm.getDiagnosa() %></td>
                        <td><%= rm.getPerawatan() %></td>
                        <td><%= namaDokter %></td>
                        <td><%= namaKlinik %></td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="7" class="text-center">Tidak ada data janji konsultasi.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Import Bootstrap JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>