<%@page import="models.Klinik"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect("../../index.jsp");
        return;
    }
    
    // Ambil nilai username dari session
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

    <!-- Konten Halaman -->         
    <div class="container my-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-primary">Daftar Klinik</h2>
            <a href="<%= request.getContextPath() %>/klinik?menu=add" class="btn btn-success btn-lg">Tambah Klinik</a>

        </div>
        <div class="table-responsive shadow">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nama</th>
                        <th>Alamat</th>
                        <th>Jam Operasional</th>
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (klinik != null && !klinik.isEmpty()) {
                            for (Klinik kln : klinik) {
                    %>
                    <tr>
                        <td><%= kln.getId() %></td>
                        <td><%= kln.getNama() %></td>
                        <td><%= kln.getAlamat() %></td>
                        <td><%= kln.getJamOperasional() %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/klinik?menu=edit&id=<%= kln.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                            <form method="POST" action="<%= request.getContextPath() %>/klinik" style="display: inline;">
                                <input type="hidden" name="action" value="delete" />
                                <input type="hidden" name="id" value="<%= kln.getId() %>" />
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Apakah Anda yakin ingin menghapus klinik ini?');">Hapus</button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="4" class="text-center">Tidak ada data klinik.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
    
    <!-- Import package Bootstrap JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>