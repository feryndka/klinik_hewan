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
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark d-flex justify-content-between px-5">
        <a class="navbar-brand" href="index.jsp">Klinik Hewan</a>
        
        <div class="dropdown d-flex flex-block align-items-center">
            <!-- Halaman Utama -->
            <div class="me-5">
                <a class="nav-link text-light" href="user?menu=view_dokter">Home</a>
            </div>
            
            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                Menu
            </button>
            <ul class="dropdown-menu dropdown-menu-dark dropdown-menu-end">
                <li><a class="dropdown-item disabled text-center" aria-disabled="true">-- <%= username %> --</a></li>
                <li><hr class="dropdown-divider"></li>
                <li><a class="dropdown-item active" href="klinik?menu=view">Daftar Klinik</a></li>
                <li><a class="dropdown-item disabled" aria-disabled="true">Daftar Pelanggan</a></li>
                <li><a class="dropdown-item disabled" aria-disabled="true">Janji Konsultasi</a></li>
                <li><a class="dropdown-item disabled" aria-disabled="true">Rekam Medis</a></li>
                <li><hr class="dropdown-divider"></li>
                <li>
                    <!-- URL logout -->
                    <form method="POST" action="<%= request.getContextPath() %>/AuthController" style="display: inline;">
                        <input type="hidden" name="action" value="logout">
                        <div class="d-grid col-6 mx-auto">
                            <button type="submit" class="btn btn-outline-danger btn-sm">Logout</button>
                        </div>
                    </form>
                </li>
            </ul>
        </div>
    </nav>

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