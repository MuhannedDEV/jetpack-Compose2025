package com.example.countryinfoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.countryinfoapp.components.CountryCard
import com.example.countryinfoapp.data.CountryInfo
import com.example.countryinfoapp.ui.theme.CountryInfoAppTheme

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    private val usaInfo = CountryInfo(
        R.drawable.us,
        "United States",
        "Washington, D.C.",
        "United States of America",
        "Americas",
        "Northern America",
        "$",
        "United States Dollar",
        "+1",
        ".us"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MainScreen(usaInfo)
        }
    }
}


@Composable
fun MainScreen(countryInfo: CountryInfo) {
    CountryInfoAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            CountryCard(countryInfo)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // We need to create a sample CountryInfo object for the preview.
    // We can reuse the usaInfo definition or create a new one.
    // For simplicity, let's create a new one here.
    val sampleInfo = CountryInfo(
        R.drawable.us, // Assuming you have a drawable named 'us'
        "United States",
        "Washington, D.C.",
        "United States of America",
        "Americas",
        "Northern America",
        "$",
        "United States Dollar",
        "+1",
        ".us"
    )
    MainScreen(sampleInfo)
}
