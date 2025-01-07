<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("user") == null) {
        response.sendRedirect("../index.jsp");
        return;
    }
    
    // Ambil nilai username dari session
    String username = (String) userSession.getAttribute("username");
    
    // Set username as a request attribute for EL access
    request.setAttribute("user", username);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Dokter</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="components/navbar.jsp"/>

        <!-- Konten Halaman -->
        <div class="container my-5">
            <div class="p-5 mb-4 rounded-3 shadow">
                <div class="container py-5 text-center">
                    <h1 class="display-5 fw-bold">== Pelanggan ==</h1>
                    <hr>
                    <h2 class="display-7 fw-bold">Selamat Datang di Aplikasi Klinik Hewan</h2>
                    <p class="lead">Aplikasi untuk konsultasi kesehatan hewan!</p>
                </div>
            </div>
        </div>
        
        <!-- Import package Bootstrap JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
