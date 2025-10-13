package com.example.countryinfoapp.repo

interface ICountryRepo {
    suspend fun fetchAndInsertAll()
    suspend fun getAllCountries(): List<com.example.countryinfoapp.data.Country>
    suspend fun deleteCountry(country: com.example.countryinfoapp.data.Country)
    suspend fun updateCapital(country: com.example.countryinfoapp.data.Country, newCapital: String)
}