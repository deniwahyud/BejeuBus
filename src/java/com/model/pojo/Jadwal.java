package com.model.pojo;
// Generated Oct 3, 2019 2:08:48 PM by Hibernate Tools 4.3.1



/**
 * Jadwal generated by hbm2java
 */
public class Jadwal  implements java.io.Serializable {


     private Integer idJadwal;
     private String seriBis;
     private String rute;
     private String jamBerangkat;
     private String harga;

    public Jadwal() {
    }

    public Jadwal(String seriBis, String rute, String jamBerangkat, String harga) {
       this.seriBis = seriBis;
       this.rute = rute;
       this.jamBerangkat = jamBerangkat;
       this.harga = harga;
    }
   
    public Integer getIdJadwal() {
        return this.idJadwal;
    }
    
    public void setIdJadwal(Integer idJadwal) {
        this.idJadwal = idJadwal;
    }
    public String getSeriBis() {
        return this.seriBis;
    }
    
    public void setSeriBis(String seriBis) {
        this.seriBis = seriBis;
    }
    public String getRute() {
        return this.rute;
    }
    
    public void setRute(String rute) {
        this.rute = rute;
    }
    public String getJamBerangkat() {
        return this.jamBerangkat;
    }
    
    public void setJamBerangkat(String jamBerangkat) {
        this.jamBerangkat = jamBerangkat;
    }
    public String getHarga() {
        return this.harga;
    }
    
    public void setHarga(String harga) {
        this.harga = harga;
    }




}

