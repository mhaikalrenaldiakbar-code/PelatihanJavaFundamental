#Aplikasi Manajemen Kelas dan Mahasiswa (Spring Boot CRUD)

Aplikasi web ini dibangun menggunakan Java Spring Boot untuk mendemonstrasikan implementasi dasar **CRUD (Create, Read, Update, Delete)** dengan fokus pada **relasi entitas One-to-Many** menggunakan Spring Data JPA dan database MySQL.

##Penjelasan Umum Program

Aplikasi ini mengelola dua entitas utama: **Kelas** dan **Mahasiswa**. Logika bisnis diimplementasikan menggunakan arsitektur 3-tingkat standar Spring Boot (`Controller` $\rightarrow$ `Service` $\rightarrow$ `Repository`) untuk memastikan pemisahan tanggung jawab yang baik.

### Alur Kerja (Controller → Service → Repository)

1.  **Controller (Web Layer):** Menerima permintaan HTTP dari pengguna dan memvalidasi input.
2.  **Service (Business Layer):** Menjalankan logika bisnis dan mengelola transaksi database.
3.  **Repository (Data Access Layer):** Berinteraksi dengan database (MySQL) melalui Spring Data JPA untuk operasi CRUD.

-----

## Fitur Utama Aplikasi

| Kategori | Fitur | Deskripsi |
| :--- | :--- | :--- |
| **Kelas** | CRUD Kelas | Tambah, Lihat semua, Edit, dan Hapus data kelas. |
| **Kelas** | **Total Mahasiswa** | Menampilkan jumlah mahasiswa yang terdaftar di setiap kelas (dihitung dari relasi). |
| **Mahasiswa** | CRUD Mahasiswa | Tambah, Lihat semua, Edit, dan Hapus data mahasiswa. |
| **Mahasiswa** | **Relasi Pilihan** | Form Tambah/Edit Mahasiswa dilengkapi dengan **Dropdown** yang berisi daftar kelas yang tersedia. |
| **Relasi** | **Detail Kelas** | Halaman detail kelas (`/classes/{id}`) menampilkan daftar lengkap mahasiswa yang terikat pada kelas tersebut. |

-----

## Penjelasan Relasi Entitas

Aplikasi ini berpusat pada hubungan antara `Class` dan `Student`.

| Entitas | Relasi | Deskripsi JPA & Database |
| :--- | :--- | :--- |
| **`Class`** | **One-to-Many** | Satu **Kelas** dapat memiliki banyak **Mahasiswa**. Pada `Class.java`, diimplementasikan dengan `@OneToMany List<Student> students`. |
| **`Student`** | **Many-to-One** | Banyak **Mahasiswa** terikat pada satu **Kelas**. Pada `Student.java`, diimplementasikan dengan `@ManyToOne Class aClass` dan Foreign Key `class_id` di tabel `students`. |

**Inti Relasi:** Tabel `students` memiliki kolom **`class_id`** yang menjadi kunci asing (Foreign Key) yang menunjuk ke kolom `id` di tabel `classes`.
## Struktur Folder Proyek

Struktur folder mengikuti konvensi standar proyek Spring Boot:

```
src/main/java/com/example/demo/
├── controller/
│   ├── ClassController.java    # Menangani rute /classes
│   └── StudentController.java  # Menangani rute /students
├── model/
│   ├── Class.java              # Entitas (One-to-Many)
│   └── Student.java            # Entitas (Many-to-One)
├── repository/
│   ├── ClassRepository.java    # Akses data JPA untuk Kelas
│   └── StudentRepository.java  # Akses data JPA untuk Mahasiswa
└── service/
    ├── ClassService.java       # Logika bisnis untuk Kelas
    └── StudentService.java     # Logika bisnis untuk Mahasiswa

src/main/resources/
├── static/
│   └── css/
│       └── style.css           # Styling dasar
├── templates/
│   ├── classes.html            # List semua kelas
│   ├── class-detail.html       # Detail kelas + list mahasiswa
│   ├── students.html           # List semua mahasiswa
│   ├── add-class.html          # Form tambah kelas
│   ├── edit-student.html       # Form edit mahasiswa (dengan dropdown kelas)
│   └── ... (form lainnya)
└── application.properties      # Konfigurasi database MySQL & JPA
