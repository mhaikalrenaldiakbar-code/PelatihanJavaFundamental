Aplikasi Manajemen Produk — Spring Boot & Thymeleaf
Aplikasi ini adalah proyek sederhana berbasis Java Spring Boot, Thymeleaf, dan MySQL yang digunakan untuk melakukan operasi CRUD (Create, Read, Update, Delete) pada satu entity yaitu Product.
Proyek ini menjadi dasar sebelum masuk ke materi relasi data di JPA/Hibernate.

#Teknologi yang Digunakan
1.Java 17+
2.Spring Boot
3.Spring Web
4.Spring Data JPA
5.Thymeleaf,MySQL
6.Bootstrap (opsional untuk styling)

#Fitur Aplikasi
*Menampilkan list produk
*Menambahkan produk baru
*ngedit data produk
*Menghapus produk
*Pencarian produk berdasarkan nama
*Layering lengkap (Controller → Service → Repository)
*Implementasi OOP (Encapsulation, Entity class)

Struktur Database

Nama database: db_product_app
Tabel: products
id
,name
,price
,stock
,created_at

#Alur Kerja Program

1.Controller
-Menerima request HTTP
-Menampilkan halaman Thymeleaf
-Mengirim/menangkap data dari form

2.Service
-Memproses logika sederhana
-Menghubungkan Controller ↔ Repository

3.Repository
-Berkomunikasi langsung ke database menggunakan JpaRepository
-View (Thymeleaf)
-Menampilkan halaman:
-products.html
-add-product.html
-edit-product.

Routing Utama
Route	Method	Fungsi
/products	GET	Menampilkan daftar produk
/products/add	GET	Form tambah produk
/products/add	POST	Simpan produk
/products/edit/{id}	GET	Form edit produk
/products/edit	POST	Update produk
/products/delete/{id}	GET	Hapus produk


Mungkin sekian penjelasan saya saya ucapkan terimakasih
