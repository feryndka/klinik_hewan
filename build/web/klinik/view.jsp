<%@page import="models.Klinik"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="id">
<head>
    <title>Dashboard Klinik</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container my-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-primary">Daftar Klinik</h2>
            <a href="product?menu=add" class="btn btn-success btn-lg">Tambah Barang</a>
        </div>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <!--(lengkapi table header sesuai dengan nama kolom yang ada pada table db + tambahkan kolom aksi)-->
                        <th>ID</th>
                        <th>Nama</th>
                        <th>Alamat</th>
                        <th>Jam Operasional</th>
                        <th>Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Klinik> kln = (ArrayList<Klinik>) request.getAttribute("kln");
                        if (kln != null) {
                            for (Klinik klinik : kln) {
                    %>
                    <tr>
                        <!--(lengkapi kolom table body untuk menampilkan seluruh data pada berdasarkan model klinik)--> 
                        <td><%= klinik.getId() %></td>
                        <td><%= klinik.getNama() %></td>
                        <td><%= klinik.getAlamat() %></td>
                        <td><%= klinik.getJamOperasional() %></td>
                        <td>
                            <!--(sesuaikan routing agar masuk ke halaman edit sesuai dengan id yang ingin di edit(edit.jsp))-->
                            <a href="klinik?menu=edit&id=<%= klinik.getId() %>" class="btn btn-warning btn-sm">Edit</a>
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
        <div class="mt-4">
            <!--(sesuaikan routing agar masuk ke halaman welcome page (index.jsp))-->
            <a href="index.jsp" class="btn btn-secondary btn-lg">Kembali ke Halaman Utama</a>
        </div>
    </div>
</body>
</html>