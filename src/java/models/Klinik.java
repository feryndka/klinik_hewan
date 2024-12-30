package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Klinik extends Model<Klinik> {
    private int idKlinik;          // Change from 'id' to 'idKlinik' for clarity
    private String nama;
    private String alamat;
    private String jamOperasional;  // Added field for operational hours

    public Klinik() {
        this.table = "klinik";      // Table name
        this.primaryKey = "idKlinik"; // Primary key column name
    }

    public Klinik(int idKlinik, String nama, String alamat, String jamOperasional) {
        this();  // Call default constructor to set table and primary key
        this.idKlinik = idKlinik;  // Set fields based on parameters
        this.nama = nama;
        this.alamat = alamat;
        this.jamOperasional = jamOperasional;
    }

    @Override
    public Klinik toModel(ResultSet rs) {
        try {
            return new Klinik(
                rs.getInt("idKlinik"),      // Match with SQL column name
                rs.getString("nama"),
                rs.getString("alamat"),
                rs.getString("jamOperasional")  // Ensure correct column name is used
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public int getIdKlinik() {       // Updated getter method name for clarity
        return idKlinik;
    }

    public void setIdKlinik(int idKlinik) {  // Updated setter method name for clarity
        this.idKlinik = idKlinik;
    }

    public String getNama() {
        return nama;
    }

   public void setNama(String nama) {
       this.nama = nama;
   }

   public String getAlamat() {
       return alamat;
   }

   public void setAlamat(String alamat) {
       this.alamat = alamat;
   }

   public String getJamOperasional() {
       return jamOperasional; 
   }

   public void setJamOperasional(String jamOperasional) { 
       this.jamOperasional = jamOperasional; 
   }
}