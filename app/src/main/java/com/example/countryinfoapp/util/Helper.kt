package com.example.countryinfoapp.util

import android.content.Context
import com.example.countryinfoapp.R
import com.example.countryinfoapp.data.Country
import com.example.countryinfoapp.data.CountryInfo
import kotlinx.serialization.json.Json // Added import for Json

fun getCountryList(): MutableList<CountryInfo> {
    val countryList = mutableListOf<CountryInfo>()
    countryList.add(
        CountryInfo(
            R.drawable.`in`,
            "India",
            "New Delhi",
            "Republic of India",
            "Asia","South Asia",
            "₹",
            "Indian Rupee",
            "+91",
            ".in")
    ) // Removed redundant semicolon

    countryList.add(
        CountryInfo(
            R.drawable.us,
            "United States",
            "Washington, D.C.",
            "United States of America",
            "Americas","North America",
            "$",
            "United States dollar",
            "+1",
            ".us")
    )


    countryList.add(
        CountryInfo(
            R.drawable.za,
            "Bangladesh",
            "Pretoria",
            "People's Republic of Bangladesh",
            "Asia","Southern Africa",
            "R",
            "South African rand",
            "+27",
            ".za")
    )

    countryList.add(
        CountryInfo(
            R.drawable.pk,
            "Pakistan",
            "Islamabad",
            "Islamic Republic of Pakistan",
            "Asia","South Asia",
            "₨",
            "Pakistani rupee",
            "+92",
            ".pk")
    )


    countryList.add(
        CountryInfo(
            R.drawable.my,
            "Malaysia",
            "Kuala Lumpur",
            "Malaysia",
            "Africa","South-Eastern Asia",
            "RM",
            "Malaysian ringgit",
            "+60",
            ".my")
    )

    countryList.add(
        CountryInfo(
            R.drawable.nl,
            "Netherlands",
            "Amsterdam",
            "Kingdom of the Netherlands",
            "Europe","Western Europe",
            "€",
            "Euro",
            "+31",
            ".nl")
    )

    countryList.add(
        CountryInfo(
            R.drawable.it,
            "Italy",
            "Rome",
            "Italian Republic",
            "Europe","Southern Europe",
            "€",
            "Euro",
            "+39",
            ".it")
    )

    countryList.add(
        CountryInfo(
            R.drawable.es,
            "Spain",
            "Madrid",
            "Kingdom of Spain",
            "Europe","Southern Europe",
            "€",
            "Euro",
            "+34",
            ".es")
    )

    countryList.add(
        CountryInfo(
            R.drawable.np,
            "Nepal",
            "Dhaka",
            "Federal Democratic Republic of Nepal",
            "Asia","South Asia",
            "₨",
            "Nepalese rupee",
            "+593",
            ".np")
    )


    return countryList
}

fun getJsonString(context: Context): String {
    val inputStream = context.resources.openRawResource(R.raw.countries)
    return inputStream.bufferedReader().use {
        it.readText()
    }
}

fun getCountryListFromJson(context: Context): MutableList<Country> {
    val jsonStringFromRaw = getJsonString(context)
    return Json { ignoreUnknownKeys = true }.decodeFromString<List<Country>>(jsonStringFromRaw).toMutableList()
}
