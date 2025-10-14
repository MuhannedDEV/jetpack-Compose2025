package com.example.countryinfoapp.viewmodel

import androidx.compose.runtime.MutableState
import com.example.countryinfoapp.data.Country
import com.example.countryinfoapp.repo.FilterCriteria

interface ICountryViewModel {
    val allCountries: MutableState<List<Country>>
    suspend fun getAllCountries()
    suspend fun updateCountry(newCapital: String)
    suspend fun deleteCountry()
    suspend fun filterCountries(filterCriteria: FilterCriteria)
}