package com.example.countryinfoapp.repo

import android.content.Context
import com.example.countryinfoapp.data.Country
import com.example.countryinfoapp.database.CountryDao
import com.example.countryinfoapp.util.getCountryLis
import com.example.countryinfoapp.util.getCountryList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryRepository(private val context: Context, private val countryDao: CountryDao) {
    private val contextForRepository = context.applicationContext
    private var allCountries: List<Country> = emptyList()

    // Fetch and insert all countries if not already present
    suspend fun fetchAndInsertAll() = withContext(Dispatchers.IO) {
        val allCountries = getAllCountries()
        if (allCountries.isNotEmpty()) {
            return@withContext
        } else {
            val countryMutableList = getCountryLis(contextForRepository)
            val countryList: List<Country> = countryMutableList.toList()
            countryDao.insertAll(countryList)
            return@withContext
        }
    }

    // Get all countries, using cache if available
    suspend fun getAllCountries(): List<Country> = withContext(Dispatchers.IO) {
        if (allCountries.isNotEmpty()) {
            return@withContext allCountries
        } else {
            allCountries = countryDao.getAllCountries()
            return@withContext allCountries
        }
    }


}

