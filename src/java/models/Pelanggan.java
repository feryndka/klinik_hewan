package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Pelanggan extends Model<Pelanggan> {
    private int idPelanggan;        // Primary Key
    private String alamat;          // Address
    private long nomorTelepon;      // Phone number
    private int klinik;             // Foreign Key to Klinik

    public Pelanggan() {
        this.table = "pelanggan";   // Table name
        this.primaryKey = "idPelanggan"; // Primary key column name
    }

    public Pelanggan(int idPelanggan, String alamat, long nomorTelepon, int klinik) {
        this();  // Call default constructor to set table and primary key
        this.idPelanggan = idPelanggan;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
        this.klinik = klinik;
    }

    @Override
    public Pelanggan toModel(ResultSet rs) {
        try {
            return new Pelanggan(
                rs.getInt("idPelanggan"),   // Match with SQL column name
                rs.getString("alamat"),
                rs.getLong("nomor_telepon"), // Match with SQL column name
                rs.getInt("klinik")          // Foreign Key to Klinik
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public String getAlamat() {
        return alamat;
    }

   public void setAlamat(String alamat) {
       this.alamat = alamat;
   }

   public long getNomorTelepon() {
       return nomorTelepon;
   }

   public void setNomorTelepon(long nomorTelepon) {
       this.nomorTelepon = nomorTelepon;
   }

   public int getKlinik() { 
       return klinik; 
   } 

   public void setKlinik(int klinik) { 
       this.klinik = klinik; 
   }
}