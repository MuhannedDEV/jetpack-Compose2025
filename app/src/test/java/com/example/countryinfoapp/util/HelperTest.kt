package com.example.countryinfoapp.util

import android.content.Context
import android.content.res.Resources
import com.example.countryinfoapp.R
import com.example.countryinfoapp.data.Country // Ensure this data class is @Serializable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.io.ByteArrayInputStream
import java.io.InputStream

@RunWith(MockitoJUnitRunner::class)
class HelperTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockResources: Resources

    private val sampleJsonString = """
        [
            {
                "name": {
                    "common": "TestCountry",
                    "official": "Official TestCountry"
                },
                "capital": ["TestCapital"],
                "region": "TestRegion",
                "subregion": "TestSubregion",
                "currencies": {
                    "TC": {
                        "name": "TestCurrency",
                        "symbol": "T$"
                    }
                },
                "idd": {
                    "root": "+9",
                    "suffixes": ["99"]
                },
                "tld": [".tc"]
            }
        ]
    """.trimIndent()

    @Before
    fun setUp() {
        `when`(mockContext.resources).thenReturn(mockResources)
    }

    @Test
    fun getJsonString_readsCorrectlyFromResources() {
        val inputStream: InputStream = ByteArrayInputStream(sampleJsonString.toByteArray())
        `when`(mockResources.openRawResource(R.raw.countries)).thenReturn(inputStream)

        val jsonString = getJsonString(mockContext)
        assertEquals(sampleJsonString, jsonString)
    }

    @Test
    fun getCountryListFromJson_parsesJsonCorrectly() {
        val inputStream: InputStream = ByteArrayInputStream(sampleJsonString.toByteArray())
        `when`(mockResources.openRawResource(R.raw.countries)).thenReturn(inputStream)

        val countryList = getCountryListFromJson(mockContext)

        assertNotNull(countryList)
        assertEquals(1, countryList.size)

        val country = countryList[0]
        assertEquals("TestCountry", country.name?.common)
        assertEquals("Official TestCountry", country.name?.official)
        assertEquals("TestCapital", country.capital?.get(0))
        assertEquals("TestRegion", country.region)
        assertEquals("TestSubregion", country.subregion)
        assertNotNull(country.currencies)
        assertEquals("TestCurrency", country.currencies?.get("TC")?.name)
        assertEquals("T$", country.currencies?.get("TC")?.symbol)
        assertEquals("+999", country.idd?.let { it.root + it.suffixes?.get(0) ?: "" })
        assertEquals(".tc", country.tld?.get(0))
    }

    // You might need a more complex setup if your Country data class has more fields
    // or if those fields are mandatory and not nullable.
    // Ensure your Country.kt data class is properly annotated with @Serializable
    // and all its nested classes if any.
}