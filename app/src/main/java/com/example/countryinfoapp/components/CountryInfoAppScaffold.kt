package com.example.countryinfoapp.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
// Corrected: Assuming SortByAlpha was intended to be Sort or if SortByAlpha is now resolving.
// If SortByAlpha is still an error, this should be androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
// import androidx.compose.material3.BottomAppBarDefaults // Not needed for direct color assignment
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.countryinfoapp.data.Country
import com.example.countryinfoapp.screen.MainScreen
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countryinfoapp.database.appdb.AppDataBase
import com.example.countryinfoapp.repo.CountryRepository
import com.example.countryinfoapp.viewmodel.CountryViewModel
import com.example.countryinfoapp.viewmodel.CountryViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryInfoAppScaffold() {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    val context = LocalContext.current
    //Initialise Dao
    val countryDao = AppDataBase.getDatabase(context.applicationContext)?.countryDao()
    //Initialise the Repository
    val countryRepository = countryDao?.let { CountryRepository(context, it) }

    //Initialise the ViewModel
    val viewModel: CountryViewModel =
        viewModel(factory = countryRepository?.let { CountryViewModelFactory(repository = it) })

    val selectedFilter = viewModel.selectedFilter
    val filterByKey = viewModel.filterByKey

    ObserveFilterKeyChanges(filterByKey, selectedFilter, viewModel)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                title = { Text("CountryInfo App") },
//                navigationIcon = {
//                    IconButton(onClick = { /* TODO: Handle navigation icon press */ }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBackIosNew,
//                            contentDescription = "ArrowBackIosNew",
//                            tint = Color.White // Added tint
//                        )
//                    }
//                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
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
                },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            BottomAppBar {
                FilterCountryChips("Continent", selectedFilter)
                FilterCountryChips("Drive Side", selectedFilter)

                if (selectedFilter.value != null) {
                    TextField(
                        value = filterByKey.value,
                        onValueChange = { newValue ->
                            Log.d("CountryFilterDebug", "[onValueChange] TextField called with newValue='$newValue'")
                            filterByKey.value = newValue
                            // If you call any filtering function here, add a log for it as well
                        },
                        modifier = Modifier.padding(3.dp),
                        label = { Text("") },
                        singleLine = true,
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Handle FAB click */ },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(imageVector = Icons.Filled.Filter, contentDescription = "Filter")

            }
        }
    ) { innerPadding ->
        MainScreen( innerPadding)
    }
}
