package com.tugas7.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tugas7.demo.models.Obat;

@Service
public class ObatService {
    private List<Obat> daftarObat = new ArrayList<>();

    public List<Obat> getAll() {
        return daftarObat;
    }

    public void save(Obat obat) {
        Obat existing = getByKode(obat.getKodeObat());
        if (existing != null) {
            daftarObat.remove(existing);
        }
        daftarObat.add(obat);
    }

    public Obat getByKode(String kode) {
        return daftarObat.stream()
                .filter(o -> o.getKodeObat().equalsIgnoreCase(kode))
                .findFirst()
                .orElse(null);
    }

    public void delete(String kode) {
        Obat obat = getByKode(kode);
        if (obat != null) daftarObat.remove(obat);
    }
}
