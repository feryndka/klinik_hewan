<%@page import="models.Pelanggan"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    // Ambil daftar pelanggan dari request attribute
    ArrayList<Pelanggan> pelangganList = (ArrayList<Pelanggan>) request.getAttribute("pelangganList");
%>
<!DOCTYPE html>
<html lang="id">
<head>
    <title>Daftar Pelanggan</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../components/navbar.jsp"/>
    
    <!-- Page Content -->         
    <div class="container my-5">
        <h2 class="text-primary mb-4">Daftar Pelanggan</h2>
        <div class="table-responsive shadow">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID Pelanggan</th>
                        <th>Nama</th>
                        <th>Alamat</th>
                        <th>No. Telepon</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (pelangganList != null && !pelangganList.isEmpty()) {
                            for (Pelanggan pelanggan : pelangganList) {
                    %>
                    <tr>
                        <td><%= pelanggan.getIdPelanggan() %></td>
                        <td><%= pelanggan.getNama() %></td>
                        <td><%= pelanggan.getAlamat() %></td>
                        <td><%= pelanggan.getNomorTelepon() %></td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="5" class="text-center">Tidak ada data pelanggan.</td>
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