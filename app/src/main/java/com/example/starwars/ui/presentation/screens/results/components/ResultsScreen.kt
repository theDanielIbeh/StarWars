package com.example.starwars.ui.presentation.screens.results.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.starwars.ui.presentation.common.AppTopBar
import com.example.starwars.ui.presentation.common.ErrorScreen
import com.example.starwars.ui.presentation.screens.results.components.films.FilmsContent
import com.example.starwars.ui.presentation.screens.results.components.starships.StarShipsContent
import com.example.starwars.ui.presentation.screens.results.components.vehicles.VehiclesContent
import com.example.starwars.ui.presentation.screens.results.ResultsViewModel
import com.example.starwars.ui.presentation.viewmodels.category.CategoryViewModel
import com.example.starwars.R.string as AppText

@Composable
fun ResultsScreen(
    onClear: () -> Unit,
    searchString: String?,
    modifier: Modifier = Modifier,
    resultsViewModel: ResultsViewModel = hiltViewModel(),
    categoryViewModel: CategoryViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    // Collect category from datastore
    val categoryModel by categoryViewModel.getData.collectAsStateWithLifecycle()
    val categoryFromStore = categoryModel.category

    // Determine categoryText
    val categoryText = searchString ?: categoryFromStore ?: "Unknown"

    // Construct the top bar title dynamically
    val topBarTitle = stringResource(
        AppText.displaying_results,
        categoryText.replaceFirstChar { it.uppercaseChar() } )
    Log.d("ResultsScreen", "Top Bar Title: $topBarTitle")
    val sortAscending by resultsViewModel.sortAscending.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBar(
                title = topBarTitle, onSortClick = {
                    when (categoryText) {
                        "starships", "vehicles", "films" -> {
                            resultsViewModel.toggleSortOrder()
                        }

                        else -> {
                            // do nothing
                            Toast.makeText(context, "There Is No Data To Sort", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                },
                showSortIcon = true,
                showClearIcon = true,
                onClear = {
                    categoryViewModel.clearCategory()
                    onClear()
                },
                sortAscending = sortAscending // pass dynamic state here

            )
        },
        modifier = modifier.fillMaxSize(),


        ) { padding ->

        when (categoryText) {

            "starships" -> {
                StarShipsContent(
                    modifier = Modifier.padding(padding),
                    viewModel = resultsViewModel,
                    context = context,
                )
            }

            "vehicles" -> {
                VehiclesContent(
                    modifier = Modifier.padding(padding),
                    viewModel = resultsViewModel,
                    context = context,
                )
            }

            "films" -> {
                FilmsContent(
                    modifier = Modifier.padding(padding),
                    viewModel = resultsViewModel,
                    context = context,
                )
            }

            else -> {
                ErrorScreen(
                    errorMessage = stringResource(AppText.category_not_found),
                    onRetry = {},
                    showRetryButton = false
                )
            }
        }
    }
}



