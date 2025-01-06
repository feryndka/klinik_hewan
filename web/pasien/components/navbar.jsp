<!-- navbar.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark d-flex justify-content-between px-5">
    <div class="d-flex flex-block align-items-center">
        <a class="navbar-brand" href="user?menu=view_pasien">Klinik Hewan</a>
        <a class="nav-link disabled badge text-bg-light" aria-disabled="true">${user}</a>
    </div>

    <div class="d-flex flex-block align-items-center">
        <!-- Halaman Utama -->
        <div class="">
            <a class="nav-link text-light" href="user?menu=view_pasien">Home</a>
        </div>
        
        <!-- Halaman Janji Konsultasi -->
        <div class="mx-5">
            <a class="nav-link text-light" href="rekam_medis?menu=view_konsultasi_pelanggan">Janji Konsultasi</a>
        </div>
        
        <!-- URL logout -->
        <form method="POST" action="<%= request.getContextPath() %>/AuthController" style="display: inline;">
            <input type="hidden" name="action" value="logout">
            <button type="submit" class="btn btn-danger btn-sm">Logout</button>
        </form>
            
    </div>
</nav>
