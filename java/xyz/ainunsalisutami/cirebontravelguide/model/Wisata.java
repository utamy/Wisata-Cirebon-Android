package xyz.ainunsalisutami.cirebontravelguide.model;

public class Wisata {

    private String nama, alamat, url, deskripsi, id, jenis_wisata, lat, lng;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
    public String getDeskripsi(){
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi){
        this.deskripsi = deskripsi;
    }
    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getJenis_wisata() {
        return jenis_wisata;
    }
    public void setJenis_wisata(String jenis_wisata){
        this.jenis_wisata = jenis_wisata;
    }
    public String getLat() {
        return lat;
    }
    public void setLat(String lat){
        this.lat = lat;
    }
    public String getLng() {
        return lng;
    }
    public void setLng(String lng){
        this.lng = lng;
    }
}
