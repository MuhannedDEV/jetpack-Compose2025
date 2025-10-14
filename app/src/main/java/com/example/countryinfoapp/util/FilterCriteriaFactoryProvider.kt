package com.example.countryinfoapp.util

object FilterCriteriaFactoryProvider {
    private val factories = mapOf(
        "Continent" to ContinentFilterFactory(),
        "Drive Side" to DriveSideFilterFactory()
    )

    fun getFactory(selectedFilter: String): FilterCriteriaFactory? {
        return factories[selectedFilter]
    }
}
