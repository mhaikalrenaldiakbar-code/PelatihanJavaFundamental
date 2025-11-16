package com.tugas7.demo.models;

public class Peserta {
    private String nama;
    private String email;
    private String nomorHP;

    public Peserta() {}

    public Peserta(String nama, String email, String nomorHP) {
        this.nama = nama;
        this.email = email;
        this.nomorHP = nomorHP;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomorHP() {
        return nomorHP;
    }

    public void setNomorHP(String nomorHP) {
        this.nomorHP = nomorHP;
    }
}
