<%@page import="models.Klinik"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect("../../index.jsp");
        return;
    }
    // Get username from session
    String username = (String) userSession.getAttribute("user");
    ArrayList<Klinik> klinik = (ArrayList<Klinik>) request.getAttribute("klinik");
%>
<!DOCTYPE html>
<html lang="id">
<head>
    <title>Dashboard Klinik</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../components/navbar.jsp"/>
    
    <!-- Page Content -->         
    <div class="container my-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-primary">Daftar Klinik</h2>
            <a href="<%= request.getContextPath() %>/klinik?menu=add" class="btn btn-success btn-lg">Tambah Klinik</a>
        </div>

        <div class="table-responsive shadow">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID Klinik</th>          <!-- Updated column header -->
                        <th>Nama</th>
                        <th>Alamat</th>
                        <th>Jam Operasional</th>   <!-- New column for operational hours -->
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (klinik != null && !klinik.isEmpty()) {
                            for (Klinik kln : klinik) {
                    %>
                    <tr>
                        <td><%= kln.getIdKlinik() %></td>  <!-- Updated method call -->
                        <td><%= kln.getNama() %></td>
                        <td><%= kln.getAlamat() %></td>
                        <td><%= kln.getJamOperasional() %></td>  <!-- Display operational hours -->
                        <td>
                            <a href="<%= request.getContextPath() %>/klinik?menu=edit&id=<%= kln.getIdKlinik() %>" class="btn btn-warning btn-sm">Edit</a>  <!-- Updated method call -->
                            <form method="POST" action="<%= request.getContextPath() %>/klinik" style="display: inline;">
                                <input type="hidden" name="action" value="delete" />
                                <input type="hidden" name="id" value="<%= kln.getIdKlinik() %>" />  <!-- Updated method call -->
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Apakah Anda yakin ingin menghapus klinik ini?');">Hapus</button>
                            </form>
                        </td>
                    </tr>
                    <% 
                            }
                        } else { 
                    %>

                    <!-- No data message -->
                    <tr><td colspan="5" class="text-center">Tidak ada data klinik.</td></tr>

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
