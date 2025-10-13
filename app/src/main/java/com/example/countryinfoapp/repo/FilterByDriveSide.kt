package com.example.countryinfoapp.repo

import com.example.countryinfoapp.data.Country

class FilterByDriveSide(private val driveSide: String?) : FilterCriteria {

    override suspend fun filter(countries: List<Country>): List<Country> {
        return countries.filter { it.car?.side?.equals(driveSide) ?: false }
    }
}