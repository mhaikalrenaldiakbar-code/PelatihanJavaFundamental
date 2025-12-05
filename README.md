Aplikasi Pendataan Mahasiswa Sederhana
Aplikasi berbasis web ini dikembangkan menggunakan **Spring Boot** dan **Thymeleaf** untuk menyediakan fungsionalitas CRUD (Create, Read, Update, Delete) data mahasiswa, dilengkapi fitur *Pagination*, *Sorting*, *Searching*, dan *Filtering*.

Fitur Utama Aplikasi
Aplikasi ini bertujuan untuk mengelola data mahasiswa dengan mudah:
  * **Data Entry (Create):** Menambahkan data mahasiswa baru (Nama, NIM, Jurusan, Email, Tanggal Lahir).
  * **Daftar Data (Read):** Menampilkan semua data dalam tabel yang mendukung **Pagination** dan **Sorting** berdasarkan kolom.
  * **Pencarian & Filter:** Mencari mahasiswa berdasarkan Nama, ID, atau memfilter berdasarkan Jurusan.
  * **Modifikasi Data (Update):** Mengubah informasi mahasiswa yang sudah terdaftar.
  * **Penghapusan (Delete):** Menghapus data mahasiswa secara permanen.
  * **Validasi:** Implementasi validasi data (misalnya, Email unik, NIM unik) pada level *Entity* dan *Service*.

Struktur Proyek (Arsitektur 3-Tingkat)
Aplikasi ini mengikuti pola desain **Model-View-Controller (MVC)** dengan arsitektur 3-Tingkat:

1.  **Controller:** Menerima permintaan HTTP, mengelola alur, dan memilih *view* (`StudentController.java`).
2.  **Service:** Mengandung logika bisnis, validasi, dan orkestrasi data (`StudentService.java`).
3.  **Repository:** Berinteraksi langsung dengan database melalui **Spring Data JPA** (`StudentRepository.java`).
4.  **Model/Entity:** Representasi data mahasiswa dan skema tabel (`Student.java`).

Persyaratan Sistem
Untuk menjalankan proyek ini, Anda membutuhkan:
1.  **Java Development Kit (JDK) 17** atau yang lebih baru.
2.  **Maven** (untuk manajemen dependensi).
3.  **MySQL Server** (versi 8.0 disarankan).
4.  **IDE** seperti IntelliJ IDEA atau VS Code.

Konfigurasi Database
Aplikasi ini terhubung ke database MySQL.
1. Buat Database
2. Konfigurasi `application.properties`

Pastikan file src/main/resources/application.properties dikonfigurasi dengan benar:
properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_student_app
spring.datasource.username=root
spring.datasource.password=your_mysql_password 

DDL-Auto disetel ke 'update' untuk membuat tabel otomatis
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true 

Cara Menjalankan Aplikasi
1.  **Clone Repository** (Asumsi proyek ada di lokal Anda).
2.  **Buka Proyek** di IDE pilihan Anda.
3.  **Install Dependensi** (Jika menggunakan Maven, biarkan IDE Anda mengimpor dependensi).
4.  Pastikan **MySQL Server** berjalan.
5.  Jalankan *main class* Spring Boot: AplikasiMahasiswaApplication.java.
6.  Akses aplikasi melalui *browser*:
    http://localhost:8080/students
