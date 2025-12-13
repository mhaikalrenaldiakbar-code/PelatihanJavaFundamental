Dokumentasi Proyek Aplikasi Restoran
Judul Proyek
Sistem Manajemen Restoran

Deskripsi Aplikasi
Aplikasi SIMARES adalah sistem manajemen berbasis web yang dibangun menggunakan Spring Boot dan Thymeleaf. 
Aplikasi ini bertujuan untuk memfasilitasi pengelolaan entitas utama dalam bisnis restoran, yaitu daftar Makanan (Food),
Minuman (Drink), dan informasi Chef yang bertugas.Aplikasi ini menyediakan antarmuka pengguna (UI) yang mudah digunakan 
untuk melakukan operasi dasar database (CRUD), serta fitur pencarian dan pengurutan (Sorting & Searching) untuk meningkatkan 
efisiensi operasional.

Alasan Memilih Tema
Aspek,Penjelasan
Penerapan Konsep MVC,"Tema restoran sangat ideal untuk mempraktikkan arsitektur Model-View-Controller (MVC) yang diterapkan oleh Spring Boot, memisahkan data (Model), tampilan (View: Thymeleaf), dan logika (Controller)."
Relasi Database Nyata,"Tema ini memungkinkan implementasi relasi Many-to-One (atau One-to-Many) yang realistis, misalnya antara Chef dengan Spesialisasi Makanan/Minuman."
Fitur Bisnis Dasar,Menyediakan platform untuk mengimplementasikan fitur-fitur penting seperti Manajemen Inventaris Sederhana (Food/Drink List) dan Manajemen SDM (Chef).
Latihan CRUD Lanjutan,"Selain CRUD dasar, tema ini memaksa penerapan fitur Search dan Sort untuk mengelola daftar yang mungkin besar, seperti daftar harga atau daftar Chef berdasarkan asal."


Entitas,Relasi dengan Entitas Lain,Tipe Relasi,Keterangan
Chef,Food,Many-to-One,"Satu Chef memiliki spesialisasi pada satu jenis Makanan. Namun, satu Makanan bisa menjadi spesialisasi banyak Chef."
Chef,Drink,Many-to-One,"Satu Chef memiliki spesialisasi pada satu jenis Minuman. Namun, satu Minuman bisa menjadi spesialisasi banyak Chef."
Food,Chef,One-to-Many,(Kebalikan dari relasi di atas). Satu Makanan (sebagai spesialisasi) dapat dimiliki oleh banyak Chef.
Drink,Chef,One-to-Many,(Kebalikan dari relasi di atas). Satu Minuman (sebagai spesialisasi) dapat dimiliki oleh banyak Chef.


Penjelasan Logika yang Diterapkan
Aplikasi ini menerapkan beberapa logika pemrograman dan fungsionalitas backend:

A. Logika CRUD Lengkap
Setiap entitas (Food, Drink, Chef) dilengkapi dengan:
Create/Save: Menyimpan data baru melalui POST request (/save-food).
Read/List: Menampilkan semua data dalam tabel (/list-food).
Update/Edit: Memodifikasi data yang sudah ada, menggunakan GET untuk memuat formulir dan 
POST untuk menyimpan perubahan (/update-food/{id}).
Delete: Menghapus data menggunakan GET request (/delete-food/{id}).

B. Logika Pencarian (Search)
Teknik: Menggunakan keyword yang dikirim melalui parameter @RequestParam (?name=keyword).
Implementasi Service/Repository: Didefinisikan langsung di Repository (misalnya, DrinkRepository.findByNameContainingIgnoreCase(String name)). 
Logika ini memungkinkan pencarian fleksibel (parsial) dan tidak case-sensitive.

C. Logika Pengurutan (Sort)
Teknik: Menggunakan URL mapping spesifik (misalnya, /sort-drink-by-price-asc).
Implementasi Service/Repository: Didefinisikan di Repository (misalnya, DrinkRepository.findAllByOrderByPriceAsc()) yang 
memanfaatkan fitur query derivation dari Spring Data JPA untuk menghasilkan hasil yang sudah diurutkan (ASC/DESC).

D. Penanganan Relasi dalam Formulir
Pada Chef: Saat menambah atau mengedit data Chef, formulir menggunakan elemen <select> yang diisi oleh objek foodList dan drinkList.
Hal ini memungkinkan pengguna memilih entitas Food dan Drink yang sudah ada, memastikan integritas data dalam relasi Many-to-One.
