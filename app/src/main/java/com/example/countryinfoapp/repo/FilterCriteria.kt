package com.example.countryinfoapp.repo

import com.example.countryinfoapp.data.Country

interface FilterCriteria {
    suspend fun filter(countries: List<Country>): List<Country>
}