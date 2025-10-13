package com.example.countryinfoapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.countryinfoapp.data.Country

@Dao
interface CountryDao: ICountryDao {
    // Select all countries
    @Query("SELECT * from Country")
    override suspend fun getAllCountries(): List<Country>

    // Select countries by continent
    @Query("SELECT * from Country WHERE continents LIKE :continent")
    override suspend fun getCountriesByContinent(continent: String): List<Country>

    // Insert all countries
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    override suspend fun insertAll(countries: List<Country>)

    // Delete a country
    @Delete
    override suspend fun delete(country: Country): Int

    // Update a country (full object)
    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    override suspend fun updateCountry(country: Country): Int

    // Update capital by id (not recommended to use List<String> for capital, but matching screenshot)
    @Query("UPDATE Country set capital = :capital where id = :id")
    override suspend fun updateCountryCapital(capital: List<String>, id: Int): Int
}