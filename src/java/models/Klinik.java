package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Klinik extends Model<Klinik> {
    private int id;
    private String nama;
    private String alamat;
    private String jamOperasional;

    public Klinik() {
        // (isi konstruktor default klinik dengan ketentuan
        // table di isi dengan name table, primary key diisi dengan kolom id)
        this.table = "klinik";
        this.primaryKey = "id";
    }
    
    public Klinik(int id, String nama, String alamat, String jamOperasional) {
        // (isi konstruktor klinik dengan menyesuaikan value yang ada pada parameter)
        this();
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.jamOperasional = jamOperasional;
    }

    @Override
    public Klinik toModel(ResultSet rs) {
        try {
            // (dapatkan nilai setiap kolom pada db menggunakan rs, perhatikan juga tipe datanya
            // data yang diambil menggunakan rs harus dimasukkan ke dalam model Klinik agar sesuai dengan konsep arsitektur MVC (Model-View-Controller)
            return new Klinik(
                rs.getInt("id"),
                rs.getString("nama"),
                rs.getString("alamat"),
                rs.getString("jamOperasional")
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJamOperasional() {
        return jamOperasional;
    }
    
     public void setJamOperasional(String jamOperasional) {
        this.jamOperasional = jamOperasional;
    }
}
