package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Model<User> {
    private int idUser;          // Changed from id to idUser for clarity
    private String username;
    private String password;
    private String role;
    private Integer pelanggan;   // Foreign Key to Pelanggan (nullable)
    private Integer dokter;      // Foreign Key to Dokter (nullable)

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

    public User(int idUser, String username, String password, String role, Integer pelanggan, Integer dokter) {
        this(idUser, username, password, role); // Call existing constructor
        this.pelanggan = pelanggan;  // Set foreign keys
        this.dokter = dokter;
    }

    @Override
    public User toModel(ResultSet rs) {
        try {
            return new User(
                rs.getInt("idUser"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getObject("pelanggan", Integer.class),  // Handle nullable foreign key
                rs.getObject("dokter", Integer.class)      // Handle nullable foreign key
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    // Getters and Setters

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

   public void setUsername(String username) {
       this.username = username;
   }

   public String getPassword() {
       return password;  // Consider hashing before returning
   }

   public void setPassword(String password) { 
       this.password = password;  // Consider hashing before setting 
   }

   public String getRole() { 
       return role; 
   } 

   public void setRole(String role) { 
       this.role = role; 
   } 

   public Integer getPelanggan() { 
       return pelanggan; 
   } 

   public void setPelanggan(Integer pelanggan) { 
       this.pelanggan = pelanggan; 
   } 

  public Integer getDokter() { 
      return dokter; 
  } 

  public void setDokter(Integer dokter) { 
      this.dokter = dokter; 
  }
}