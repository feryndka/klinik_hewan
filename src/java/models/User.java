package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Model<User> {
    private int idUser;         // Primary Key
    private String username;
    private String password;
    private String role;
    private Integer pelanggan;  // Foreign Key to Pelanggan (nullable)
    private Integer dokter;     // Foreign Key to Dokter (nullable)

    public User() {
        this.table = "user";        // Table name
        this.primaryKey = "idUser"; // Primary key column
    }

    public User(int idUser, String username, String password, String role) {
        this();
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(int idUser, String username, String password, String role, Integer pelanggan, Integer dokter) {
        this(idUser, username, password, role); // Call existing constructor
        this.pelanggan = pelanggan;             // Set foreign key as ID
        this.dokter = dokter;                   // Set foreign key as ID
    }

    @Override
    public User toModel(ResultSet rs) {
        try {
            Integer pelanggan = null;
            Integer dokter = null;
            if (rs.getString("role").equals("pasien")) {
                pelanggan = rs.getInt("pelanggan");
            } else if (rs.getString("role").equals("dokter")) {
                dokter = rs.getInt("dokter");
            }
            
            return new User(
                rs.getInt("idUser"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role"),
                pelanggan,  // Assign foreign key ID
                dokter      // Assign foreign key ID
            );
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<User> getAllUserWithRelations() {
        this.join("pelanggan", "user.pelanggan = pelanggan.idPelanggan") // Changed to pelanggan
            .join("dokter", "user.dokter = dokter.idDokter")             // Changed to dokter
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
    public void setPassword(String password) { this.password = password; } // Hashing is recommended
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public Integer getPelangganId() { return pelanggan; }  // Getter for id pelanggan
    public void setPelangganId(Integer pelanggan) { this.pelanggan = pelanggan; } // Setter for id pelanggan
    
    public Integer getDokterId() { return dokter; } // Getter for id dokter
    public void setDokterId(Integer dokter) { this.dokter = dokter; } // Setter for id dokter
    
    public User find(int id) {
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
        if (this.pelanggan != null) {
            Pelanggan pelanggan = new Pelanggan();
            return pelanggan.find(this.pelanggan);
        }
        return null; // Handle case when pelanggan is null
    }
    
    public Dokter getDokterById() {
        if (this.dokter != null) {
            Dokter dokter = new Dokter();
            return dokter.find(this.dokter);
        }
        return null; // Handle case when dokter is null
    }
    
    public User validateUser(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        User user = null;
        
        try {
            connect(); // Connect to the database
            System.out.println("Database connected successfully."); // Log connection status

            try (PreparedStatement pstmt = con.prepareStatement(sql)) { // Use the connection from Model
                pstmt.setString(1, username);
                pstmt.setString(2, password); // Directly compare the plain text password
                
                System.out.println("Executing query: " + pstmt.toString());
                
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    // Log when user is found
                    System.out.println("User found: " + username);
                    // Create user object on successful validation
                    user = toModel(rs);
                } else {
                    // Log if no user is found
                    System.out.println("No user found with provided credentials for: " + username);
                }
            }
        } catch (SQLException e) {
            // Log SQL exceptions
            System.out.println("Error: " + e.getMessage());
        } finally {
            disconnect(); // Ensure the database connection is closed
        }

        return user; // Return the user object if validated; otherwise, return null
    }
}