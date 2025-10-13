package com.example.countryinfoapp.database.dao

import com.example.countryinfoapp.data.Country

interface ICountryDao {
    suspend fun getAllCountries(): List<Country>
    suspend fun getCountriesByContinent(continent: String): List<Country>
    suspend fun insertAll(countries: List<Country>)
    suspend fun delete(country: Country): Int
    suspend fun updateCountry(country: Country): Int
    suspend fun updateCountryCapital(capital: List<String>, id: Int): Int
}