package com.example.countryinfoapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Added this import
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countryinfoapp.components.CountryCard
import com.example.countryinfoapp.database.AppDataBase
import com.example.countryinfoapp.repo.CountryRepository
import com.example.countryinfoapp.ui.theme.CountryInfoAppTheme
import com.example.countryinfoapp.viewmodel.CountryViewModel
import com.example.countryinfoapp.viewmodel.CountryViewModelFactory

@Composable
fun MainScreen(innerPadding: PaddingValues) {

    val context = LocalContext.current
    //Initialize Dao
    val countryDao = AppDataBase.getDatabase(context.applicationContext).countryDao()
    //Initialize the Repository
    val countryRepository = countryDao?.let {
        CountryRepository(context, it)
    }
    //Initialize the ViewModel
     val viewModel: CountryViewModel = viewModel(factory = countryRepository?.let { CountryViewModelFactory(it) })

    val countrylist = viewModel.allCountries.value
    val isLoading = viewModel.isLoading.value

    CountryInfoAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.surface
        ) {
            when {
                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                else -> {
                    LazyColumn {
                        items(countrylist) { country ->
                            CountryCard(countryInfo = country)
                        }
                    }
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
//        MainScreen(countries = listOf(usaSampleData), innerPadding = PaddingValues())
    }
}