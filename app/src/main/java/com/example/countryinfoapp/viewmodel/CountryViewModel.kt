package com.example.countryinfoapp.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countryinfoapp.data.Country
import com.example.countryinfoapp.repo.CountryRepository
import kotlinx.coroutines.launch

class CountryViewModel(private val countryRepository: CountryRepository) : ViewModel() {
    // Update the capital of a country using selectedCountryForUpdation and countryMutableState
    suspend fun updateCountry(newCapital: String) {
        selectedCountryForUpdation?.let { countryMutableState ->
            countryMutableState.value?.let { country ->
                countryRepository.updateCapital(country, newCapital)
            }
        }
    }

    val allCountries: MutableState<List<Country>> = mutableStateOf(emptyList())
    val isLoading: MutableState<Boolean> = mutableStateOf(true)

    var selectedCountryForDeletion: MutableState<Country?> = mutableStateOf(null)
    var selectedCountryForUpdation: MutableState<Country?> = mutableStateOf(null)
    val showDeleteConfirmationDialog: MutableState<Boolean> = mutableStateOf(false) // Added
    val showUpdateDialogAlert: MutableState<Boolean> = mutableStateOf(false) // Added

    var selectedFilter: MutableState<String?> =  mutableStateOf(null)
    var filterByKey : MutableState<String> =  mutableStateOf("")

    val filteredCountryList : MutableState<List<Country>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch {
            fetchAndInsertAll()
        }
    }

    // IMPROVEMENT: Coroutine simplification and error handling should be done here in the ViewModel
    private suspend fun fetchAndInsertAll() {
        try {
            countryRepository.fetchAndInsertAll() // Direct call, no need for launch
            getAllCountries()
            isLoading.value = false
        } catch (e: Exception) {
            // Handle error, e.g., log or update error state for UI
        }
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

    suspend fun updateCountryItem(newCapital: String) {
        selectedCountryForUpdation?.let {
            it.value?.let { country ->
                countryRepository.updateCapital(country, newCapital)
                getAllCountries() // Refresh the list
            }
        }
        selectedCountryForUpdation.value = null // Clear the selected country
        showUpdateDialogAlert.value = false // Hide the dialog

    }

    suspend fun filterCountryByContinent(){
        isLoading.value = true
        val filteredByContinent  = allCountries.value.filter { it.continents?.contains(filterByKey.value) ?: false }
        Log.i("FilterCriteria", "Filtered by Continent ${filteredByContinent.size}")
        filteredCountryList.value = filteredByContinent
        filteredCountryList.value.let {
            when {
                it.isNotEmpty() -> {
                    allCountries.value = it
                    isLoading.value = false
                }
            }
        }
    }

    suspend fun filterCountryByDriveSide(){
        isLoading.value = true
        val filteredByDriveSide   = allCountries.value.filter { it.car?.side?.equals(filterByKey.value) ?: false }
        Log.i("FilterCriteria", "Filtered by Drive Side ${filteredByDriveSide.size}")
        filteredCountryList.value = filteredByDriveSide
        filteredCountryList.value.let {
            when {
                it.isNotEmpty() -> {
                    allCountries.value = it
                    isLoading.value = false
                }
            }
        }
    }


}