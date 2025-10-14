package com.example.countryinfoapp.util

import com.example.countryinfoapp.repo.FilterByContinent
import com.example.countryinfoapp.repo.FilterCriteria

class ContinentFilterFactory : FilterCriteriaFactory {
    override fun createFilterCriteria(filterKey: String): FilterCriteria {
        return FilterByContinent(filterKey)
    }
}
