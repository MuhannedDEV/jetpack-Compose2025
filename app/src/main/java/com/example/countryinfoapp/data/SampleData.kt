package com.example.countryinfoapp.data

// Reusable sample data for previews
val usaSampleData = Country(
    name = Name(common = "United States", official = "United States of America"),
    capital = listOf("Washington, D.C."),
    region = "Americas",
    subregion = "North America",
    flags = Flags(
        png = "android.resource://com.example.countryinfoapp/drawable/us",
        svg = null // Explicitly set svg to null
    ),
    flag = "Flag of the United States",
    currencies = mapOf("USD" to Currency(name = "United States Dollar", symbol = "$")),
    idd = Idd(root = "+1", suffixes = listOf()),
    tld = listOf(".us")
)
