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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.countryinfoapp.components.CountryCardWithConstraintLayout
import com.example.countryinfoapp.data.Country
import com.example.countryinfoapp.data.usaSampleData // Added import for shared sample data
import com.example.countryinfoapp.ui.theme.CountryInfoAppTheme
import com.example.countryinfoapp.util.getCountryListFromJson
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    private var countries by mutableStateOf<List<Country>>(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            countries = getCountryListFromJson(applicationContext)
        }

        setContent {
            MainScreen(countries)
        }
    }

    @Suppress("unused")
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
fun MainScreen(countries: List<Country>) {
    CountryInfoAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            LazyColumn {
                items(countries) { country -> 
                    CountryCardWithConstraintLayout(country)
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Main Screen with USA Data")
@Composable
fun MainScreenPreview() {
    CountryInfoAppTheme {
        // Use the shared sample data
        MainScreen(countries = listOf(usaSampleData))
    }
}
