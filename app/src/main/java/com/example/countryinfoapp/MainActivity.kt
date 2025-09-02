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
// Text import might be unused if MainScreen is simple, but often needed.
// import androidx.compose.material3.Text 
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
// Preview import might be unused in the final MainActivity, but good to keep if you add previews.
// import androidx.compose.ui.tooling.preview.Preview 
import androidx.lifecycle.lifecycleScope
import com.example.countryinfoapp.components.CountryCardWithConstraintLayout
import com.example.countryinfoapp.data.Country // Assuming Country is the type from JSON
// CountryInfo import might be unused if not directly used in MainActivity.
// import com.example.countryinfoapp.data.CountryInfo 
import com.example.countryinfoapp.ui.theme.CountryInfoAppTheme
// getCountryList import might be unused if only getCountryListFromJson is used.
// import com.example.countryinfoapp.util.getCountryList 
import com.example.countryinfoapp.util.getCountryListFromJson
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    // Use State for Compose to observe changes and trigger recomposition
    private var countries by mutableStateOf<List<Country>>(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Launch a coroutine in the lifecycle scope to load data asynchronously
        lifecycleScope.launch {
            countries = getCountryListFromJson(applicationContext)
            // If you need to log after loading, you can do it here or pass countries to a function
            // printCountryInfoAfterLoading() 
        }

//        enableEdgeToEdge() // If you have this function for edge-to-edge display
        setContent {
            // MainScreen will recompose when 'countries' state changes
            MainScreen(countries)
        }
    }

    // Optional: If you want to log details after loading
    @Suppress("unused") // Suppress if not immediately used to avoid warnings
    private fun printCountryInfoAfterLoading() {
        Log.d(TAG, "Countries List Size: ${countries.size}")
        if (countries.isNotEmpty()) {
            countries.forEachIndexed { index, country ->
                Log.d(TAG, "Country #${index + 1}:")
                Log.d(TAG, "  Common Name: ${country.name?.common}")
                Log.d(TAG, "  Official Name: ${country.name?.official}")
                Log.d(TAG, "  Capital: ${country.capital?.joinToString()}")
                Log.d(TAG, "  Region: ${country.region}")
                Log.d(TAG, "  Subregion: ${country.subregion}")
                Log.d(TAG, "  Flag PNG URL: ${country.flags?.png}")
            }
        } else {
            Log.d(TAG, "Countries list is empty.")
        }
    }
}


@Composable
fun MainScreen(countries: List<Country>) { // Receives the state-managed list
    CountryInfoAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            if (countries.isEmpty()) {
                // Optional: Show a loading indicator or empty state message
                // For now, it will just be an empty LazyColumn
            }
            LazyColumn {
                items(countries) { country -> 
                    CountryCardWithConstraintLayout(country)
                }
            }
        }
    }
}
