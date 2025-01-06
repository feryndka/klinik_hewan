package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Klinik extends Model<Klinik> {
    private int idKlinik;          // Change from 'id' to 'idKlinik' for clarity
    private String namaKlinik;
    private String alamatKlinik;
    private String jamOperasional;  // Added field for operational hours

    public Klinik() {
        this.table = "klinik";      // Table name
        this.primaryKey = "idKlinik"; // Primary key column name
    }

    public Klinik(int idKlinik, String nama, String alamat, String jamOperasional) {
        this();  // Call default constructor to set table and primary key
        this.idKlinik = idKlinik;  // Set fields based on parameters
        this.namaKlinik = nama;
        this.alamatKlinik = alamat;
        this.jamOperasional = jamOperasional;
    }

    @Override
    public Klinik toModel(ResultSet rs) {
        try {
            return new Klinik(
                rs.getInt("idKlinik"),      // Match with SQL column name
                rs.getString("namaKlinik"),
                rs.getString("alamatKlinik"),
                rs.getString("jamOperasional")  // Ensure correct column name is used
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    // Getters and Setters
    public int getIdKlinik() { return idKlinik; } // Updated getter method name for clarity
    public void setIdKlinik(int idKlinik) { this.idKlinik = idKlinik; } // Updated setter method name for clarity

    public String getNama() { return namaKlinik; }
    public void setNama(String nama) { this.namaKlinik = nama; }

    public String getAlamat() { return alamatKlinik; }
    public void setAlamat(String alamat) { this.alamatKlinik = alamat; }

    public String getJamOperasional() { return jamOperasional; }
    public void setJamOperasional(String jamOperasional) { this.jamOperasional = jamOperasional; }
}