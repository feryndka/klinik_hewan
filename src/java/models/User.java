package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Model<User> {
    private int id;
    private String username;
    private String password;
    private String role;

    public User() {
        this.table = "users"; // Nama tabel
        this.primaryKey = "id"; // Primary key tabel
    }
    
    public User(int id, String username, String password, String role) {
        this();
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public User toModel(ResultSet rs) {
        try {
            return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role")
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String alamat) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    
     public void setRole(String role) {
        this.role = role;
    }
}
