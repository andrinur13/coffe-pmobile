package com.example.responsi;

public class Pegawai {
    private String email;
    private String nama;
    private String alamat;

    public Pegawai(String email, String nama, String alamat) {
        this.email = email;
        this.nama = nama;
        this.alamat = alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }
}
