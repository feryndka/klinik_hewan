package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Model<User> {
    private int idUser;          // Changed from id to idUser for clarity
    private String username;
    private String password;
    private String role;
    private Pelanggan pelanggan;   // Foreign Key to Pelanggan (nullable)
    private Dokter dokter;      // Foreign Key to Dokter (nullable)

    public User() {
        this.table = "user"; // Table name
        this.primaryKey = "idUser"; // Primary key table
    }

    public User(int idUser, String username, String password, String role) {
        this();
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(int idUser, String username, String password, String role, Pelanggan pelanggan, Dokter dokter) {
        this(idUser, username, password, role); // Call existing constructor
        this.pelanggan = pelanggan;  // Set foreign keys
        this.dokter = dokter;
    }

    @Override
    public User toModel(ResultSet rs) {
        try {
            Pelanggan pelanggan = null;
            Dokter dokter = null;

            if (rs.getString("role").equals("pasien")) {
                pelanggan = new Pelanggan().toModel(rs);
            } else if (rs.getString("role").equals("dokter")) {
                dokter = new Dokter().toModel(rs);
            }
            
            return new User(
                rs.getInt("idUser"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role"),
                pelanggan,  // Handle nullable foreign key
                dokter      // Handle nullable foreign key
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<User> getAllUserWithRelations() {
        this.join("pelanggan", "user.pelanggan = pelanggan.idPelanggan")
            .join("dokter", "user.dokter = dokter.idDokter")
            .where("username = '" + username + "' AND password = '" + password + "'");

        this.select("user.*, "
            + "pelanggan.idPelanggan AS idPelanggan, pelanggan.namaPelanggan AS namaPelanggan, "
            + "pelanggan.alamatPelanggan AS alamatPelanggan, pelanggan.nomor_telepon AS nomor_telepon, "
            + "dokter.idDokter AS idDokter, dokter.namaDokter AS namaDokter, dokter.spesialisasi AS spesialisasi");

        return this.get();
    }

    // Getters and Setters
    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; } // Consider hashing before returning
    public void setPassword(String password) { this.password = password; } // Consider hashing before setting 

    public String getRole() { return role; } 
    public void setRole(String role) { this.role = role; } 

    public Pelanggan getPelanggan() { return pelanggan; } 
    public void setPelanggan(Pelanggan pelanggan) { this.pelanggan = pelanggan; } 

    public Dokter getDokter() { return dokter; } 
    public void setDokter(Dokter dokter) { this.dokter = dokter; }
}
