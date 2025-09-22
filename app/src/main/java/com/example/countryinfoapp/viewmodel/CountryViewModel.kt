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

    val selectedCountryForDeletion: MutableState<Country?> = mutableStateOf(null)
    val showDeleteConfirmationDialog: MutableState<Boolean> = mutableStateOf(false) // Added

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
        getAllCountries()
        isLoading.value = false
    }

    private suspend fun getAllCountries() {
        allCountries.value = countryRepository.getAllCountries()
    }

    // Call this when the user indicates they want to delete a specific country
    fun initiateDeleteProcess(country: Country) {
        selectedCountryForDeletion.value = country
        showDeleteConfirmationDialog.value = true
    }

    // Call this when the user confirms the deletion in the dialog
    suspend fun deleteCountry() {
        selectedCountryForDeletion.value?.let { country ->
           countryRepository.deleteCountry(country)
        }
        getAllCountries() // Refresh the list
        selectedCountryForDeletion.value = null // Clear the selected country
        showDeleteConfirmationDialog.value = false // Hide the dialog
    }

    // Call this when the user cancels the deletion or dismisses the dialog
    fun cancelDeleteProcess() {
        selectedCountryForDeletion.value = null
        showDeleteConfirmationDialog.value = false
    }
}