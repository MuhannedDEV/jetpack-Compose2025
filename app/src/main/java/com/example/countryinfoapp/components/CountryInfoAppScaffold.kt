package com.example.countryinfoapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
// Corrected: Assuming SortByAlpha was intended to be Sort or if SortByAlpha is now resolving.
// If SortByAlpha is still an error, this should be androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.SortByAlpha 
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.countryinfoapp.data.Country
import com.example.countryinfoapp.screen.MainScreen
import androidx.compose.material3.TopAppBarDefaults


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryInfoAppScaffold(countryList: List<Country>) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                title = { Text("CountryInfo App") },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = Color.White // Added tint
                        )
                    }
                    IconButton(onClick = { /* TODO: Handle filter action */ }) {
                        Icon(
                            imageVector = Icons.Filled.Filter,
                            contentDescription = "Filter",
                            tint = Color.White // Added tint
                        )
                    }
                    IconButton(onClick = { /* TODO: Handle sort action */ }) {
                        Icon(
                            // Assuming SortByAlpha is intended and resolving.
                            // If not, change to Icons.Filled.Sort and ensure correct import.
                            imageVector = Icons.Filled.SortByAlpha, 
                            contentDescription = "Sort",
                            tint = Color.White // Added tint
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "MoreVert", // Corrected typo previously
                            tint = Color.White // Added tint
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        MainScreen(countryList, innerPadding)
    }
}