package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import models.RekamMedis;

public class Hewan extends Model<Hewan> {
    private int id;                // Primary Key
    private String spesies;        // Species of the animal
    private String namaHewan;      // Name of the animal
    private int usiaBulan;         // Age in months
    private int pemilik;         // Store only the ID of Pelanggan (owner)

    public Hewan() {
        this.table = "hewan";      // Table name
        this.primaryKey = "id";    // Primary key column name
    }

    public Hewan(int id, String spesies, String namaHewan, int usiaBulan, int pemilik) {
        this();  // Call default constructor to set table and primary key
        this.id = id;
        this.spesies = spesies;
        this.namaHewan = namaHewan;
        this.usiaBulan = usiaBulan;
        this.pemilik = pemilik;  // Set owner ID directly
    }

    @Override
    public Hewan toModel(ResultSet rs) {
        try {
            return new Hewan(
                rs.getInt("id"),            // Match with SQL column name
                rs.getString("spesies"),
                rs.getString("namaHewan"),
                rs.getInt("usiaBulan"),     // Match with SQL column name
                rs.getInt("pemilik")         // Get owner ID directly from ResultSet
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public void insert() {
        String sql = "INSERT INTO " + table + " (spesies, namaHewan, usiaBulan, pemilik) VALUES (?, ?, ?, ?)";
        
        try {
            connect(); // Connect to the database
            
            try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, spesies);
                pstmt.setString(2, namaHewan);
                pstmt.setInt(3, usiaBulan);
                pstmt.setInt(4, pemilik);

                // Execute the insertion
                int affectedRows = pstmt.executeUpdate();
                
                // Check if the insertion was successful
                if (affectedRows > 0) {
                    // Retrieve and set the generated keys
                    try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            this.id = generatedKeys.getInt(1); // Set the generated ID
                        }
                    }
                }
                System.out.println("Inserted: " + affectedRows + " rows.");
            }
        } catch (SQLException e) {
            System.out.println("Error during inserting Hewan: " + e.getMessage());
        } finally {
            disconnect(); // Ensure the database connection is closed
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

    public int getPemilikId() { return pemilik; } 
    public void setPemilikId(int pemilik) { 
        this.pemilik = pemilik; 
    } 
 
    public Hewan find(int id) {
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
    
    public Pelanggan getPelangganById() {
        Pelanggan pelanggan = new Pelanggan();
        return pelanggan.find(this.pemilik); // Assuming find() is a method in Hewan class to fetch a Hewan by ID.
    }
}