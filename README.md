# Smart Traveling Planner

## Informasi Proyek

* **Mata Kuliah**: Algoritma dan Struktur Data
* **Dosen Pengampu**: Renny Pradina Kusumawardani
* **Kelas**: D
* **Kelompok**: 12
* **Anggota Kelompok**:

| No | Nama Lengkap                  | NRP        |
| -- | ----------------------------- | ---------- |
| 1  | Benedictus Nicholas Christian | 5026241187 |
| 2  | Dziky Fajryan Noersyafitrah   | 5026241151 |
| 3  | Fabian Iqbal Firjatullah      | 5026241087 |

---

## Deskripsi Singkat

**Smart Traveling Planner** adalah aplikasi berbasis **Java (console)** yang membantu pengguna merencanakan perjalanan wisata di suatu kota. Aplikasi ini memungkinkan pengguna untuk:

* Memilih kota tujuan
* Menentukan posisi awal
* Melihat daftar wisata terdekat atau termurah
* Menentukan rute perjalanan optimal ke beberapa destinasi

Program ini dikembangkan sebagai **Proyek Akhir Kuliah Algoritma dan Struktur Data** dengan menerapkan beberapa algoritma dasar seperti *sorting* dan *shortest path*.

---

## Latar Belakang

Wisatawan sering mengalami kesulitan dalam menentukan urutan kunjungan tempat wisata yang efisien, baik dari segi jarak maupun biaya. Oleh karena itu, aplikasi ini dibuat untuk mensimulasikan perencanaan perjalanan menggunakan pendekatan algoritmik sehingga pengguna dapat memahami penerapan struktur data dan algoritma dalam permasalahan nyata.

---

## Fitur Utama

1. **Pemilihan Kota**
   Pengguna dapat memilih salah satu kota yang tersedia (Surabaya, Jakarta, Bandung).

2. **Penentuan Posisi Awal**
   Pengguna menentukan lokasi awal berdasarkan daftar tempat yang tersedia.

3. **Cek Wisata Terdekat**
   Menampilkan daftar tempat wisata yang diurutkan berdasarkan jarak terdekat dari posisi awal.

4. **Cek Wisata Termurah**
   Menampilkan daftar tempat wisata yang diurutkan berdasarkan harga tiket termurah.

5. **Perencanaan Rute Perjalanan**
   Menentukan rute perjalanan terbaik ke beberapa destinasi menggunakan algoritma Dijkstra.

---

## Algoritma dan Struktur Data yang Digunakan

### 1. Quick Sort

* **Digunakan untuk**: Mengurutkan tempat wisata berdasarkan jarak terdekat
* **Lokasi Implementasi**: `Sorter.java` → `quickSortByDistance()`

### 2. Merge Sort

* **Digunakan untuk**: Mengurutkan tempat wisata berdasarkan harga termurah
* **Lokasi Implementasi**: `Sorter.java` → `mergeSortByPrice()`

### 3. Graph (Adjacency Matrix)

* **Digunakan untuk**: Merepresentasikan jarak antar tempat
* **Lokasi Implementasi**: `City.java`

### 4. Dijkstra Algorithm

* **Digunakan untuk**: Menentukan rute terpendek dari posisi awal ke beberapa destinasi
* **Lokasi Implementasi**: `Graph.java` → `dijkstra()`

---

## Struktur Folder Repositori

```
📁 Smart-Traveling-Planner
├── 📁 Kode
│   ├── Main.java
│   ├── City.java
│   ├── Place.java
│   ├── Sorter.java
│   └── Graph.java
├── 📁 Presentasi
│   └── Smart_Traveling_Planner.pdf
└── README.md
```

---

## Cara Menjalankan Program

1. Pastikan Java sudah terinstal (minimal Java 8)
2. Masuk ke folder `Kode`
3. Compile seluruh file:

   ```bash
   javac *.java
   ```
4. Jalankan program:

   ```bash
   java Main
   ```

---

## Contoh Alur Penggunaan

1. Pilih kota
2. Tentukan posisi awal
3. Pilih menu:

   * Cek wisata terdekat
   * Cek wisata termurah
   * Buat rute perjalanan
4. Jika memilih rute, masukkan beberapa ID destinasi
5. Program menampilkan rute optimal dan total jarak tempuh

---

