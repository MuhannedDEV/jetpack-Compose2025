package com.example.countryinfoapp.repo

import com.example.countryinfoapp.data.Country

class FilterByContinent(private val continent: String?): FilterCriteria {

    override suspend fun filter(countries: List<Country>): List<Country> {
        return countries.filter { it.continents?.contains(continent) ?: false }
    }

}