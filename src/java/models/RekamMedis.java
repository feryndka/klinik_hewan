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

    public RekamMedis() {
        this.table = "rekam_medis";  // Table name
        this.primaryKey = "idRekam";  // Primary key column name
    }

    public RekamMedis(int idRekam, String diagnosa, String perawatan, Hewan hewan, Pelanggan pemilik, Dokter dokter) {
        this();  // Call default constructor to set table and primary key
        this.idRekam = idRekam;
        this.diagnosa = diagnosa;
        this.perawatan = perawatan;
        this.hewan = hewan;
        this.pemilik = pemilik;
        this.dokter = dokter;
    }

    @Override
    public RekamMedis toModel(ResultSet rs) { 
        try { 
            Hewan hewan = new Hewan().toModel(rs);
            Pelanggan pemilik = new Pelanggan().toModel(rs);
            Dokter dokter = new Dokter().toModel(rs);

            return new RekamMedis(
                rs.getInt("idRekam"),
                rs.getString("diagnosa"),
                rs.getString("perawatan"),
                hewan,
                pemilik,
                dokter
            );
        } catch (SQLException e) { 
            System.out.println("Error: " + e.getMessage()); 
            return null; 
        } 
    }
    
    public ArrayList<RekamMedis> getAllRekamMedisWithRelations() {
        this.join("hewan", "rekam_medis.hewan = hewan.id")
            .join("pelanggan", "rekam_medis.pemilik = pelanggan.idPelanggan")
            .join("dokter", "rekam_medis.dokter = dokter.idDokter");

        this.select("rekam_medis.*, "
                  + "hewan.id AS hewan_id, hewan.spesies AS hewan_spesies, hewan.nama AS hewan_nama, "
                  + "hewan.usia_bulan AS hewan_usia_bulan, hewan.pemilik AS hewan_pemilik, "
                  + "pelanggan.idPelanggan AS idPelanggan, pelanggan.nama AS pemilik_nama, pelanggan.alamat AS pemilik_alamat, "
                  + "dokter.idDokter AS dokter_id, dokter.nama AS dokter_nama, dokter.spesialisasi AS dokter_spesialisasi");

        return this.get();
    }

    // Getters and setters for RekamMedis fields
    public int getIdRekam() { return idRekam; }
    public void setIdRekam(int idRekam) { this.idRekam = idRekam; }

    public String getDiagnosa() { return diagnosa; }
    public void setDiagnosa(String diagnosa) { this.diagnosa = diagnosa; }

    public String getPerawatan() { return perawatan; }
    public void setPerawatan(String perawatan) { this.perawatan = perawatan; }

    public Hewan getHewan() { return hewan; }
    public void setHewan(Hewan hewan) { this.hewan = hewan; }

    public Pelanggan getPemilik() { return pemilik; }
    public void setPemilik(Pelanggan pemilik) { this.pemilik = pemilik; }

    public Dokter getDokter() { return dokter; }
    public void setDokter(Dokter dokter) { this.dokter = dokter; }
}