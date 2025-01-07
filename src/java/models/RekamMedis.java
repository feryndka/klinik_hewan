package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RekamMedis extends Model<RekamMedis> {
    private int idRekam;           // Primary Key
    private String diagnosa;       // Diagnosis details
    private String perawatan;      // Treatment details
    private int hewanId;           // Store only the ID of Hewan
    private int pemilikId;         // Store only the ID of Pelanggan (owner)
    private Integer dokterId;          // Store only the ID of Dokter
    private int klinikId;          // Store only the ID of Klinik

    public RekamMedis() {
        this.table = "rekam_medis";  // Table name
        this.primaryKey = "idRekam";  // Primary key column name
    }

    public RekamMedis(int idRekam, String diagnosa, String perawatan, int hewanId, int pemilikId, int dokterId, int klinikId) {
        this();  // Call default constructor to set table and primary key
        this.idRekam = idRekam;
        this.diagnosa = diagnosa;
        this.perawatan = perawatan;
        this.hewanId = hewanId;
        this.pemilikId = pemilikId;
        this.dokterId = dokterId;
        this.klinikId = klinikId;
    }

    @Override
    public RekamMedis toModel(ResultSet rs) {
        try {
            return new RekamMedis(
                rs.getInt("idRekam"),
                rs.getString("diagnosa"),
                rs.getString("perawatan"),
                rs.getInt("hewan"),         // Get Hewan ID directly from ResultSet
                rs.getInt("pemilik"),       // Get Pelanggan ID directly from ResultSet
                rs.getObject("dokter", Integer.class),        // Get Dokter ID directly from ResultSet
                rs.getInt("klinik")         // Get Klinik ID directly from ResultSet
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

    public int getHewan() { 
        return hewanId; 
    }
    public void setHewan(int hewanId) { 
        this.hewanId = hewanId; 
    } 

    public int getPelanggan() { return pemilikId; } 
    public void setPelanggan(int pemilikId) { 
        this.pemilikId = pemilikId; 
    } 

    public int getDokter() { return dokterId; } 
    public void setDokter(Integer dokterId) { 
        this.dokterId= dokterId ; 
    } 

    public int getKlinik() { return klinikId ; }  
    public void setKlinik(int klinikId ) {  
       this.klinikId= klinikId ;  
    }
    
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
        return hewan.find(this.hewanId); // Assuming find() is a method in Hewan class to fetch a Hewan by ID.
    }

    public Dokter getDokterById() {
        Dokter dokter = new Dokter();
        return dokter.find(this.dokterId); // Assuming find() is a method in Hewan class to fetch Dokter by ID.
    }
    
    public Klinik getKlinikById() {
        Klinik klinik = new Klinik();
        return klinik.find(this.klinikId); // Assuming find() is a method in Hewan class to fetch a Hewan by ID.
    }
}