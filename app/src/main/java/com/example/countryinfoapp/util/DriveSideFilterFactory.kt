package com.example.countryinfoapp.util

import com.example.countryinfoapp.repo.FilterByContinent
import com.example.countryinfoapp.repo.FilterByDriveSide
import com.example.countryinfoapp.repo.FilterCriteria

class DriveSideFilterFactory : FilterCriteriaFactory {
    override fun createFilterCriteria(filterKey: String): FilterCriteria {
        return FilterByDriveSide(filterKey)
    }
}
