package com.example.countryinfoapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Slk(
    val common: String? = null,
    val official: String? = null
)