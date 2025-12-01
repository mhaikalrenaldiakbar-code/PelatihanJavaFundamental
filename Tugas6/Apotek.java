import java.util.ArrayList;

public class Apotek {
    private ArrayList<Obat> daftarObat = new ArrayList<>();

    public void tambahObat(Obat obat) {
        daftarObat.add(obat);
        System.out.println("Obat berhasil ditambahkan!");
    }

    public void tampilSemuaObat() {
        System.out.println("===== DAFTAR OBAT =====");
        if (daftarObat.isEmpty()) {
            System.out.println("Tidak ada data obat.");
            return;
        }
        for (Obat o : daftarObat) {
            o.tampilInfo();
        }
    }

    public Obat cariObat(String kodeObat) {
        for (Obat o : daftarObat) {
            if (o.getKodeObat().equalsIgnoreCase(kodeObat)) {
                return o;
            }
        }
        return null;
    }

    public void ubahObat(String kodeObat, String nama, String kategori, String deskripsi, int stok, double harga, String supplier, String noSup) {
        Obat o = cariObat(kodeObat);
        if (o != null) {
            o.setNamaObat(nama);
            o.setKategori(kategori);
            o.setDeskripsi(deskripsi);
            o.setStok(stok);
            o.setHarga(harga);
            o.setNamaSupplier(supplier);
            o.setNoSupplier(noSup);
            System.out.println("Data obat berhasil diperbarui!");
        } else {
            System.out.println("Obat tidak ditemukan.");
        }
    }

    public void hapusObat(String kodeObat) {
        Obat o = cariObat(kodeObat);
        if (o != null) {
            daftarObat.remove(o);
            System.out.println("Obat berhasil dihapus!");
        } else {
            System.out.println("Obat tidak ditemukan.");
        }
    }
}
