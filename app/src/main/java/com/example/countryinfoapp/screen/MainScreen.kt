package com.example.countryinfoapp.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.countryinfoapp.components.CountryCardWithConstraintLayout
import com.example.countryinfoapp.data.Country
import com.example.countryinfoapp.data.usaSampleData
import com.example.countryinfoapp.ui.theme.CountryInfoAppTheme

@Composable
fun MainScreen(countries: List<Country>, innerPadding: PaddingValues) {
    CountryInfoAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
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
        MainScreen(countries = listOf(usaSampleData), innerPadding = PaddingValues())
    }
}