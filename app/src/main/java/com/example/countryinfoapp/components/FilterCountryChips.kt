package com.example.countryinfoapp.components

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterCountryChips(filterBy: String, selectedFilter: MutableState<String?>) {
    var selected by remember { mutableStateOf(false) }

    Log.d("CountryFilterDebug", "[RECOMPOSE] filterBy=$filterBy, selectedFilter.value=${selectedFilter.value}, selected=$selected")
    selected = selectedFilter.value == filterBy

    FilterChip(
        onClick = {
            Log.d("CountryFilterDebug", "[CLICK] filterBy=$filterBy, selectedFilter.value=${selectedFilter.value}, selected=$selected")
            if (selectedFilter.value == filterBy) {
                selectedFilter.value = null
                selected = false
                Log.d("CountryFilterDebug", "[CLICK] Deselected filterBy=$filterBy, selectedFilter.value=${selectedFilter.value}, selected=$selected")
            } else {
                selectedFilter.value = filterBy
                selected = true
                Log.d("CountryFilterDebug", "[CLICK] Selected filterBy=$filterBy, selectedFilter.value=${selectedFilter.value}, selected=$selected")
            }
        },
        label = {
            Text(filterBy)
        },
        modifier = Modifier.padding(2.dp),
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}
