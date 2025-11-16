package com.tugas7.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tugas7.demo.models.Peserta;

@Service
public class PesertaService {
    private HashMap<String, List<Peserta>> daftarPeserta = new HashMap<>();

    public List<Peserta> getPesertaByKelas(String kodeKelas) {
        return daftarPeserta.getOrDefault(kodeKelas, new ArrayList<>());
    }

    public int getRegisteredCount(String kodeKelas) {
        return getPesertaByKelas(kodeKelas).size();
    }

    public boolean isQuotaFull(String kodeKelas, int kapasitasMaksimal) {
        return getRegisteredCount(kodeKelas) >= kapasitasMaksimal;
    }

    public boolean isDuplicateEmail(String kodeKelas, String email) {
        List<Peserta> pesertaList = getPesertaByKelas(kodeKelas);
        return pesertaList.stream()
                .anyMatch(p -> p.getEmail().equalsIgnoreCase(email));
    }

    public String validate(Peserta peserta, String kodeKelas, int kapasitasMaksimal) {
        if (peserta.getNama() == null || peserta.getNama().trim().isEmpty()) {
            return "Nama tidak boleh kosong";
        }
        if (peserta.getEmail() == null || peserta.getEmail().trim().isEmpty()) {
            return "Email tidak boleh kosong";
        }
        if (!peserta.getEmail().contains("@")) {
            return "Email tidak valid (harus mengandung @)";
        }
        if (peserta.getNomorHP() == null || peserta.getNomorHP().trim().isEmpty()) {
            return "Nomor HP tidak boleh kosong";
        }
        if (isQuotaFull(kodeKelas, kapasitasMaksimal)) {
            return "Kuota kelas sudah penuh";
        }
        if (isDuplicateEmail(kodeKelas, peserta.getEmail())) {
            return "Email sudah terdaftar di kelas ini";
        }
        return null;
    }

    public void register(String kodeKelas, Peserta peserta) {
        if (!daftarPeserta.containsKey(kodeKelas)) {
            daftarPeserta.put(kodeKelas, new ArrayList<>());
        }
        daftarPeserta.get(kodeKelas).add(peserta);
    }

    public void cancelRegistration(String kodeKelas, String email) {
        List<Peserta> pesertaList = getPesertaByKelas(kodeKelas);
        pesertaList.removeIf(p -> p.getEmail().equalsIgnoreCase(email));
    }

    public void deleteAllByKelas(String kodeKelas) {
        daftarPeserta.remove(kodeKelas);
    }
}
