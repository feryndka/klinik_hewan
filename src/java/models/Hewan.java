package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Hewan extends Model<Hewan> {
    private int id;                // Primary Key
    private String spesies;        // Species of the animal
    private String nama;           // Name of the animal
    private int usiaBulan;         // Age in months
    private Pelanggan pemilik;           // Foreign Key to Pelanggan

    public Hewan() {
        this.table = "hewan";      // Table name
        this.primaryKey = "id";    // Primary key column name
    }

    public Hewan(int id, String spesies, String nama, int usiaBulan, Pelanggan pemilik) {
        this();  // Call default constructor to set table and primary key
        this.id = id;
        this.spesies = spesies;
        this.nama = nama;
        this.usiaBulan = usiaBulan;
        this.pemilik = pemilik;
    }

    @Override
    public Hewan toModel(ResultSet rs) {
        try {
            Pelanggan pemilik = new Pelanggan().toModel(rs);
            
            return new Hewan(
                rs.getInt("id"),            // Match with SQL column name
                rs.getString("spesies"),
                rs.getString("nama"),
                rs.getInt("usia_bulan"),     // Match with SQL column name
                pemilik         // Foreign Key to Pelanggan
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

   public String getSpesies() {
       return spesies;
   }

   public void setSpesies(String spesies) {
       this.spesies = spesies;
   }

   public String getNama() {
       return nama;
   }

   public void setNama(String nama) {
       this.nama = nama;
   }

   public int getUsiaBulan() { 
       return usiaBulan; 
   } 

   public void setUsiaBulan(int usiaBulan) { 
       this.usiaBulan = usiaBulan; 
   } 

   public Pelanggan getPemilik() { 
       return pemilik; 
   } 

   public void setPemilik(Pelanggan pemilik) { 
       this.pemilik = pemilik; 
   }
}