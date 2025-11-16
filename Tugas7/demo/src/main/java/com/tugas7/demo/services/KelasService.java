package com.tugas7.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tugas7.demo.models.Kelas;

@Service
public class KelasService {
    private List<Kelas> daftarKelas = new ArrayList<>();

    public List<Kelas> getAll() {
        return daftarKelas;
    }

    public Kelas getByKode(String kode) {
        return daftarKelas.stream()
                .filter(k -> k.getKodeKelas().equalsIgnoreCase(kode))
                .findFirst()
                .orElse(null);
    }

    public String validate(Kelas kelas, boolean isNew) {
        if (kelas.getKodeKelas() == null || kelas.getKodeKelas().trim().isEmpty()) {
            return "Kode kelas tidak boleh kosong";
        }
        if (kelas.getNamaKelas() == null || kelas.getNamaKelas().trim().isEmpty()) {
            return "Nama kelas tidak boleh kosong";
        }
        if (kelas.getDeskripsi() == null || kelas.getDeskripsi().trim().isEmpty()) {
            return "Deskripsi tidak boleh kosong";
        }
        if (kelas.getKapasitasMaksimal() <= 0) {
            return "Kapasitas harus berupa angka positif";
        }
        if (kelas.getHarga() <= 0) {
            return "Harga harus berupa angka positif";
        }
        if (isNew && isDuplicateCode(kelas.getKodeKelas())) {
            return "Kode kelas sudah digunakan";
        }
        return null;
    }

    public boolean isDuplicateCode(String kode) {
        return daftarKelas.stream()
                .anyMatch(k -> k.getKodeKelas().equalsIgnoreCase(kode));
    }

    public void save(Kelas kelas) {
        daftarKelas.add(kelas);
    }

    public void delete(String kode) {
        Kelas kelas = getByKode(kode);
        if (kelas != null) {
            daftarKelas.remove(kelas);
        }
    }
}
