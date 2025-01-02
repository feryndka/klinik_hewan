<!-- navbar.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark d-flex justify-content-between px-5">
    <a class="navbar-brand" href="user?menu=view_pasien">Klinik Hewan</a>

    <div class="dropdown d-flex flex-block align-items-center">
        <!-- Halaman Utama -->
        <div class="me-5">
            <a class="nav-link text-light" href="user?menu=view_pasien">Home</a>
        </div>
        
        <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
            Menu
        </button>
        <ul class="dropdown-menu dropdown-menu-dark dropdown-menu-end">
            <li><a class="dropdown-item disabled text-center" aria-disabled="true">-- ${user} --</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="rekam_medis?menu=view_konsultasi_pelanggan">Janji Konsultasi</a></li>
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
