package com.example.yosephulu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.yosephulu.ui.theme.YosepHuluTheme
import com.example.yosephulu.ui.theme.Artwork

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { // Metode ini dipanggil saat aktivitas dibuat. Di sini, konten UI diatur dengan tema yang ditentukan (YosepHuluTheme) dan menampilkan fungsi YosepHuluApp().
        super.onCreate(savedInstanceState)
        setContent {
            YosepHuluTheme {
                YosepHuluApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YosepHuluApp() {
    var currentArtworkIndex by remember { mutableIntStateOf (0) } //Menggunakan remember untuk menyimpan indeks dari artwork yang ditampilkan saat ini. mutableStateOf memungkinkan nilai ini untuk diperbarui dan membuat UI bereaksi terhadap perubahan.
    val artworks = listOf( // Mendeklarasikan daftar objek Artwork, masing-masing dengan ID gambar, judul, nama artis, dan tahun.
        Artwork(R.drawable.yosep1, "AGAK LAEN", "yosep ", "2024"),
        Artwork(R.drawable.yosep2, "ANCIKA",  " yosep", "2024"),
        Artwork(R.drawable.yosep3, "SEHIDUP SEMATI",  "yosep ", "2024"),
        Artwork(R.drawable.yosep4, "PASUTRI GAJE",  "yosep ", "2024"),
        Artwork(R.drawable.yosep5, "STENGAH HAT",  " yosep", "2024")
        // Add more artworks here
    )

    Scaffold( // Scaffold menyediakan kerangka dasar untuk UI, termasuk topBar untuk judul aplikasi. innerPadding memastikan konten tidak tertutupi oleh elemen UI lainnya.
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("LAYAR KACA 21")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtworkDisplay(artwork = artworks[currentArtworkIndex])  //Fungsi ini menampilkan detail dari satu artwork. Di dalamnya

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { //Dua tombol untuk mengubah currentArtworkIndex. Menggunakan operasi modulus untuk memastikan indeks tetap dalam batas daftar artworks.
                    currentArtworkIndex = (currentArtworkIndex - 1 + artworks.size) % artworks.size
                }) {
                    Text("Previous")
                }

                Button(onClick = {
                    currentArtworkIndex = (currentArtworkIndex + 1) % artworks.size
                }) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun ArtworkDisplay(artwork: Artwork) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = artwork.imageResourceId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = artwork.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = "by ${artwork.artist}",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Text(
            text = artwork.year,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}
