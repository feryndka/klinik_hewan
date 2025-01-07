package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dokter extends Model<Dokter> {
    private int idDokter;           // Primary Key
    private String namaDokter;      // Name of the doctor
    private String spesialisasi;    // Specialization of the doctor
    private Integer klinik;       // Foreign Key to Klinik (nullable)

    public Dokter() {
        this.table = "dokter";          // Table name
        this.primaryKey = "idDokter";   // Primary key column name
    }

    public Dokter(int idDokter, String nama, String spesialisasi, Integer klinik) {
        this();  // Call default constructor to set table and primary key
        this.idDokter = idDokter;
        this.namaDokter = nama;
        this.spesialisasi = spesialisasi;
        this.klinik = klinik; // Set foreign key as ID
    }

    @Override
    public Dokter toModel(ResultSet rs) {
        try {
            return new Dokter(
                rs.getInt("idDokter"),         // Match with SQL column name
                rs.getString("namaDokter"),
                rs.getString("spesialisasi"),
                rs.getObject("klinik", Integer.class) // Fetch klinik as Integer
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<Dokter> getAllDokterWithRelations() {
        this.join("klinik", "dokter.klinik = klinik.idKlinik"); // Changed to klinik
        this.select("dokter.*, "
            + "klinik.idKlinik AS idKlinik, klinik.namaKlinik AS namaKlinik, "
            + "klinik.alamatKlinik AS alamatKlinik, klinik.jamOperasional AS jamOperasional");
        return this.get();
    }

    // Getters and setters
    public int getIdDokter() { return idDokter; }
    public void setIdDokter(int idDokter) { this.idDokter = idDokter; }
    public String getNama() { return namaDokter; }
    public void setNama(String nama) { this.namaDokter = nama; }
    public String getSpesialisasi() { return spesialisasi; }
    public void setSpesialisasi(String spesialisasi) { this.spesialisasi = spesialisasi; }
    public Integer getKlinikId() { return klinik; } // Getter for klinik
    public void setKlinikId(Integer klinik) { this.klinik = klinik; } // Setter for klinik

    public Dokter find(int id) {
        try {
            connect(); // Connect to the database
            String query = "SELECT " + select + " FROM " + table + " WHERE " + primaryKey + " = " + id;
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return toModel(rs); // Convert ResultSet to Dokter model
            }
        } catch (SQLException e) {
            setMessage(e.getMessage()); // Set any error messages from the exception
        } finally {
            disconnect(); // Ensure the database connection is closed
            select = "*"; // Reset the select statement for future queries
        }
        return null; // Return null if the object was not found
    }
}