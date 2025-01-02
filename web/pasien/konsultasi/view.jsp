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
            <a href="<%= request.getContextPath() %>/rekam_medis?menu=add" class="btn btn-primary btn-lg">Buat Janji Konsultasi</a>
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
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (rekamMedisList != null && !rekamMedisList.isEmpty()) {
                            for (RekamMedis rm : rekamMedisList) {
                                String namaHewan = rm.getHewan() != null ? rm.getHewan().getNama() : "Tidak Diketahui";
                                String spesiesHewan = rm.getHewan() != null ? rm.getHewan().getSpesies() : "Tidak Diketahui";
                                String namaPemilik = rm.getPemilik() != null ? rm.getPemilik().getNama() : "Tidak Diketahui";
                                String namaDokter = rm.getDokter() != null ? rm.getDokter().getNama() : "Tidak Diketahui";
                    %>
                    <tr>
                        <td><%= rm.getIdRekam() %></td>
                        <td><%= namaHewan %></td>
                        <td><%= spesiesHewan %></td>
                        <td><%= namaPemilik %></td>
                        <td><%= rm.getDiagnosa() %></td>
                        <td><%= rm.getPerawatan() %></td>
                        <td><%= namaDokter %></td>
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