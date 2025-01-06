package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RekamMedis extends Model<RekamMedis> {
    private int idRekam;           // Primary Key
    private String diagnosa;       // Diagnosis details
    private String perawatan;      // Treatment details
    private Hewan hewan;           // Foreign Key to Hewan
    private Pelanggan pemilik;     // Foreign Key to Pelanggan (owner)
    private Dokter dokter;         // Foreign Key to Dokter
    private Klinik klinik;         // Foreign Key to klinik

    public RekamMedis() {
        this.table = "rekam_medis";  // Table name
        this.primaryKey = "idRekam";  // Primary key column name
    }

    public RekamMedis(int idRekam, String diagnosa, String perawatan, Hewan hewan, Pelanggan pemilik, Dokter dokter, Klinik klinik) {
        this();  // Call default constructor to set table and primary key
        this.idRekam = idRekam;
        this.diagnosa = diagnosa;
        this.perawatan = perawatan;
        this.hewan = hewan;
        this.pemilik = pemilik;
        this.dokter = dokter;
        this.klinik = klinik;
    }

    @Override
    public RekamMedis toModel(ResultSet rs) { 
        try { 
            Hewan hewan = new Hewan().toModel(rs);
            Pelanggan pemilik = new Pelanggan().toModel(rs);
            Dokter dokter = new Dokter().toModel(rs);
            Klinik klinik = new Klinik().toModel(rs);

            return new RekamMedis(
                rs.getInt("idRekam"),
                rs.getString("diagnosa"),
                rs.getString("perawatan"),
                hewan,
                pemilik,
                dokter,
                klinik
            );
        } catch (SQLException e) { 
            System.out.println("Error: " + e.getMessage()); 
            return null; 
        } 
    }
    
    public ArrayList<RekamMedis> getAllRekamMedisWithRelations() {
        this.join("hewan", "rekam_medis.hewan = hewan.id")
            .join("pelanggan", "rekam_medis.pemilik = pelanggan.idPelanggan")
            .join("dokter", "rekam_medis.dokter = dokter.idDokter")
            .join("klinik", "rekam_medis.klinik = klinik.idKlinik");

        this.select("rekam_medis.*, "
            + "hewan.id AS id, hewan.spesies AS spesies, hewan.namaHewan AS namaHewan, "
            + "hewan.usia_bulan AS usia_bulan, "
            + "pelanggan.idPelanggan AS idPelanggan, pelanggan.namaPelanggan AS namaPelanggan, "
            + "pelanggan.alamatPelanggan AS alamatPelanggan, pelanggan.nomor_telepon AS nomor_telepon, "
            + "dokter.idDokter AS idDokter, dokter.namaDokter AS namaDokter, dokter.spesialisasi AS spesialisasi, "
            + "klinik.idKlinik AS idKlinik, klinik.namaKlinik AS namaKlinik, "
            + "klinik.alamatKlinik AS alamatKlinik, klinik.jamOperasional AS jamOperasional");

        return this.get();
    }

    // Getters and setters
    public int getIdRekam() { return idRekam; }
    public void setIdRekam(int idRekam) { this.idRekam = idRekam; }

    public String getDiagnosa() { return diagnosa; }
    public void setDiagnosa(String diagnosa) { this.diagnosa = diagnosa; }

    public String getPerawatan() { return perawatan; }
    public void setPerawatan(String perawatan) { this.perawatan = perawatan; }

    public Hewan getHewan() { return hewan; }
    public void setHewan(Hewan hewan) { this.hewan = hewan; }

    public Pelanggan getPelanggan() { return pemilik; }
    public void setPelanggan(Pelanggan pemilik) { this.pemilik = pemilik; }

    public Dokter getDokter() { return dokter; }
    public void setDokter(Dokter dokter) { this.dokter = dokter; }
    
    public Klinik getKlinik() { return klinik; }
    public void setKlinik(Klinik klinik) { this.klinik = klinik; }
}
