package com.tugas7.demo.models;

public class Kelas {
    private String kodeKelas;
    private String namaKelas;
    private String deskripsi;
    private int kapasitasMaksimal;
    private double harga;

    public Kelas() {}

    public Kelas(String kodeKelas, String namaKelas, String deskripsi, int kapasitasMaksimal, double harga) {
        this.kodeKelas = kodeKelas;
        this.namaKelas = namaKelas;
        this.deskripsi = deskripsi;
        this.kapasitasMaksimal = kapasitasMaksimal;
        this.harga = harga;
    }

    public String getKodeKelas() {
        return kodeKelas;
    }

    public void setKodeKelas(String kodeKelas) {
        this.kodeKelas = kodeKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getKapasitasMaksimal() {
        return kapasitasMaksimal;
    }

    public void setKapasitasMaksimal(int kapasitasMaksimal) {
        this.kapasitasMaksimal = kapasitasMaksimal;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
