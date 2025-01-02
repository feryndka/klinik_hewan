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
        <h2 class="text-dark mb-4">Daftar Janji Konsultasi</h2>
        <div class="table-responsive shadow">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nama Hewan</th>
                        <th>Spesies Hewan</th>
                        <th>Nama Pemilik</th>
                        <th>Diagnosa</th>
                        <th>Perawatan</th>
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (rekamMedisList != null && !rekamMedisList.isEmpty()) {
                            for (RekamMedis rm : rekamMedisList) {
                                String namaHewan = rm.getHewan() != null ? rm.getHewan().getNama() : "Tidak Diketahui";
                                String spesiesHewan = rm.getHewan() != null ? rm.getHewan().getSpesies() : "Tidak Diketahui";
                                String namaPemilik = rm.getPemilik() != null ? rm.getPemilik().getNama() : "Tidak Diketahui";
                    %>
                    <tr>
                        <td><%= rm.getIdRekam() %></td>
                        <td><%= namaHewan %></td>
                        <td><%= spesiesHewan %></td>
                        <td><%= namaPemilik %></td>
                        <td><%= rm.getDiagnosa() %></td>
                        <td><%= rm.getPerawatan() %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/rekam_medis?menu=edit&id=<%= rm.getIdRekam() %>" class="btn btn-success btn-sm">Diagnosa</a>
                            <form method="POST" action="<%= request.getContextPath() %>/rekam_medis" style="display: inline;">
                                <input type="hidden" name="action" value="delete" />
                                <input type="hidden" name="id" value="<%= rm.getIdRekam() %>" />
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Apakah Anda yakin ingin menghapus data ini?');">Hapus</button>
                            </form>
                        </td>
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