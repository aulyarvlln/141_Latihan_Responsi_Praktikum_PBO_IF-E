# Latihan Responsi Praktikum PBO IF-E
- **Nama**: Aulya Revalina
- **NIM**: 123240141

## Deskripsi Tugas:

### 1. Memindahkan penyimpanan data ke MySQL
Awalnya data disimpan sementara di dalam array dan data akan hilang saat aplikasi ditutup. Jadi diubahlah agar data tersimpan di dalam database 'latres_ppbo_if_e'.

### 2. Menambah packages dan class baru
- packages 'com.pbo.latres.db' ditambahkan untuk menampung class 'DatabaseConnection.java', class ini yang akan menghubungkan java ke MySQL.
- menambahkan class 'TodoRepositoryMySQL' di packages 'com.pbo.latres.model', class ini akan menggantikan penyimpanan dummy 'FakeTodoRepository'. Digunakan untuk menyimpan, mengambil, mengubah, dan menghapus data ke MySQL.
- packages 'com.pbo.latres.controller' ditambahkan untuk menampung class 'TodoController.java', yang isinya berupa logika bisnis dan event handling. 

### 3. Mengubah 'Latres.java' (main)
Sekarang hanya 1 baris untuk menjalankan controller.

### 4. Menambah dependency ke 'pom.xml'
Menambahkan library MySQL Connector agar Java bisa terhubung dengan MySQL.

### 5. MVC
Aplikasi ini sekarnag menggunakan pola MVC (Model-View-Controller):
- Model: 'TodoTask.java' dan 'TodoRepositoryMySQL.java' (urusan data dan database)
- View: 'TodoView.java' (tampilan (tombol, tabel, form)
- Controller: 'TodoController.java' (logika dan event handling)

Dengan MVC, kode jadi lebih terorganisir karena masing-masing bagian punya tugasnya sendiri.
