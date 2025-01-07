package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Hewan extends Model<Hewan> {
    private int id;                // Primary Key
    private String spesies;        // Species of the animal
    private String namaHewan;      // Name of the animal
    private int usiaBulan;         // Age in months
    private int pemilikId;         // Store only the ID of Pelanggan (owner)

    public Hewan() {
        this.table = "hewan";      // Table name
        this.primaryKey = "id";    // Primary key column name
    }
    
    public Hewan(int id, String spesies, String namaHewan, int usiaBulan, int pemilikId) {
        this();  // Call default constructor to set table and primary key
        this.id = id;
        this.spesies = spesies;
        this.namaHewan = namaHewan;
        this.usiaBulan = usiaBulan;
        this.pemilikId = pemilikId;  // Set owner ID directly
    }

    @Override
    public Hewan toModel(ResultSet rs) {
        try {
            return new Hewan(
                rs.getInt("id"),            // Match with SQL column name
                rs.getString("spesies"),
                rs.getString("namaHewan"),
                rs.getInt("usia_bulan"),     // Match with SQL column name
                rs.getInt("pemilik")         // Get owner ID directly from ResultSet
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
           + "pelanggan.alamatPelanggan AS alamatPelanggan, pelanggan.nomor_telepon AS nomor_telepon");
       return this.get();
   }

   // Getters and setters for all fields including IDs.
   public int getId() { return id; }
   public void setId(int id) { this.id = id; }
   public String getSpesies() { return spesies; }
   public void setSpesies(String spesies) { this.spesies = spesies; }
   public String getNama() { return namaHewan; }
   public void setNama(String nama) { this.namaHewan = nama; }
   public int getUsiaBulan() { return usiaBulan; }
   public void setUsiaBulan(int usiaBulan) { this.usiaBulan = usiaBulan; }
   
   public int getPemilikId() { return pemilikId; } 
   public void setPemilikId(int pemilikId) { 
       this.pemilikId = pemilikId; 
   }
}