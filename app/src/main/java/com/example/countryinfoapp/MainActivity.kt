package com.example.countryinfoapp

import android.os.Bundle
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
import com.example.countryinfoapp.components.CountryCard
import com.example.countryinfoapp.components.CountryCardWithConstraintLayout
import com.example.countryinfoapp.data.CountryInfo
import com.example.countryinfoapp.ui.theme.CountryInfoAppTheme
import com.example.countryinfoapp.util.getCountryList

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    private val countries= getCountryList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
           MainScreen(countries)
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
