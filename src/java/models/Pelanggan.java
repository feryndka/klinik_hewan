package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Pelanggan extends Model<Pelanggan> {
    private int idPelanggan;        // Primary Key
    private String namaPelanggan;   // Name
    private String alamatPelanggan; // Address
    private long nomorTelepon;      // Phone number
    private Integer klinik;         // Foreign Key to Klinik (nullable)

    public Pelanggan() {
        this.table = "pelanggan";        // Table name
        this.primaryKey = "idPelanggan"; // Primary key column name
    }

    public Pelanggan(int idPelanggan, String nama, String alamat, long nomorTelepon, Integer klinik) {
        this();  // Call default constructor to set table and primary key
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = nama;
        this.alamatPelanggan = alamat;
        this.nomorTelepon = nomorTelepon;
        this.klinik = klinik;  // Set foreign key as ID
    }

    @Override
    public Pelanggan toModel(ResultSet rs) {
        try {
            return new Pelanggan(
                rs.getInt("idPelanggan"),             // Match with SQL column name
                rs.getString("namaPelanggan"),
                rs.getString("alamatPelanggan"),
                rs.getLong("nomor_telepon"),          // Match with SQL column name
                rs.getObject("klinik", Integer.class) // Fetch klinik as Integer
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
    
    public Integer getKlinikId() { return klinik; } // Getter for klinik
    public void setKlinikId(Integer klinik) { this.klinik = klinik; } // Setter for klinik
    
    public Pelanggan find(int id) {
        try {
            connect(); // Connect to the database
            String query = "SELECT " + select + " FROM " + table + " WHERE " + primaryKey + " = " + id;
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return toModel(rs); // Convert ResultSet to Doktermodel
            }
        } catch (SQLException e) {
            setMessage(e.getMessage()); // Set any error messages from the exception
        } finally {
            disconnect(); // Ensure the database connection is closed
            select = "*"; // Reset the select statement for future queries
        }
        return null; // Return null if the object was not found
    }
    
    public Klinik getKlinikById() {
        Klinik klinik = new Klinik();
        return klinik.find(this.klinik); // Assuming find() is a method in Hewan class to fetch a Hewan by ID.
    }
}