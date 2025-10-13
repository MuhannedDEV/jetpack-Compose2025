package com.example.countryinfoapp.repo

import com.example.countryinfoapp.data.Country

interface ICountryRepo {
    suspend fun fetchAndInsertAll()
    suspend fun getAllCountries(): List<Country>
    suspend fun deleteCountry(country: Country)
    suspend fun updateCapital(country: Country, newCapital: String)

    suspend fun filterCountries(criteria: FilterCriteria): List<Country>
}