package com.example.countryinfoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.example.countryinfoapp.components.CountryInfoAppScaffold
import com.example.countryinfoapp.data.Country
import com.example.countryinfoapp.ui.theme.CountryInfoAppTheme
import com.example.countryinfoapp.util.getCountryListFromJson
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
//    private var countries by mutableStateOf<List<Country>>(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        lifecycleScope.launch {
//            countries = getCountryListFromJson(applicationContext)
//        }

        setContent {
            CountryInfoAppTheme(dynamicColor = false) {
                CountryInfoAppScaffold()
            }
        }
    }

//    @Suppress("unused")
//    private fun printCountryInfoAfterLoading() {
//        Log.d(TAG, "Countries List Size: ${countries.size}")
//        if (countries.isNotEmpty()) {
//            countries.forEachIndexed { index, country ->
//                Log.d(TAG, "Country #${index + 1}:")
//                Log.d(TAG, "  Common Name: ${country.name?.common}")
//                Log.d(TAG, "  Official Name: ${country.name?.official}")
//                Log.d(TAG, "  Capital: ${country.capital?.joinToString()}")
//                Log.d(TAG, "  Region: ${country.region}")
//                Log.d(TAG, "  Subregion: ${country.subregion}")
//                Log.d(TAG, "  Flag PNG URL: ${country.flags?.png}")
//            }
//        } else {
//            Log.d(TAG, "Countries list is empty.")
//        }
//    }
}



