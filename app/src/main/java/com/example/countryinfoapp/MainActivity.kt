package com.example.countryinfoapp

import android.os.Bundle
import android.util.Log // Added Log import
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.countryinfoapp.components.CountryCardWithConstraintLayout
import com.example.countryinfoapp.data.Country
import com.example.countryinfoapp.data.CountryInfo
import com.example.countryinfoapp.ui.theme.CountryInfoAppTheme
import com.example.countryinfoapp.util.getCountryList
import com.example.countryinfoapp.util.getCountryListFromJson

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    private val countries = getCountryList()
    private val usaInfo by lazy { getCountryListFromJson(this) } // Changed to by lazy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MainScreen(countries)
        }
        printUsaInfo()
    }

    private fun printUsaInfo() {
        Log.d(TAG, "USA Info Size: ${usaInfo.size}")
        if (usaInfo.isNotEmpty()) {
            Log.d(TAG, "USA Info (First Item):")
            val firstCountry = usaInfo[0] // Assuming usaInfo contains at least one country, as per common use cases
            Log.d(TAG, "Common Name: ${firstCountry.name?.common}")
            Log.d(TAG, "Official Name: ${firstCountry.name?.official}")
            Log.d(TAG, "Capital: ${firstCountry.capital?.joinToString()}")
            Log.d(TAG, "Region: ${firstCountry.region}")
            Log.d(TAG, "Subregion: ${firstCountry.subregion}")
            // Add more fields as needed, for example:
            // firstCountry.currencies?.forEach { (code, currency) ->
            //     Log.d(TAG, "Currency $code: ${currency.name} (${currency.symbol})")
            // }
            // Log.d(TAG, "IDD: ${firstCountry.idd?.root}${firstCountry.idd?.suffixes?.joinToString(\"\")}")
            // Log.d(TAG, "TLD: ${firstCountry.tld?.joinToString()}")
        } else {
            Log.d(TAG, "USA Info is empty or not loaded yet.")
        }
    }
}


@Composable
fun MainScreen(countries: List<CountryInfo>) {
    CountryInfoAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            LazyColumn {
                items(countries) {
                    CountryCardWithConstraintLayout(it)
                }
            }
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
        "U.S.A",
        "United States of America",
        "D.C",
        "North America",
        "America",
        "$",
        "US. Dollar Dollar",
        "+1",
        ".us"
    )
    MainScreen(getCountryList())
}
