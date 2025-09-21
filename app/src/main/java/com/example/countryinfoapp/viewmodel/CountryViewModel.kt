package com.example.countryinfoapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countryinfoapp.data.Country
import com.example.countryinfoapp.repo.CountryRepository
import kotlinx.coroutines.launch

class CountryViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    val allCountries: MutableState<List<Country>> = mutableStateOf(emptyList())
    val isLoading: MutableState<Boolean> = mutableStateOf(true)

    init {
        viewModelScope.launch {
            fetchAndInsertAll()
        }
    }

    private suspend fun fetchAndInsertAll() {
      val job = viewModelScope.launch {
          countryRepository.fetchAndInsertAll()
        }
        job.join()
        getAllCountries() // Uncomment if you want to load countries immediately after fetching
        isLoading.value = false
    }

    private suspend fun getAllCountries() {
        allCountries.value = countryRepository.getAllCountries()
    }


}