package com.example.angkoters.model;

import com.google.gson.annotations.SerializedName;

public class RuteAngkotItem{

    @SerializedName("lintasan")
    private String lintasan;

    @SerializedName("gambar_url")
    private String gambarUrl;

    @SerializedName("trayek")
    private String trayek;

    @SerializedName("id")
    private Integer id;

    @SerializedName("nomor_angkot")
    private String nomorAngkot;

    public void setLintasan(String lintasan){
        this.lintasan = lintasan;
    }

    public String getLintasan(){
        return lintasan;
    }

    public void setGambarUrl(String gambarUrl){
        this.gambarUrl = gambarUrl;
    }

    public String getGambarUrl(){
        return gambarUrl;
    }

    public void setTrayek(String trayek){
        this.trayek = trayek;
    }

    public String getTrayek(){
        return trayek;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setNomorAngkot(String nomorAngkot){
        this.nomorAngkot = nomorAngkot;
    }

    public String getNomorAngkot(){
        return nomorAngkot;
    }
}