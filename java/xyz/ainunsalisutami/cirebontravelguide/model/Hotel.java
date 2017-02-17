package xyz.ainunsalisutami.cirebontravelguide.model;

/**
 * Created by Ainun on 10/05/2016.
 */
public class Hotel {
    private String nama, url, alamat, id, jenis_hotel, fasilitas, no_tlp, lat, lng;

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
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getAlamat(){
        return  alamat;
    }
    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
    public String getJenis_hotel() {
        return jenis_hotel;
    }
    public void setJenis_hotel(String jenis_hotel){
        this.jenis_hotel = jenis_hotel;
    }
    public String getFasilitas() {
        return fasilitas;
    }
    public void setFasilitas(String fasilitas){
        this.fasilitas = fasilitas;
    }
    public String getNo_tlp() {
        return no_tlp;
    }
    public void setNo_tlp(String no_tlp){
        this.no_tlp = no_tlp;
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
