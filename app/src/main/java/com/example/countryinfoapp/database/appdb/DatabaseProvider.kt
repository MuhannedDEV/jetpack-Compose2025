package com.example.countryinfoapp.database.appdb

import com.example.countryinfoapp.database.dao.CountryDao

interface DatabaseProvider {
    fun countryDao(): CountryDao
}