## Screenshot Program
<img width="1366" height="768" alt="Screenshot 2025-12-14 133352" src="https://github.com/user-attachments/assets/bebb3c6d-99a9-4a32-bb17-38af10ccf540" />
<img width="1366" height="768" alt="Screenshot 2025-12-14 133408" src="https://github.com/user-attachments/assets/2f6a6b11-799e-4af4-8ec9-fb9e806b3a40" />
<img width="1366" height="768" alt="Screenshot 2025-12-14 133418" src="https://github.com/user-attachments/assets/2cb88437-4b90-40b5-b712-0dd768564bc1" />
<img width="1366" height="768" alt="Screenshot 2025-12-14 133434" src="https://github.com/user-attachments/assets/f96c9edf-6f8d-4c67-9b3d-fcb6ba2f5526" />
<img width="1366" height="768" alt="Screenshot 2025-12-14 133447" src="https://github.com/user-attachments/assets/83f0ecf3-28e6-4926-99fc-24e2578ce849" />



---

## Update Program

| Tanggal          | Deskripsi Update                                                                                                                                                                                                                                            | File Terkait          | 
| ---------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------- | 
| 14 Desember 2025 | Perapihan output program: penambahan spasi antar kategori, perataan kolom output agar sejajar, serta penghapusan tampilan ID tempat pada output pengguna. Perubahan dilakukan untuk meningkatkan keterbacaan dan kerapihan tampilan sesuai feedback revisi. | Main.java, Place.java | 

---
## Daftar Proyek Akhir Kelompok Lain 
Berikut adalah beberapa proyek akhir dari kelompok lain beserta judul dan tautan repositorinya:

| Kelompok | Judul Proyek                                                    | Repository                                                                                                                         |
| -------- | --------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| D1       | Word Rank Guesser Game                                          | [https://github.com/NashiwaInsan/asdfinalproject](https://github.com/NashiwaInsan/asdfinalproject)                                 |
| D2       | Sistem Rekomendasi Event Berbasis Graph                         | [https://github.com/dedyirama-id/kael-recommendation-system](https://github.com/dedyirama-id/kael-recommendation-system)           |
| D3       | Smart Traffic Light Simulator                                   | [https://github.com/Sudukk/FP_ASD_Traffic_Light_Simulation_FINAL](https://github.com/Sudukk/FP_ASD_Traffic_Light_Simulation_FINAL) |
| D4       | HotelSeek – Rekomendasi Pemilihan Hotel                         | [https://github.com/dreadf/hotelseek](https://github.com/dreadf/hotelseek)                                                         |
| D5       | *(Belum tersedia)*                                              | [https://github.com/](https://github.com/)                                                                                         |
| D6       | To-Do List                                                      | [https://github.com/anggraitapr/ASDFPTODOLIST](https://github.com/anggraitapr/ASDFPTODOLIST)                                       |
| D7       | Sistem Antrian IGD                                              | [https://github.com/WilliamHanantha/FP-ASD](https://github.com/WilliamHanantha/FP-ASD)                                                                                         |
| D8       | Sistem Rekomendasi Jadwal Latihan dan Nutrisi Gym               | [https://github.com/tyr3x74/GymPlan](https://github.com/tyr3x74/GymPlan)                                                                                         |
| D9       | Sistem Rekomendasi Teman Berdasarkan Mutual Friends             | [https://github.com/mariaelvina/FinalProjectD9](https://github.com/mariaelvina/FinalProjectD9)                                     |
| D10      | Monster Chase                                                   | [https://github.com/Aida41104/FPASD](https://github.com/Aida41104/FPASD)                                                           |
| D11      | Warehouse Management System                                     | [https://github.com/FasaBil/ASD-D11-Inventory-Management](https://github.com/FasaBil/ASD-D11-Inventory-Management)                 |
| D13      | Sistem Manajemen Inventaris Gudang dan Optimasi Rute Pengiriman | [https://github.com/FashaAsshofa/Final-Project-ASD-D-Kelompok-13](https://github.com/FashaAsshofa/Final-Project-ASD-D-Kelompok-13) |
| D14      | Rekomendasi Film berbasis Graph                                 | [https://github.com/neutralcheeze/final-project-asd.git](https://github.com/neutralcheeze/final-project-asd.git)                   |

---

## Referensi

* Materi Algoritma dan Struktur Data
* Modul Perkuliahan

---

## Catatan

Proyek ini dibuat untuk keperluan akademik dan bertujuan menunjukkan pemahaman penerapan algoritma dan struktur data dalam studi kasus sederhana perencanaan perjalanan.
