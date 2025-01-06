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
    <title>Dashboard Rekam Medis</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../components/navbar.jsp"/>
    
    <!-- Page Content -->         
    <div class="container my-5">
        <h2 class="text-dark mb-4">Daftar Rekaman Medis</h2>
        <div class="table-responsive shadow">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nama Pemilik</th>
                        <th>Nama Hewan</th>
                        <th>Spesies Hewan</th>
                        <th>Diagnosa</th>
                        <th>Perawatan</th>
                        <th>Nama Dokter</th>
                        <th>Klinik</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (rekamMedisList != null && !rekamMedisList.isEmpty()) {
                            for (RekamMedis rm : rekamMedisList) {
                                String namaPemilik = rm.getPelanggan() != null ? rm.getPelanggan().getNama() : "Tidak Diketahui";
                                String namaHewan = rm.getHewan() != null ? rm.getHewan().getNama() : "Tidak Diketahui";
                                String spesiesHewan = rm.getHewan() != null ? rm.getHewan().getSpesies() : "Tidak Diketahui";
                                String namaDokter = rm.getDokter() != null ? rm.getDokter().getNama() : "Tidak Diketahui";
                                String namaKlinik = rm.getKlinik() != null ? rm.getKlinik().getNama() : "Tidak Diketahui";
                    %>
                    <tr>
                        <td><%= rm.getIdRekam() %></td>
                        <td><%= namaPemilik %></td>
                        <td><%= namaHewan %></td>
                        <td><%= spesiesHewan %></td>
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
                        <td colspan="7" class="text-center">Tidak ada data rekaman medis.</td>
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