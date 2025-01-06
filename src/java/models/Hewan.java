package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Hewan extends Model<Hewan> {
    private int id;                // Primary Key
    private String spesies;        // Species of the animal
    private String namaHewan;      // Name of the animal
    private int usia_bulan;        // Age in months
    private Pelanggan pelanggan;   // Foreign Key to Pelanggan

    public Hewan() {
        this.table = "hewan";      // Table name
        this.primaryKey = "id";    // Primary key column name
    }

    public Hewan(int id, String spesies, String nama, int usiaBulan, Pelanggan pelanggan) {
        this();  // Call default constructor to set table and primary key
        this.id = id;
        this.spesies = spesies;
        this.namaHewan = nama;
        this.usia_bulan = usiaBulan;
        this.pelanggan = pelanggan;
    }

    @Override
    public Hewan toModel(ResultSet rs) {
        try {
            Pelanggan pelanggan = new Pelanggan().toModel(rs);
            
            return new Hewan(
                rs.getInt("id"),            // Match with SQL column name
                rs.getString("spesies"),
                rs.getString("namaHewan"),
                rs.getInt("usia_bulan"),    // Match with SQL column name
                pelanggan                   // Foreign Key to Pelanggan
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Hewan> getAllHewanWithRelations() {
        this.join("pelanggan", "hewan.pemilik = pelanggan.idPelanggan");

        this.select("hewan.*, "
            + "pelanggan.idPelanggan AS idPelanggan, pelanggan.namaPelanggan AS namaPelanggan, "
            + "pelanggan.alamatPelanggan AS alamatPelanggan, pelanggan.nomor_telepon AS nomor_telepon, "
            + "dokter.idDokter AS idDokter, dokter.namaDokter AS namaDokter, dokter.spesialisasi AS spesialisasi");

        return this.get();
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSpesies() { return spesies; }
    public void setSpesies(String spesies) { this.spesies = spesies; }

    public String getNama() { return namaHewan; }
    public void setNama(String nama) { this.namaHewan = nama; }

    public int getUsiaBulan() { return usia_bulan; } 
    public void setUsiaBulan(int usiaBulan) { this.usia_bulan = usiaBulan; } 

    public Pelanggan getPelanggan() { return pelanggan; } 
    public void setPelanggan(Pelanggan pelanggan) { this.pelanggan = pelanggan; }
}