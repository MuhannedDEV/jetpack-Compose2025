package com.example.countryinfoapp.repo

import android.content.Context
import com.example.countryinfoapp.data.Country
import com.example.countryinfoapp.database.dao.CountryDao
import com.example.countryinfoapp.database.dao.ICountryDao
import com.example.countryinfoapp.util.getCountryLis
import com.example.countryinfoapp.util.getCountryList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class CountryRepository(private val context: Context,
                        private val countryDao: ICountryDao,
                        private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ICountryRepo {
    private val contextForRepository = context.applicationContext
    private var allCountries: List<Country> = emptyList()

    // Fetch and insert all countries if not already present
    override suspend fun fetchAndInsertAll() = withContext(dispatcher) {
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
    override suspend fun getAllCountries(): List<Country> = withContext(dispatcher) {
        if (allCountries.isNotEmpty()) {
            return@withContext allCountries
        } else {
            allCountries = countryDao.getAllCountries()
            return@withContext allCountries
        }
    }


    //Delete one country from the database
    override suspend fun deleteCountry(country: Country) = withContext(dispatcher) {
        countryDao.delete(country)
        allCountries = countryDao.getAllCountries() // to refresh the list
    }


    override suspend fun updateCapital(country: Country, newCapital: String) =
        withContext(dispatcher) {
            val parsedString = "[\"$newCapital\"]"
            val parsedArray = Json.decodeFromString<List<String>>(parsedString)
            val updatedCountry = country.copy(capital = parsedArray)
            updatedCountry.let {
                countryDao.updateCountry(it)
                allCountries = countryDao.getAllCountries() // to refresh the list
            }
        }


}

