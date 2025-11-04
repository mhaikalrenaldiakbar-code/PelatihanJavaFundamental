public class Obat {
    private String kodeObat;
    private String namaObat;
    private String kategori;
    private String deskripsi;
    private int stok;
    private double harga;
    private String namaSupplier;
    private String noSupplier;

    public Obat(String kodeObat, String namaObat, String kategori, String deskripsi, int stok, double harga,
            String namaSupplier, String noSupplier) {
        this.kodeObat = kodeObat;
        this.namaObat = namaObat;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.stok = stok;
        this.harga = harga;
        this.namaSupplier = namaSupplier;
        this.noSupplier = noSupplier;
    }

    public String getKodeObat() {
        return kodeObat;
    }

    public void setKodeObat(String kodeObat) {
        this.kodeObat = kodeObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public String getNoSupplier() {
        return noSupplier;
    }

    public void setNoSupplier(String noSupplier) {
        this.noSupplier = noSupplier;
    }

    public void tampilInfo() {
        System.out.println("Kode Obat      : " + kodeObat);
        System.out.println("Nama Obat      : " + namaObat);
        System.out.println("Kategori       : " + kategori);
        System.out.println("Deskripsi      : " + deskripsi);
        System.out.println("Stok           : " + stok);
        System.out.println("Harga          : Rp" + harga);
        System.out.println("Nama Supplier  : " + namaSupplier);
        System.out.println("No. Supplier   : " + noSupplier);
        System.out.println("----------------------------------------");
    }
}
