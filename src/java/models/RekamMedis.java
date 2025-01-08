package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RekamMedis extends Model<RekamMedis> {
    private int idRekam;         // Primary Key
    private String diagnosa;     // Diagnosis details
    private String perawatan;    // Treatment details
    private int hewan;           // Store only the ID of Hewan
    private int pemilik;         // Store only the ID of Pelanggan (owner)
    private Integer dokter;      // Store only the ID of Dokter
    private int klinik;          // Store only the ID of Klinik

    public RekamMedis() {
        this.table = "rekam_medis";  // Table name
        this.primaryKey = "idRekam"; // Primary key column name
    }

    public RekamMedis(int idRekam, String diagnosa, String perawatan, int hewan, int pemilik, int dokter, int klinik) {
        this();  // Call default constructor to set table and primary key
        this.idRekam = idRekam;
        this.diagnosa = diagnosa;
        this.perawatan = perawatan;
        this.hewan = hewan;
        this.pemilik = pemilik;
        this.dokter = dokter;
        this.klinik = klinik;
    }

    @Override
    public RekamMedis toModel(ResultSet rs) {
        try {
            return new RekamMedis(
                rs.getInt("idRekam"),
                rs.getString("diagnosa"),
                rs.getString("perawatan"),
                rs.getInt("hewan"),                     // Get Hewan ID directly from ResultSet
                rs.getInt("pemilik"),                   // Get Pelanggan ID directly from ResultSet
                rs.getObject("dokter", Integer.class),  // Get Dokter ID directly from ResultSet
                rs.getInt("klinik")                     // Get Klinik ID directly from ResultSet
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

   public ArrayList<RekamMedis> getAllRekamMedisWithRelations() {
        this.join("hewan", "rekam_medis.hewan = hewan.id")
            .join("pelanggan", "rekam_medis.pemilik = pelanggan.idPelanggan")
            .join("dokter", "rekam_medis.dokter = dokter.idDokter")
            .join("klinik", "rekam_medis.klinik = klinik.idKlinik");
        this.select("rekam_medis.*, "
            + "hewan.spesies AS spesies, hewan.namaHewan AS namaHewan, "
            + "pelanggan.namaPelanggan AS namaPelanggan, "
            + "dokter.namaDokter AS namaDokter, "
            + "klinik.namaKlinik AS namaKlinik");
        
        return this.get();
   }

    // Getters and setters for all fields including IDs.
    public int getIdRekam() { return idRekam; }
    public void setIdRekam(int idRekam) { this.idRekam = idRekam; }
    
    public String getDiagnosa() { return diagnosa; }
    public void setDiagnosa(String diagnosa) { this.diagnosa = diagnosa; }
    
    public String getPerawatan() { return perawatan; }
    public void setPerawatan(String perawatan) { this.perawatan = perawatan; }

    public int getHewan() { return hewan; }
    public void setHewan(int hewan) { this.hewan = hewan; } 

    public int getPelanggan() { return pemilik; } 
    public void setPelanggan(int pemilik) { this.pemilik = pemilik; } 

    public int getDokter() { return dokter; } 
    public void setDokter(Integer dokter) { this.dokter= dokter ; } 

    public int getKlinik() { return klinik ; }  
    public void setKlinik(int klinik ) { this.klinik= klinik ; }
    
    public RekamMedis find(int id) {
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

    public Hewan getHewanById() {
        Hewan hewan = new Hewan();
        return hewan.find(this.hewan); // Assuming find() is a method in Hewan class to fetch a Hewan by ID.
    }

    public Dokter getDokterById() {
        Dokter dokter = new Dokter();
        return dokter.find(this.dokter); // Assuming find() is a method in Hewan class to fetch Dokter by ID.
    }
    
    public Klinik getKlinikById() {
        Klinik klinik = new Klinik();
        return klinik.find(this.klinik); // Assuming find() is a method in Hewan class to fetch a Hewan by ID.
    }
    
    public Pelanggan getPelangganById() {
        Pelanggan pelanggan = new Pelanggan();
        return pelanggan.find(this.pemilik); // Assuming find() is a method in Hewan class to fetch a Hewan by ID.
    }
}