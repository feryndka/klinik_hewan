<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="id">
<head>
    <title>Tambah Data Klinik</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container my-5">
        <div class="mb-3 d-flex col-8 mx-auto">
            <a href="klinik?menu=view" class="btn btn-secondary">Kembali ke Daftar Klinik</a>
        </div>
        <div class="card shadow d-flex col-8 mx-auto">
            <div class="card-header bg-dark text-white text-center">
                <h2>Tambah Data Klinik</h2>
            </div>
            <div class="card-body">
                <form method="POST" action="klinik">
                    <input type="hidden" name="action" value="add">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="nama" placeholder="Nama Klinik" required>
                        <label>Nama Klinik</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" step="0.01" class="form-control" name="alamat" placeholder="Alamat Klinik" required>
                        <label>Alamat</label>
                    </div>
                    <select name="jamOperasional" id="" class="form-select mb-3">
                        <option value="" disabled selected>-- Pilih Jam Operasional --</option>
                        <option value="07:00 - 16:00">07:00 - 16:00</option>
                        <option value="08:00 - 17:00">08:00 - 17:00</option>
                        <option value="09:00 - 18:00">09:00 - 18:00</option>
                    </select>
                    <button type="submit" class="btn btn-dark w-100">Simpan</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
