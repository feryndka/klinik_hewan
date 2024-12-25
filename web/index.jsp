<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="id">
<head>
    <title>Selamat Datang</title>
    <!--(import package bootstrap css pada header)-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="d-flex flex-column justify-content-center align-items-center vh-100 bg-light">
        <div class="container my-5">
            <div class="p-5 mb-4 rounded-3 shadow">
                <div class="container py-5 text-center">
                    <h1 class="display-5 fw-bold">Selamat Datang di Aplikasi Klinik Hewan</h1>
                    <p class="lead">Aplikasi untuk manajemen klinik kesehatan hewan!</p>
                    <!--(sesuai routing agar masuk ke halaman dashboard (view.jsp))-->
                    <a href="klinik?menu=view" class="btn btn-primary btn-lg">Masuk ke Dashboard Klinik</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
