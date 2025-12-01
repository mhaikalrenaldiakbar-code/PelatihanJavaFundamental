import java.util.Scanner;


public class MainApotek {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Apotek apotek = new Apotek();
        int pilih;

        do {
            System.out.println("===== MENU APOTEK =====");
            System.out.println("1. Tambah Obat");
            System.out.println("2. Lihat Daftar Obat");
            System.out.println("3. Cari Obat");
            System.out.println("4. Ubah Data Obat");
            System.out.println("5. Hapus Obat");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {
                case 1:
                    System.out.print("Kode Obat      : ");
                    String kode = input.nextLine();
                    System.out.print("Nama Obat      : ");
                    String nama = input.nextLine();
                    System.out.print("Kategori       : ");
                    String kategori = input.nextLine();
                    System.out.print("Deskripsi      : ");
                    String deskripsi = input.nextLine();
                    System.out.print("Stok           : ");
                    int stok = input.nextInt();
                    System.out.print("Harga          : ");
                    double harga = input.nextDouble();
                    input.nextLine();
                    System.out.print("Nama Supplier  : ");
                    String supplier = input.nextLine();
                    System.out.print("No. Supplier   : ");
                    String noSup = input.nextLine();

                    apotek.tambahObat(new Obat(kode, nama, kategori, deskripsi, stok, harga, supplier, noSup));
                    break;

                case 2:
                    apotek.tampilSemuaObat();
                    break;

                case 3:
                    System.out.print("Masukkan kode obat yang dicari: ");
                    String kodeCari = input.nextLine();
                    Obat hasil = apotek.cariObat(kodeCari);
                    if (hasil != null) hasil.tampilInfo();
                    else System.out.println("Obat tidak ditemukan.");
                    break;

                case 4:
                    System.out.print("Masukkan kode obat yang ingin diubah: ");
                    String kodeUbah = input.nextLine();
                    System.out.print("Nama baru      : ");
                    String nb = input.nextLine();
                    System.out.print("Kategori baru  : ");
                    String kb = input.nextLine();
                    System.out.print("Deskripsi baru : ");
                    String db = input.nextLine();
                    System.out.print("Stok baru      : ");
                    int sb = input.nextInt();
                    System.out.print("Harga baru     : ");
                    double hb = input.nextDouble();
                    input.nextLine();
                    System.out.print("Nama Supplier baru : ");
                    String supBaru = input.nextLine();
                    System.out.print("Nomor Supplier baru: ");
                    String noSupBaru = input.nextLine();

                    apotek.ubahObat(kodeUbah, nb, kb, db, sb, hb, supBaru, noSupBaru);
                    break;

                case 5:
                    System.out.print("Masukkan kode obat yang ingin dihapus: ");
                    String kodeHapus = input.nextLine();
                    apotek.hapusObat(kodeHapus);
                    break;
            }

        } while (pilih != 6);

        System.out.println("Terima kasih telah menggunakan sistem apotek!");
        input.close();
    }
}
