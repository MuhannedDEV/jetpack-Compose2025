package com.example.countryinfoapp.database

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ConvertersTest {

    private val converters = Converters()

    @Test
    fun testFromJsonToList() {
        val json = """["USA", "Canada", "Mexico"]"""
        val list = converters.fromJsonToStringList(json)
        assertEquals(listOf("USA", "Canada", "Mexico"), list)
    }

    @Test
    fun testFromStringToJson() {
        val list = listOf("USA", "Canada", "Mexico")
        val json = converters.fromStringListToJson(list)
        assertEquals("""["USA","Canada","Mexico"]""", json)
    }
}


