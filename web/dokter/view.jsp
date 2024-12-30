<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect("../index.jsp");
        return;
    }
    
    // Ambil nilai username dari session
    String username = (String) userSession.getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Dokter</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark d-flex justify-content-between px-5">
            <a class="navbar-brand" href="index.jsp">Klinik Hewan</a>

            <div class="dropdown d-flex flex-block align-items-center">
                <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Menu
                </button>
                <ul class="dropdown-menu dropdown-menu-dark dropdown-menu-end">
                    <li><a class="dropdown-item disabled text-center" aria-disabled="true">-- <%= username %> --</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="klinik?menu=view">Daftar Klinik</a></li>
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
            <div class="p-5 mb-4 rounded-3 shadow">
                <div class="container py-5 text-center">
                    <h1 class="display-5 fw-bold">Selamat Datang di Aplikasi Klinik Hewan</h1>
                    <p class="lead">Aplikasi untuk manajemen klinik kesehatan hewan!</p>
                </div>
            </div>
        </div>
        
        <!-- Import package Bootstrap JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
