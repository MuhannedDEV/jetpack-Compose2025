package com.example.countryinfoapp.util

import com.example.countryinfoapp.repo.FilterCriteria

interface FilterCriteriaFactory {
    fun createFilterCriteria(filterKey: String) : FilterCriteria
}