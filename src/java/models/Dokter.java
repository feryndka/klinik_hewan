package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dokter extends Model<Dokter> {
    private int idDokter;           // Primary Key
    private String nama;            // Name of the doctor
    private String spesialisasi;     // Specialization of the doctor
    private Klinik klinik;             // Foreign Key to Klinik

    public Dokter() {
        this.table = "dokter";      // Table name
        this.primaryKey = "idDokter";  // Primary key column name
    }

    public Dokter(int idDokter, String nama, String spesialisasi, Klinik klinik) {
        this();  // Call default constructor to set table and primary key
        this.idDokter = idDokter;
        this.nama = nama;
        this.spesialisasi = spesialisasi;
        this.klinik = klinik;
    }

    @Override
    public Dokter toModel(ResultSet rs) { 
       try { 
           Klinik klinik = new Klinik().toModel(rs);
           return new Dokter(
               rs.getInt("idDokter"),     // Match with SQL column name 
               rs.getString("nama"),
               rs.getString("spesialisasi"),
               klinik         // Foreign Key to Klinik 
           ); 
       } catch (SQLException e) { 
           System.out.println("Error: " + e.getMessage()); 
           return null; 
       } 
    } 

     public int getIdDokter() { 
         return idDokter; 
     } 

     public void setIdDokter(int idDokter) { 
         this.idDokter = idDokter; 
     } 

     public String getNama() {  
         return nama;  
     }  

     public void setNama(String nama) {  
         this.nama = nama;  
     }  

     public String getSpesialisasi() {  
         return spesialisasi;  
     }  

     public void setSpesialisasi(String spesialisasi) {  
         this.spesialisasi = spesialisasi;  
     }  

     public Klinik getKlinik() {  
         return klinik;  
     }  

     public void setKlinik(Klinik klinik) {  
         this.klinik = klinik;  
      }
}