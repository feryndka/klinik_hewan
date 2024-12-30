package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RekamMedis extends Model<RekamMedis> {
    private int idRekam;           // Primary Key
    private String diagnosa;       // Diagnosis details
    private String perawatan;      // Treatment details
    private int hewan;             // Foreign Key to Hewan
    private int pemilik;           // Foreign Key to Pelanggan (owner)
    private int dokter;            // Foreign Key to Dokter

    public RekamMedis() {
        this.table = "rekam_medis";  // Table name
        this.primaryKey = "idRekam";  // Primary key column name
    }

     public RekamMedis(int idRekam, String diagnosa, String perawatan, int hewan, int pemilik, int dokter) {
         this();  // Call default constructor to set table and primary key
         this.idRekam = idRekam;
         this.diagnosa = diagnosa;
         this.perawatan = perawatan;
         this.hewan = hewan;
         this.pemilik = pemilik;
         this.dokter = dokter;
     }

     @Override
     public RekamMedis toModel(ResultSet rs) { 
         try { 
             return new RekamMedis(
                 rs.getInt("idRekam"),      // Match with SQL column name 
                 rs.getString("diagnosa"),
                 rs.getString("perawatan"),
                 rs.getInt("hewan"),          // Foreign Key to Hewan 
                 rs.getInt("pemilik"),        // Foreign Key to Pelanggan  
                 rs.getInt("dokter")          // Foreign Key to Dokter  
             ); 
         } catch (SQLException e) { 
             System.out.println("Error: " + e.getMessage()); 
             return null; 
         } 
     } 

     public int getIdRekam() {  
         return idRekam;  
     }  

     public void setIdRekam(int idRekam) {  
         this.idRekam = idRekam;  
     }  

     public String getDiagnosa() {  
         return diagnosa;  
     }  

     public void setDiagnosa(String diagnosa) {  
         this.diagnosa = diagnosa;  
     }  

     public String getPerawatan() {  
         return perawatan;  
     }  

     public void setPerawatan(String perawatan) {  
         this.perawatan = perawatan;  
      }
      
      public int getHewan() {   
          return hewan;   
      }
      
      public void setHewan(int hewan) {   
          this.hewan = hewan;   
      }
      
      public int getPemilik() {   
          return pemilik;   
      }
      
      public void setPemilik(int pemilik) {   
          this.pemilik= pemilik ;   
      }
      
      public int getDokter() {   
          return dokter ;   
      }
      
      public void setDokter(int dokter ) {   
          this.dokter= dokter ;   
      }
}