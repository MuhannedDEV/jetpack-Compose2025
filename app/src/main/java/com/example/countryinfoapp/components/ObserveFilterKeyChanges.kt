package com.example.countryinfoapp.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import com.example.countryinfoapp.repo.FilterByContinent
import com.example.countryinfoapp.repo.FilterByDriveSide
import com.example.countryinfoapp.repo.FilterCriteria
import com.example.countryinfoapp.viewmodel.CountryViewModel

@Composable
fun ObserveFilterKeyChanges(filterByKey: MutableState<String>,
                            selectedFilter: MutableState<String?>,
                            viewModelCountryOps: CountryViewModel
) {
    val filterKey by filterByKey
    val selectedFilterValue by selectedFilter

    Log.d("CountryFilterDebug", "[COMPOSE] filterKey='$filterKey', selectedFilterValue='$selectedFilterValue'")
    LaunchedEffect(filterKey, selectedFilterValue) {
        Log.d("CountryFilterDebug", "[LaunchedEffect] filterKey='$filterKey', selectedFilterValue='$selectedFilterValue'")
        if (selectedFilterValue != null) {
            Log.d("CountryFilterDebug", "[LaunchedEffect] Calling filterBy with filterKey='$filterKey', selectedFilterValue='$selectedFilterValue'")
            filterBy(filterKey, selectedFilterValue, viewModelCountryOps)
        }
    }
}

suspend fun filterBy(
    filterKey: String,
    selectedFilterValue: String?,
    viewModelCountryOps: CountryViewModel
) {
    Log.d("CountryFilterDebug", "[filterBy] Called with filterKey='$filterKey', selectedFilterValue='$selectedFilterValue'")
    if (filterKey.isNotEmpty()) {
        val filterCriteria = determineFilterCriteria(selectedFilterValue!!, filterKey)
        Log.d("CountryFilterDebug", "[filterBy] filterCriteria='${filterCriteria?.javaClass?.simpleName}', filterKey='$filterKey'")
        filterCriteria?.let {
            Log.d("CountryFilterDebug", "[filterBy] Calling viewModelCountryOps.filterCountries with criteria='$it'")
            viewModelCountryOps.filterCountries(it as FilterCriteria)
        }
    } else {
        Log.d("CountryFilterDebug", "[filterBy] filterKey is empty, calling getAllCountries()")
        viewModelCountryOps.getAllCountries()
    }
}

fun determineFilterCriteria(selectedFilterValue: String, filterKey: String) : Any? {
    Log.d("CountryFilterDebug", "[determineFilterCriteria] Called with selectedFilterValue='$selectedFilterValue', filterKey='$filterKey'")
    val result = when(selectedFilterValue) {
        "Continent" -> {
            FilterByContinent(filterKey)
        }
        "Drive Side" -> {
            FilterByDriveSide(filterKey)
        }
        else -> {
            null
        }
    }
    Log.d("CountryFilterDebug", "[determineFilterCriteria] Returning result='${result?.javaClass?.simpleName}'")
    return result
}