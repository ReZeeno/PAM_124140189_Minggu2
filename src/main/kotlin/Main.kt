import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// data class buat nyimpen berita
// id = nomor urut, judul = judul beritanya, kategori = kategori beritanya
data class Berita(
    val id: Int,
    val judul: String,
    val kategori: String
)

// dipisah biar judul sama kategorinya selalu nempel sepasang, gak diacak sendiri-sendiri
data class TemplateBerita(
    val judul: String,
    val kategori: String
)

class NewsFeedManager {

    // ini buat nyimpen jumlah berita yang udah dibaca
    private val _jumlahDibaca = MutableStateFlow(0)
    val jumlahDibaca: StateFlow<Int> = _jumlahDibaca.asStateFlow()

    // semua beritanya tentang game, kategorinya dibagi berdasarkan jenis beritanya
    private val templateBerita = listOf(
        TemplateBerita("Game RPG Baru Dirilis Minggu Ini", "Rilis Game"),
        TemplateBerita("DLC Terbaru Ditambahkan ke Game Populer", "Rilis Game"),
        TemplateBerita("Sekuel Game Battle Royale Segera Meluncur", "Rilis Game"),
        TemplateBerita("Update Patch 2.5 Perbaiki Banyak Bug", "Update Game"),
        TemplateBerita("Developer Rilis Patch Balance Karakter", "Update Game"),
        TemplateBerita("Fitur Multiplayer Baru Ditambahkan", "Update Game"),
        TemplateBerita("Tim Esport Nasional Juarai Turnamen Internasional", "Esports"),
        TemplateBerita("Kompetisi Esports Regional Segera Digelar", "Esports"),
        TemplateBerita("Pemain Pro Pindah ke Tim Baru Musim Ini", "Esports"),
        TemplateBerita("Game Ini Lagi Diskon Gede-gedean di Steam", "Diskon"),
        TemplateBerita("Promo Akhir Tahun di Berbagai Platform Game", "Diskon")
    )

    // flow buat simulasi berita masuk tiap 2 detik
    fun buatNewsFlow(): Flow<Berita> = flow {
        var idBerita = 1
        // sengaja looping terus, nanti di collect-nya kita batesin pake take()
        while (true) {
            delay(2000)
            val template = templateBerita.random() // random SATU pasangan, bukan dua list terpisah
            emit(Berita(idBerita, template.judul, template.kategori))
            idBerita++
        }
    }

    // suspend function buat "ambil detail" berita, disimulasikan pake delay
    suspend fun ambilDetailBerita(idBerita: Int): String {
        delay(500) // pura-pura network call
        return "Detail berita nomor $idBerita berhasil diambil dari server"
    }

    fun tambahDibaca() {
        _jumlahDibaca.value = _jumlahDibaca.value + 1
    }
}

fun main() = runBlocking {
    val manager = NewsFeedManager()
    val kategoriYangDicari = "Esports" // ganti-ganti ini buat coba kategori lain: "Rilis Game", "Update Game", "Diskon"

    println("=== Mulai Simulasi News Feed ===")
    println("Kategori yang dicari: $kategoriYangDicari")
    println()

    // collect jumlah dibaca di coroutine terpisah biar bisa jalan bareng
    launch {
        manager.jumlahDibaca.collect { jumlah ->
            println("Jumlah berita yang udah dibaca sekarang: $jumlah")
        }
    }

    manager.buatNewsFlow()
        .filter { berita -> berita.kategori == kategoriYangDicari } // cuma ambil yang kategorinya cocok
        .take(5) // biar programnya berhenti sendiri, ambil 5 aja
        .collect { berita ->
            // ubah jadi string yang lebih enak dibaca
            val hasilFormat = "[" + berita.kategori + "] " + berita.judul

            println("Berita baru masuk -> $hasilFormat")

            // ambil detailnya pake suspend function
            val detail = manager.ambilDetailBerita(berita.id)
            println(detail)

            manager.tambahDibaca()
            println()
        }

    println("==> Simulasi Selesai <==")
}