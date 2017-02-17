package xyz.ainunsalisutami.cirebontravelguide.model;

/**
 * Created by Ainun on 10/05/2016.
 */
public class Kuliner {

    private String nama, deskripsi, url, id_kuliner, jenis_kuliner,alamat;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public String getDeskripsi(){
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }
    public String getId_kuliner(){
        return id_kuliner;
    }
    public void setId_kuliner(String id_kuliner){
        this.id_kuliner = id_kuliner;
    }
    public String getJenis_kuliner(){
        return jenis_kuliner;
    }
    public void setJenis_kuliner(String jenis_kuliner){
        this.jenis_kuliner = jenis_kuliner;
    }
}
