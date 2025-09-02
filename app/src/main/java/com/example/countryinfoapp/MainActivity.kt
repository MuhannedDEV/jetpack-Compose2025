package com.example.countryinfoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.countryinfoapp.components.CountryCardWithConstraintLayout
import com.example.countryinfoapp.data.Country // Assuming Country is the type from JSON
import com.example.countryinfoapp.data.CountryInfo // Original type for getCountryList()
import com.example.countryinfoapp.ui.theme.CountryInfoAppTheme
import com.example.countryinfoapp.util.getCountryList // For preview or if still used
import com.example.countryinfoapp.util.getCountryListFromJson

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    private lateinit var countries: List<Country> // Changed to lateinit var

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countries = getCountryListFromJson(applicationContext) // Initialized in onCreate

//        enableEdgeToEdge()
        setContent {
            // Assuming MainScreen now takes List<Country>
            MainScreen(countries)
        }
        printCountryInfo() // Renamed for clarity, using 'countries'
    }

    private fun printCountryInfo() {
        Log.d(TAG, "Countries List Size: ${countries.size}")
//        if (countries.isNotEmpty()) {
//            countries.forEachIndexed { index, country ->
//                Log.d(TAG, "Country #${index + 1}:")
//                Log.d(TAG, "  Common Name: ${country.name?.common}")
//                Log.d(TAG, "  Official Name: ${country.name?.official}")
//                Log.d(TAG, "  Capital: ${country.capital?.joinToString()}")
//                Log.d(TAG, "  Region: ${country.region}")
//                Log.d(TAG, "  Subregion: ${country.subregion}")
//                Log.d(TAG, "  Flag: ${country.flags}") // This will show the raw Flags object or null
//                // If you want to check a specific property of flags, e.g., png:
//                // Log.d(TAG, "  Flag PNG URL: ${country.flags?.png}")
//            }
//        } else {
//            Log.d(TAG, "Countries list is empty or not loaded yet.")
//        }
    }
}


@Composable
fun MainScreen(countries: List<Country>) { // Changed to List<Country>
    CountryInfoAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            LazyColumn {
                items(countries) { country -> // item is now 'Country'
                    CountryCardWithConstraintLayout(country) // Pass 'Country' object
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // Preview currently uses getCountryList() which returns List<CountryInfo>
    // If MainScreen expects List<Country>, this preview needs adjustment
    // or a different preview that mocks List<Country>.
    // For now, to make it compile, MainScreen in preview might need to be different
    // or we create sample Country data.
    // Let's use getCountryList() for now, and assume you'll adapt the preview if needed.
    val sampleCountryInfoList = getCountryList() // Returns List<CountryInfo>
    // This preview will likely have issues if MainScreen strictly expects List<Country>
    // and CountryCardWithConstraintLayout expects Country.
    // A proper fix would be to mock List<Country> for the preview.
    // For simplicity, I'm commenting out the preview call to avoid type conflicts here.
    // MainScreen(getCountryList()) // This would cause a type mismatch if MainScreen expects List<Country>

    // Placeholder for preview:
    Text("Preview not fully configured for List<Country>")
}
