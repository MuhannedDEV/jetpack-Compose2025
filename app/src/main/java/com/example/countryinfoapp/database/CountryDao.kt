package com.example.countryinfoapp.database

import androidx.room.*
import com.example.countryinfoapp.data.Country

@Dao
interface CountryDao {
    // Select all countries
    @Query("SELECT * from Country")
    suspend fun getAllCountries(): List<Country>

    // Select countries by continent
    @Query("SELECT * from Country WHERE continents LIKE :continent")
    suspend fun getCountriesByContinent(continent: String): List<Country>

    // Insert all countries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<Country>)

    // Delete a country
    @Delete
    suspend fun delete(country: Country): Int

    // Update a country (full object)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCountry(country: Country): Int

    // Update capital by id (not recommended to use List<String> for capital, but matching screenshot)
    @Query("UPDATE Country set capital = :capital where id = :id")
    suspend fun updateCountry(capital: List<String>, id: Int): Int
}

