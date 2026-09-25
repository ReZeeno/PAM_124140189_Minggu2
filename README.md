# News Feed Simulator

Program simulasi news feed bertema game menggunakan Kotlin

## Fitur

* Flow yang mensimulasikan berita game baru setiap 2 detik
* Filter berita berdasarkan kategori
* Kategori berita seperti Rilis Game, Update Game, Esports, dan Diskon
* Format data berita untuk ditampilkan ke console
* StateFlow untuk melacak jumlah berita yang sudah dibaca
* Suspend function untuk mengambil detail berita secara async
* Setiap berita memiliki ID yang digunakan saat mengambil detail berita
* Program otomatis berhenti setelah 5 berita yang sesuai kategori diproses

## Kategori Berita

Program menyediakan beberapa kategori berita game:

* **Rilis Game**

    * Game RPG Baru Dirilis Minggu Ini
    * DLC Terbaru Ditambahkan ke Game Populer
    * Sekuel Game Battle Royale Segera Meluncur

* **Update Game**

    * Update Patch 2.5 Perbaiki Banyak Bug
    * Developer Rilis Patch Balance Karakter
    * Fitur Multiplayer Baru Ditambahkan

* **Esports**

    * Tim Esport Nasional Juarai Turnamen Internasional
    * Kompetisi Esports Regional Segera Digelar
    * Pemain Pro Pindah ke Tim Baru Musim Ini

* **Diskon**

    * Game Ini Lagi Diskon Gede-gedean di Steam
    * Promo Akhir Tahun di Berbagai Platform Game

## Cara Menjalankan

1. Clone repository ini
2. Buka dengan Android Studio / IntelliJ IDEA
3. Tunggu Gradle sync selesai
4. Jalankan `Main.kt` (klik tombol Run di sebelah `fun main()`)

## Struktur

* `src/main/kotlin/Main.kt` seluruh logic Flow, StateFlow, dan Coroutines

## Alur Program

1. `buatNewsFlow()` membuat berita game baru setiap 2 detik.
2. Setiap berita memiliki ID, judul, dan kategori.
3. Template berita dipilih secara random dari daftar yang tersedia.
4. Berita difilter berdasarkan kategori yang dipilih pada `kategoriYangDicari`.
5. Maksimal 5 berita yang sesuai kategori akan diproses.
6. Data berita diformat dan ditampilkan ke console.
7. `ambilDetailBerita()` mengambil detail berdasarkan ID berita yang sedang diproses.
8. `StateFlow` memperbarui jumlah berita yang sudah dibaca.
9. Program selesai setelah 5 berita berhasil diproses.

Kategori yang tersedia:

```text
Rilis Game
Update Game
Esports
Diskon
```
