package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Pelanggan extends Model<Pelanggan> {
    private int idPelanggan;        // Primary Key
    private String namaPelanggan;   // Name
    private String alamatPelanggan; // Address
    private long nomorTelepon;      // Phone number
    private Klinik klinik;          // Foreign Key to Klinik

    public Pelanggan() {
        this.table = "pelanggan";   // Table name
        this.primaryKey = "idPelanggan"; // Primary key column name
    }

    public Pelanggan(int idPelanggan, String nama, String alamat, long nomorTelepon, Klinik klinik) {
        this();  // Call default constructor to set table and primary key
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = nama;
        this.alamatPelanggan = alamat;
        this.nomorTelepon = nomorTelepon;
        this.klinik = klinik;
    }

    @Override
    public Pelanggan toModel(ResultSet rs) {
        try {
            Klinik klinik = new Klinik().toModel(rs);
             
            return new Pelanggan(
                rs.getInt("idPelanggan"),           // Match with SQL column name
                rs.getString("namaPelanggan"),
                rs.getString("alamatPelanggan"),
                rs.getLong("nomor_telepon"),        // Match with SQL column name
                klinik                              // Foreign Key to Klinik
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    // Getters and setters
    public int getIdPelanggan() { return idPelanggan; }
    public void setIdPelanggan(int idPelanggan) { this.idPelanggan = idPelanggan; }

    public String getNama() { return namaPelanggan; }
    public void setNama(String nama) { this.namaPelanggan = nama; }

    public String getAlamat() { return alamatPelanggan; }
    public void setAlamat(String alamat) { this.alamatPelanggan = alamat; }

    public long getNomorTelepon() { return nomorTelepon; }
    public void setNomorTelepon(long nomorTelepon) { this.nomorTelepon = nomorTelepon; }

    public Klinik getKlinik() { return klinik; } 
    public void setKlinik(Klinik klinik) { this.klinik = klinik; }
}
