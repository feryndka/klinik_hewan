<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="id">
<head>
    <title>Dashboard Klinik</title>
    <!-- Import package Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
        crossorigin="anonymous">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark d-flex justify-content-between px-5">
        <a class="navbar-brand" href="index.jsp">Klinik Hewan</a>
        
        <div class="dropdown d-flex flex-block align-items-center">
            <!-- Halaman Utama -->
            <div class="me-5">
                <a class="nav-link text-light" href="index.jsp">Home</a>
            </div>
            
            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                Menu Klinik
            </button>
            <ul class="dropdown-menu dropdown-menu-dark">
              <li><a class="dropdown-item" href="klinik?menu=view">Daftar Klinik</a></li>
              <li><a class="dropdown-item disabled" aria-disabled="true">Daftar Dokter</a></li>
              <li><a class="dropdown-item disabled" aria-disabled="true">Daftar Pelanggan</a></li>
              <li><hr class="dropdown-divider"></li>
              <li><a class="dropdown-item disabled" aria-disabled="true">Janji Konsultasi</a></li>
              <li><a class="dropdown-item disabled" aria-disabled="true">Rekam Medis</a></li>
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
        crossorigin="anonymous"></script>
</body>
</html>
